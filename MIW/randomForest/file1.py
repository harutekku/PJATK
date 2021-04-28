"""
Author: s18688 Jakub Pawłowicz

Raises:
    argparse.ArgumentTypeError: Raise when test_train_split is out of range


"""

import os
import argparse
import numpy as np
import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn.tree import export_graphviz


def float_range(number):
    """ Check argument to passed the range

    Args:
        x (float): The number to check range
    Returns:
        float: Float in range
    """
    try:
        number = float(number)
    except ValueError:
        raise argparse.ArgumentTypeError(
            "%r not a floating-point literal" % (number,))

    if number < 0.2 or number > 0.8:
        raise argparse.ArgumentTypeError("%r not in acceptable range" % (number,))
    return number


def parse_arguments():
    parser = argparse.ArgumentParser(add_help=True,
                                     description='Random forest classifier')
    parser.add_argument('-t', '--train_file',
                        required=True,
                        type=open,
                        help='CSV File with data')
    parser.add_argument('-s', '--train_test_split',
                        help='Numbers of splitting the file into training and test data ',
                        default=0.2,
                        type=float_range)
    parser.add_argument('-c', '--classification_column',
                        required=True,
                        type=str,
                        help='Name of the column with classification data')
    parser.add_argument('--max_depth',
                        type=int,
                        help='Maximum depth of tree',
                        default=5)
    parser.add_argument('--acceptable_impurity',
                        type=float,
                        help='Level of impurity at which we no longer split nodes',
                        default=0)
    return parser.parse_args()


class RandomForest(object):
    """
    A class used for RandomForestClassifier

    Attributes:
    clasification_column (str): Name of column where we store classification data
    data (DataFrame): Data for forest
    labels (array): Labels from data
    features (array): Filtered array with data
    X_train, X_test, y_train, y_test (list): Splitted data
    forest (RandomForestClassifier): Forest with our data

    """

    def __init__(self, train_file, split_train_test, classification_column,
                max_depth, acceptable_impurity):
        self.classification_column = classification_column
        self.data = pd.read_csv(
            train_file, dtype={'no_lasting_investmen': 'Int64'}).dropna()
        self.labels = np.array(self.data[self.classification_column])
        self.features = np.array(self.data.drop(classification_column, axis=1))
        self.x_train, self.x_test, self.y_train, self.y_test = train_test_split(
            self.features, self.labels, test_size=split_train_test)
        self.forest = RandomForestClassifier(
            n_estimators=100, criterion="gini", max_depth=max_depth,
            min_impurity_split=acceptable_impurity)

    def train(self):
        self.forest.fit(self.x_train, self.y_train)
        y_pred = self.forest.predict(self.x_train)
        accuracy = accuracy_score(self.y_train, y_pred) * 100
        with open('corectness.txt', 'w') as file:
            print("Correctnes level on training dataset: {:.2f}%".format(accuracy), file=file)

    def test(self):
        y_pred_test = self.forest.predict(self.x_test)
        accuracy = accuracy_score(self.y_test, y_pred_test) * 100
        with open('corectness.txt', 'a') as file:
            print("Correctnes level on test dataset: {:.2f}%".format(accuracy), file=file)

    def generate_png(self):
        export_graphviz(self.forest.estimators_[1], out_file='tree.dot',
            feature_names=self.data.drop(self.classification_column, axis=1)
            .columns, filled=True, rounded=True)
        os.system('dot -Tpng tree.dot -o tree.png')


def main(args):
    forest = RandomForest(args.train_file, args.train_test_split,
                          args.classification_column, args.max_depth, args.acceptable_impurity)
    forest.train()
    forest.test()
    forest.generate_png()


main(parse_arguments())
