//
// Created by s18688 on 2019-05-10.
//
#include <vector>
#include "CzescPojazdu.h"

#ifndef PJC09_POJAZD_H
#define PJC09_POJAZD_H


class Pojazd {
protected:
    std::vector<CzescPojazdu*> lista_czesci;
    bool czy_porusza_sie;

public:
    void zamontujCzesc(CzescPojazdu*);
    void OdmontujCzesc(CzescPojazdu*);
    void zatrzymaj();
};


#endif //PJC09_POJAZD_H
