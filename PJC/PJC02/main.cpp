#include <iostream>
#include <string>
#include "cw0.h"

/*

enum kolor{niebieski, czarny, czerwony};

struct dlugopis{
    double ilosc_tuszu;
    int rok_produkcji;
    kolor kolor_tuszu;
};
struct piornik{
    dlugopis jedyna_rzecz;
};

void bar();
void foo();
*/


union maslo{
    double waga;
    int data_produkcji;
};


namespace zad5{
    struct st1{
        std::string a,b,c;
    };
    struct st2{
        int a,b,c;
    };
    struct st3{
        st1 a;
        st2 b;
    };
    std::string to_string_st1(st1 a){
        return a.a + " " + a.b + " "  +a.c;
    }
    int sum_st2(st2 a){
        return a.a+a.b+a.c;
    }
    void to_string_st3(st3 a){
        std::cout<<zad5::to_string_st1(a.a)<<" "<<zad5::sum_st2(a.b)<<std::endl;
    }

}

int main() {
    /*dlugopis x;
    x.ilosc_tuszu=0.75;
    x.rok_produkcji=2018;
    x.kolor_tuszu=niebieski;
    piornik p;
    p.jedyna_rzecz.kolor_tuszu=czarny;

    foo();
    bar();*/

    punkt_3d a;
    a.x=2;a.y=3;a.z=4;

    struct::student stas;
    stas.przelozony=student;

    struct::student jas=create(1234,"Jas",doktor);
    to_string(jas);


    maslo kasia;
    kasia.waga=200;
    kasia.data_produkcji=2019;

    std::cout<<kasia.data_produkcji<<" "<<kasia.waga<<std::endl;

    return 0;
}

/*
void foo(){
    std::cout<<"foo"<<std::endl;
    bar();
}

void bar(){
    std::cout<<"bar"<<std::endl;
    foo();
}*/


