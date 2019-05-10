//
// Created by s18688 on 2019-05-10.
//

#include <iostream>
#include "Samochod.h"

Samochod::Samochod() {
    czy_porusza_sie=false;
}

void Samochod::jedz() {
    if(czy_porusza_sie){
        std::cout<<"Pojazd juz jedzie"<<std::endl;
    }
    else {
        bool silnik=false;
        for (auto &i : lista_czesci) {
            if(i->getName()=="Silnik")silnik=true;
        }
        if(silnik){
            czy_porusza_sie=!czy_porusza_sie;
            std::cout<<"Ruszono"<<std::endl;
        }
        else std::cout<<"Pojazd nie ma silnika"<<std::endl;
    }
}
