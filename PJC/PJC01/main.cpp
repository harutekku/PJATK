#include <iostream>
#include <cmath>

using std::cout, std::endl,std::cin;

namespace witanko{
    void hello(){
        std::cout << "Hello, World!\r\nma" << std::endl;
    }
}

void kwadratowe(){
    double a, b, c;
    cout<<"Podaj a"<<endl;
    cin>>a;
    cout<<"Podaj b"<<endl;
    cin>>b;
    cout<<"Podaj c"<<endl;
    cin>>c;
    double delta=b*b-4*a*c;
    delta=std::sqrt(delta);
    if(delta==0){
        std::cout<<"Rozwiazanie to "<<(b*-1)/(2*a)<<std::endl;
    }
    else if (delta>0){
        std::cout<<"Rozwiazanie to "<<(b*-1-delta)/(2*a)<<" i "<<(b*-1+delta)/(2*a)<<std::endl;
    }
    else{
        std::cout<<"Nie ma rozwiazan"<<std::endl;
    }

}

int main() {
    witanko::hello();
    kwadratowe();
    return 0;
}