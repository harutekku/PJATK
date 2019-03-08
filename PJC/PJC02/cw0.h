//
// Created by s18688 on 2019-03-08.
//

#ifndef PJC02_CW0_H
#define PJC02_CW0_H

#include <iostream>
#include <string>

enum szef{student,inzynier,magister,doktor};
struct punkt_3d{
    int x;
    int y;
    int z;
};
struct student{
    int numer_s;
    std::string name;
    szef przelozony;
};
struct::student create(int num, std::string nam, szef sze);
void to_string(struct::student);

#endif //PJC02_CW0_H
