#include <iostream>
#include <fstream>
#include <memory>
#include <string>

using namespace std;

//-------------------------- bazowe struktury
struct node {
    char key = '|';
    shared_ptr<node> left_child = nullptr;
    shared_ptr<node> right_child = nullptr;
    shared_ptr<node> father = nullptr;
};

struct list {
    shared_ptr<node> value = nullptr;
    shared_ptr<list> next = nullptr;

    list() {}

    list(shared_ptr<node> nod) : value(nod) {}
};

//-------------------------- bazowe operacje na listach i nodach
shared_ptr<list> addtoList(shared_ptr<list> list, shared_ptr<node> nod) {
    if (list->value == nullptr) {
        list->value = nod;
        return list;
    } else {
        shared_ptr<struct ::list> actual(new struct::list(nod));
        actual->next = list;
        return actual;
    }
}

void showList(shared_ptr<list> list) {
    shared_ptr<struct ::list> actual = list;
    while (actual != nullptr) {
        cout << actual->value->key << " ";
        actual = actual->next;
    }
    cout << endl;
}

bool ifLeaves(shared_ptr<node> node) {
    return node->left_child == nullptr && node->right_child == nullptr;
}

shared_ptr<list> cutListToLeaves(shared_ptr<list> list) {
    shared_ptr<struct ::list> leaves(new struct::list);
    shared_ptr<struct ::list> actual = list;
    while (actual != nullptr) {
        if (ifLeaves(actual->value)) {
            leaves = addtoList(leaves, actual->value);
        }
        actual = actual->next;
    }
    return leaves;
}

string getKey(shared_ptr<node> node) {
    return string(1, node->key);
}

string getWord(shared_ptr<node> node) {
    if (node->father == nullptr)return getKey(node);
    return getKey(node) + getWord(node->father);
}

shared_ptr<node> compareWords(shared_ptr<node> one, shared_ptr<node> two) {
    string w1 = getWord(one), w2 = getWord(two);
    if (w1.compare(w2) > 0) return one;
    else return two;
}

//--------------------------- bardziej zaawansowane operacje

shared_ptr<node> addKey(shared_ptr<node> root, char value, string road) {
    shared_ptr<node> actual = root;
    for (int i = 0; i < road.size(); i++) {
        char direction = road[i];
        if (direction == 'R') {
            if (actual->right_child == nullptr) {
                actual->right_child = shared_ptr<node>(new node);
                actual->right_child->father = actual;
            }
            actual = actual->right_child;
        } else if (direction == 'L') {
            if (actual->left_child == nullptr) {
                actual->left_child = shared_ptr<node>(new node);
                actual->left_child->father = actual;
            }
            actual = actual->left_child;
        }
    }
    actual->key = value;
    return actual;
}

shared_ptr<node> searchLastWord(shared_ptr<list> leaves) {
    shared_ptr<list> actual = leaves;
    shared_ptr<node> lastWord = actual->value;
    while (actual->next != nullptr) {
        actual = actual->next;
        lastWord = compareWords(lastWord, actual->value);
    }
    return lastWord;
}

void orajPlik(string filename) {
    ifstream file;
    file.open(filename);
    if (file.is_open()) {
        shared_ptr<node> root(new struct::node);
        shared_ptr<struct ::list> list(new struct::list);
        while (!file.eof()) {
            string line = "";
            getline(file, line);
            if (line != "") {
                shared_ptr<node> nod = addKey(root, line[0], line.substr(2));
                list = addtoList(list, nod);
            }
        }
        //showList(list);
        shared_ptr<struct ::list> leaves = cutListToLeaves(list);
        //showList(leaves);
        shared_ptr<node> last = searchLastWord(leaves);
        cout << getWord(last) << endl;
        file.close();
    } else cerr << "Unable to open the file" << endl;
}

int main(int args, char **argv) {
    string file = argv[1];
    orajPlik(file);
    //orajPlik("f1.txt");
    //orajPlik("f2.txt");
    //orajPlik("f3.txt");
    //orajPlik("f4.txt");
    //orajPlik("input1");
    //orajPlik("input2");
    //orajPlik("input3");
    //orajPlik("input4");
    //orajPlik("input5");
    //orajPlik("input6");
    return 0;
}