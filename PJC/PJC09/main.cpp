#include <iostream>
#include "Samochod.h"
#include "Lodz.h"
#include "Amfibia.h"

int main() {
    auto * skoda=new Samochod();
    auto * lodka=new Lodz();
    auto * cos=new Amfibia();

    CzescPojazdu* kolo=new CzescPojazdu("Kolo",234);
    CzescPojazdu* drzwi=new CzescPojazdu("Drzwi",5234);
    CzescPojazdu* kierownica=new CzescPojazdu("Kierownica",163);
    CzescPojazdu* silnik=new CzescPojazdu("Silnik",2622);
    CzescPojazdu* skrzynia_biegow=new CzescPojazdu("Skrzynia_biegow",524);
    CzescPojazdu* harpun=new CzescPojazdu("Harpun",2432);

    skoda->jedz();
    skoda->zamontujCzesc(kolo);
    skoda->jedz();
    skoda->zamontujCzesc(drzwi);
    skoda->zamontujCzesc(kierownica);
    skoda->zamontujCzesc(silnik);
    skoda->zamontujCzesc(skrzynia_biegow);
    skoda->jedz();
    skoda->zatrzymaj();
    skoda->OdmontujCzesc(silnik);
    skoda->jedz();

    std::cout<<std::endl<<std::endl;

    lodka->plyn();
    lodka->zamontujCzesc(kierownica);
    lodka->zamontujCzesc(harpun);
    lodka->plyn();
    lodka->zatrzymaj();

    std::cout<<std::endl<<std::endl;

    cos->rusz("woda");
    cos->rusz("droga");
    cos->zatrzymaj();
    cos->zamontujCzesc(silnik);
    cos->rusz("woda");
    cos->rusz("droga");
    cos->zatrzymaj();
    cos->zamontujCzesc(harpun);
    cos->rusz("woda");
    cos->rusz("droga");
    cos->zatrzymaj();
    cos->OdmontujCzesc(silnik);


    return 0;
}