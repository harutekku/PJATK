//
// Created by s18688 on 2019-03-08.
//

#include "cw0.h"

struct::student create(int num, std::string nam, szef sze){
    struct::student a;
    a.numer_s=num;
    a.name=nam;
    a.przelozony=sze;
    return a;
}

void to_string(struct::student a){
    std::cout<<"Name: "<<a.name<<" Number: "<<a.numer_s<<" Boss rank: "<<a.przelozony<<std::endl;
}