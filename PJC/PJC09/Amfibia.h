//
// Created by s18688 on 2019-05-10.
//

#ifndef PJC09_AMFIBIA_H
#define PJC09_AMFIBIA_H


#include "Lodz.h"
#include "Samochod.h"

class Amfibia : public Samochod, public Lodz{
public:
    Amfibia();
    void rusz(std::string);
    void zatrzymaj();
    void zamontujCzesc(CzescPojazdu*);
    void OdmontujCzesc(CzescPojazdu*);
};


#endif //PJC09_AMFIBIA_H
