#include <iostream>

int leng(const char* str){
    int n=0;
    while(*(str+n)){
        n++;
    }
    return n;
}
char* copy(const char* str) {
    unsigned int len = leng(str) + 1;
    char* cp = new char[len];
    for (unsigned int i = 0; i < len; i++) {
        cp[i] = str[i];
    }
    return cp;
}

bool palindrom(const char* str){
    int n=leng(str)-1;
    int i=0;
    while(i<=n){
        //std::cout<<*(str+i)<<" "<<*(str+n)<<std::endl;
        if(*(str+i)!=*(str+n))return false;
        i++;
        n--;
    }
    return true;
}
int counter_slow(const char* str){
    int n=1;
    for(int i=0;i<leng(str);i++){
        if(*(str+i)==' ')n++;
    }
    return n;
}
void reverse(char* str) {
    int len = leng(str) - 1;
    //std::cout << len << std::endl;
    for (int i = 0; i < len; i++, len--) {
        char tmp = *(str + i);
        //std::cout << tmp << std::endl;
        *(str + i) = *(str + len);
        *(str + len) = tmp;
    }
}


int main() {

    const char* a="maslo";

    //std::cout << a << std::endl<<leng(a)<<std::endl<<&a<<std::endl;
    char* b=copy(a);
    a="robor";
    std::cout<<"dlugosc "<<leng(a)<<std::endl;
    //std::cout << a << std::endl<<leng(a)<<std::endl<<&a<<std::endl;
    //std::cout << b << std::endl<<leng(b)<<std::endl<<&b<<std::endl;

    std::cout <<"pali "<< palindrom(a)<<std::endl;
    std::cout <<"counter "<< counter_slow(a)<<std::endl;

    char* c="hello world world";
    std::cout <<"counter "<< counter_slow(c)<<std::endl;
    char* cp = copy(c);
    reverse(cp);
    char* d="maselko";
    std::cout <<cp<<std::endl;
    reverse(d);
    std::cout <<d<<std::endl;

    return 0;
}