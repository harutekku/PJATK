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
    //delete(tmp);
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
void road(tree* root, int value){
    if(root->value==value){
        cout<<"Znaleziono "<<root->value<<" w ";
    }
    else if(root->value>value){
        if(root->left==nullptr)return;
        road(root->left,value);
        cout<<"lewym "<<root->value<<" w ";
    }
    else{
        if(root->right==nullptr)return;
        road(root->right,value);
        cout<<"prawym "<<root->value<<" w ";
    }
}
int main() {
    tree drzewo;
    for(int i=0;i<100;i++){
        push(&drzewo,std::rand()%(100));
    }
    //cout<<search(&drzewo,13)<<endl;
    print(&drzewo);

    cout<<endl<<endl;
    road(&drzewo,20);
    return 0;
}