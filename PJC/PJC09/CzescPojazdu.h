//
// Created by s18688 on 2019-05-10.
//

#ifndef PJC09_CZESCPOJAZDU_H
#define PJC09_CZESCPOJAZDU_H


#include <string>

class CzescPojazdu {
    std::string nazwa_czesci;
    int numer_czesci;
public:
    CzescPojazdu(std::string,int);
    std::string getName();
    int getNumber();
    bool operator==(const CzescPojazdu&);
};


#endif //PJC09_CZESCPOJAZDU_H
