#include <iostream>
#include <fstream>
#include <memory>

using namespace std;
struct node {
    char key;
    shared_ptr<node> left_child = nullptr;
    shared_ptr<node> right_child = nullptr;
    shared_ptr<node> father = nullptr;
};

struct list {
    shared_ptr<node> value;
    shared_ptr<list> next = nullptr;
};

void addtoList(shared_ptr<list> list, shared_ptr<node> nod) {
    shared_ptr<struct::list> actual = list;
    while (actual->next != nullptr)actual = actual->next;
    actual->next = shared_ptr<struct::list>(new struct::list);
    actual->next->value = nod;
}
void showList(shared_ptr<list> list){
    shared_ptr<struct::list> actual = list;
    while(actual->next!= nullptr){
        cout<<actual->value->key<<" ";
        actual=actual->next;
    }
}

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

string getKey(shared_ptr<node> node) {
    return string(1, node->key);
}

string getWord(shared_ptr<node> node) {
    if (node->father == nullptr)return getKey(node);
    return getKey(node) + getWord(node->father);
}

shared_ptr<node> compareWords(shared_ptr<node> one, shared_ptr<node> two) {
    string w1 = getWord(one), w2 = getWord(two);
    cout << w1 << " " << w2 << endl;
    if (w1.compare(w2) > 0) return one;
    else return two;
}

shared_ptr<node> searchLastLeave(shared_ptr<list> list_root) {
    shared_ptr<list> actual = list_root;
    shared_ptr<node> last = actual->value;
    while (actual->next != nullptr) {
        actual = actual->next;
        if (compareWords(last, actual->value) < 0)last = actual->value;
        cout<<actual->value->key<<endl;
    }
    return last;
}

int main(int args, char **argv) {
    ifstream file;
    file.open(argv[1]);
    if (file.is_open()) {
        shared_ptr<node> root(new node);
        shared_ptr<list> list(new struct::list);
        while (!file.eof()) {
            string line = "";
            getline(file, line);
            shared_ptr<node> nod = addKey(root, line[0], line.substr(2));
            cout<<nod->key<<" ";
            addtoList(list, nod);
        }
        showList(list);
        shared_ptr<node> last = searchLastLeave(list);
        cout << getWord(last);
        file.close();

    } else cerr << "Unable to open the file" << endl;

    return 0;
}