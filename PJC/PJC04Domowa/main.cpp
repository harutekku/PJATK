#include <iostream>
#include <string>
using std::cin,std::cout,std::endl;

struct tree{
    tree* left= nullptr;
    tree* right= nullptr;
    tree* parent= nullptr;
    int value=NULL;
    int counter=0;
};

void push(tree* root, int value){
    tree* tmp=root;
    if(tmp->value==NULL){
        tmp->value=value;
        tmp->counter++;
        return;
    }
    else if(tmp->value==value){
        tmp->counter++;
        return;
    }
    else if(tmp->value>value){
        if(tmp->left== nullptr){
            tree* left=new tree();
            tmp->left=left;
            left->parent=tmp;
        }
        push(tmp->left,value);
    }
    else{
        if(tmp->right== nullptr){
            tree* right=new tree();
            tmp->right=right;
            right->parent=tmp;
        }
        push(tmp->right,value);
    }
}
bool search(tree* root, int value){
    if(root->value==value){
        return true;
    }
    else if(root->value>value){
        if(root->left==nullptr)return false;
        search(root->left,value);
    }
    else{
        if(root->right==nullptr)return false;
        search(root->right,value);
    }
}
void print(tree* root){
    if(root->left!= nullptr){
        print(root->left);
    }
    for(int i=0;i<root->counter;i++){
        cout<<root->value<<", ";
    }
    if(root->right!= nullptr){
        print(root->right);
    }
}
void road(tree* root, int value,std::string* text){
    if(root->value==value){
        text->append("Znaleziono");
        //*text="Znaleziono ";//+value;
        //text+=" po drodze: ";
        return;
    }
    else if(root->value>value){
        if(root->left==nullptr)return;
        search(root->left,value);
        //text+=root->value+", ";
    }
    else{
        if(root->right==nullptr)return;
        search(root->right,value);
        //text+=root->value+", ";
    }
}
int main() {
    tree drzewo;
    for(int i=0;i<1000;i++){
        push(&drzewo,std::rand()%(100));
    }
    //cout<<search(&drzewo,13)<<endl;
    print(&drzewo);
    std::string *text=new std::string();
    road(&drzewo,20,text);
    cout<<"maslo, "<<*text<<endl;
    return 0;
}