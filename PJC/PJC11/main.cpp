#include <memory>

#include <iostream>
#include <string>

class Osoba{
    std::string name;
    int age;
public:
    void show(){
        std::cout<<"Imie: "<<name<<", wiek: "<<age<<std::endl;
    }
    Osoba(std::string name, int age):name(name),age(age){};
    ~Osoba(){
        std::cout<<"delet this"<<std::endl;
    }
};


struct two_direction{
    std::string name;
    std::shared_ptr<two_direction> next;
    std::shared_ptr<two_direction> dis;
    std::weak_ptr<two_direction> prev;
public:
    two_direction(std::string name):name(name),next(nullptr),dis(this),prev(next){};
    void add(std::string name){
        std::shared_ptr<two_direction> tmp=dis;
        while(tmp->next!=nullptr){
            tmp=tmp->next;
        }
        tmp->next= std::make_shared<two_direction>(name);
        tmp->next->prev=std::weak_ptr<two_direction>(tmp);
    }
    ~two_direction(){
        std::cout<<"delet "<<name<<std::endl;
    }
};

int main() {
    //std::unique_ptr<Osoba> raz(new Osoba("Michal",15));
    //raz->show();


    std::unique_ptr<two_direction> list(new two_direction("a"));
    list->add("b");
    list->add("c");
    list->add("d");
    list->add("e");

    return 0;
}