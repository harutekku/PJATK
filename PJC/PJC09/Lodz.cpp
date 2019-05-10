//
// Created by s18688 on 2019-05-10.
//

#include <iostream>
#include "Lodz.h"

Lodz::Lodz(){
    czy_porusza_sie=false;
}

void Lodz::plyn() {
    if(czy_porusza_sie){
        std::cout<<"Lodz juz plynie"<<std::endl;
    }
    else {
        bool harpun=false;
        for (auto &i : lista_czesci) {
            if(i->getName()=="Harpun")harpun=true;
        }
        if(harpun){
            czy_porusza_sie=!czy_porusza_sie;
            std::cout<<"Wyplynieto"<<std::endl;
        }
        else std::cout<<"Lodz nie ma harpuna"<<std::endl;
    }
}
