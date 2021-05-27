'''
@author: Jakub Pawłowicz s18688
AlexNet with keras library to recognize fine grained classes from CIFAR100 dataset.
'''
import argparse
import tensorflow as tf
import matplotlib.pyplot as plt
from tensorflow.keras.datasets import cifar100
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Conv2D, Dropout, Flatten, MaxPooling2D, ZeroPadding2D
from tensorflow.keras import utils

def main(args):
    (train_data, train_labels), (test_data, test_labels) = cifar100.load_data(label_mode='fine')
    train_labels = utils.to_categorical(train_labels, 100)
    test_labels = utils.to_categorical(test_labels, 100)
    sequential = Sequential()
    sequential.add(Conv2D(filters=96,
                      kernel_size=(11, 11),
                      activation='relu',
                      input_shape=(32, 32, 3),
                      strides=(4,4)))
    sequential.add(tf.keras.layers.LayerNormalization())
    
    sequential.add(MaxPooling2D(pool_size=(3,3), strides=(2,2)))
    
    sequential.add(ZeroPadding2D((2, 2)))
    sequential.add(Conv2D(filters=256,
                      kernel_size=(5,5),
                      strides=(1,1),
                      activation='relu',
                      padding="same"))
    
    sequential.add(MaxPooling2D(pool_size=(3,3), strides=(2,2)))
    
    sequential.add(ZeroPadding2D((1, 1)))
    sequential.add(Conv2D(filters=384,
                     kernel_size=(3,3),
                     strides=(1,1),
                     activation='relu',
                     padding="same"))
    sequential.add(ZeroPadding2D((1, 1)))
    sequential.add(Conv2D(filters=384,
                     kernel_size=(3,3),
                     strides=(1,1),
                     activation='relu',
                     padding="same"))
    sequential.add(ZeroPadding2D((1, 1)))
    sequential.add(Conv2D(filters=256,
                     kernel_size=(3,3),
                     strides=(1,1),
                     activation='relu',
                     padding="same"))
    
    sequential.add(MaxPooling2D(pool_size=(3,3), strides=(2,2)))
    sequential.add(Flatten())
    sequential.add(Dense(4096, activation='relu'))
    sequential.add(Dropout(0.5))
    sequential.add(Dense(4096, activation='relu'))
    sequential.add(Dropout(0.5))
    sequential.add(Dense(100, activation='softmax'))
    sequential.build(input_shape=(32,32,3))
    sequential.summary()

    train_data = train_data / 255.0
    test_data = test_data / 255.0
    train_data = train_data.reshape(-1,32,32,3)
    test_data = test_data.reshape(-1,32,32,3)

    sequential.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])
    history = sequential.fit(train_data, train_labels, batch_size=32, epochs=10,
                        validation_data=(test_data, test_labels))
    model_json = sequential.to_json()
    with open(args.architecture_file + ".json", "w") as json_file:
        json_file.write(model_json)
    
    sequential.save_weights(args.weights_file + ".h5")
    
    plt.plot(history.history['accuracy'], label='Train accuracy')
    plt.plot(history.history['val_accuracy'], label = 'Validation accuracy')
    plt.xlabel('Epoch')
    plt.ylabel('Accuracy')
    plt.ylim([0, 1])
    plt.legend(loc='lower left')
    plt.show()

def parse_arguments():
    parser = argparse.ArgumentParser()
    parser.add_argument('-w',
                        '--weights_file',
                        help='Name of file in which weights are going to be saved.',
                        required=True)
    parser.add_argument('-a',
                        '--architecture_file',
                        help='Name of file in which net architecture is going to be saved.',
                        required=True)
    return parser.parse_args()

if __name__ == '__main__':
    main(parse_arguments())
