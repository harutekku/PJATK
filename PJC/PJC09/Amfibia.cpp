//
// Created by s18688 on 2019-05-10.
//

#include <iostream>
#include "Amfibia.h"

Amfibia::Amfibia() {
    Lodz::czy_porusza_sie=false;
    Samochod::czy_porusza_sie=false;
}

void Amfibia::zamontujCzesc(CzescPojazdu * czesc) {
    Samochod::lista_czesci.push_back(czesc);
    Lodz::lista_czesci.push_back(czesc);
    std::cout<<"Zamontowano "<<czesc->getName()<<std::endl;
}

void Amfibia::OdmontujCzesc(CzescPojazdu * a) {
    bool czy=false;
    for(int i=0;i<Lodz::lista_czesci.size();i++){
        if(Lodz::lista_czesci[i]==a){
            Lodz::lista_czesci.erase(Lodz::lista_czesci.begin()+i);
            Samochod::lista_czesci.erase(Samochod::lista_czesci.begin()+i);
            czy=true;
            std::cout<<"Odmontowano "<<a->getName()<<std::endl;
        }
    }

    if(!czy)std::cout<<"Nie ma takiej czesci do zdemontowania"<<std::endl;
}

void Amfibia::rusz(std::string teren) {
    if(Lodz::czy_porusza_sie){
        std::cout<<"Pojazd juz plynie"<<std::endl;
    }
    else if(Samochod::czy_porusza_sie){
        std::cout<<"Pojazd juz jedzie"<<std::endl;
    }
    else{
        if(teren=="woda"){
            bool harpun=false;
            for (auto &i : Lodz::lista_czesci) {
                if(i->getName()=="Harpun")harpun=true;
            }
            if(harpun){
                Lodz::czy_porusza_sie=!Lodz::czy_porusza_sie;
                std::cout<<"Wyplynieto"<<std::endl;
            }
            else std::cout<<"Lodz nie ma harpuna"<<std::endl;
        }
        else if(teren=="droga"){
            bool silnik=false;
            for (auto &i : Samochod::lista_czesci) {
                if(i->getName()=="Silnik")silnik=true;
            }
            if(silnik){
                Samochod::czy_porusza_sie=!Samochod::czy_porusza_sie;
                std::cout<<"Ruszono"<<std::endl;
            }
            else std::cout<<"Pojazd nie ma silnika"<<std::endl;
        }
    }

}

void Amfibia::zatrzymaj() {
    if(Lodz::czy_porusza_sie||Samochod::czy_porusza_sie){
        Lodz::czy_porusza_sie=false;
        Samochod::czy_porusza_sie=false;
        std::cout<<"Zatrzymano"<<std::endl;
    }
    else{
        std::cout<<"Pojazd byl zatrzymany"<<std::endl;
    }
}
