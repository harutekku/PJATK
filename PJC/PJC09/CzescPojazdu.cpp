//
// Created by s18688 on 2019-05-10.
//

#include "CzescPojazdu.h"

CzescPojazdu::CzescPojazdu(std::string nazwa, int numer) {
    nazwa_czesci=nazwa;
    numer_czesci=numer;
}

std::string CzescPojazdu::getName() {
    return nazwa_czesci;
}

int CzescPojazdu::getNumber() {
    return numer_czesci;
}

bool CzescPojazdu::operator==(const CzescPojazdu & prawa) {
    return nazwa_czesci == prawa.nazwa_czesci;
}
