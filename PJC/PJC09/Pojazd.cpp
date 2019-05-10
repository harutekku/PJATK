//
// Created by s18688 on 2019-05-10.
//

#include <iostream>
#include "Pojazd.h"


void Pojazd::zamontujCzesc(CzescPojazdu* a) {
    lista_czesci.push_back(a);
    std::cout<<"Zamontowano "<<a->getName()<<std::endl;

}

void Pojazd::OdmontujCzesc(CzescPojazdu* a) {
    bool czy=false;
    for(int i=0;i<lista_czesci.size();i++){
        if(lista_czesci[i]==a){
            lista_czesci.erase(lista_czesci.begin()+i);
            czy=true;
            std::cout<<"Odmontowano "<<a->getName()<<std::endl;
        }
    }
    if(!czy)std::cout<<"Nie ma takiej czesci do zdemontowania"<<std::endl;
}

void Pojazd::zatrzymaj() {
    if(czy_porusza_sie){
        czy_porusza_sie=!czy_porusza_sie;
        std::cout<<"Zatrzymano"<<std::endl;
    }
    else{
        std::cout<<"Pojazd byl zatrzymany"<<std::endl;
    }

}
