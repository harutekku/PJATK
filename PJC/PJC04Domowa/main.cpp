#include <iostream>
#include <string>
using std::cin,std::cout,std::endl;

struct list{
    list* next= nullptr;
    list* prev= nullptr;
    std::string slowo;
};

void push(list* root, std::string value){
    list* tmp=root;
    while(tmp->next!=nullptr){
        tmp=tmp->next;
    }
    list* last=new list();
    last->slowo=value;
    last->prev=tmp;
    last->next=nullptr;
    tmp->next=last;
}
void show(list* root,int index){
    list* tmp=root;
    while(index>0){
        if(tmp->next=nullptr){
            cout<<"Nie ma takiego indeksu"<<endl;
            return;
        }
        tmp=tmp->next;
        index--;
    }
    std::string slowo=tmp->slowo;
    cout<<slowo<<endl;
}
int main() {
    list lista;
    push(&lista,"maslo");
    push(&lista,"haslo");
    push(&lista,"trzy");
    push(&lista,"cztery");
    show(&lista,1);
    return 0;
}