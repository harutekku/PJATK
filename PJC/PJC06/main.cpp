#include <iostream>
#include <time.h>
using namespace std;


int wygrana(){
    cout<<"Wygrana"<<endl;
    return 1;
}
int przegrana(){
    cout<<"Przegrana"<<endl;
    return 0;
}
int (*traf())() {
    int x = (rand() % 6);
    if (x < 1)return wygrana;
    else return przegrana;
}
double f1(double x){
    return x*x*5*x-x*x*2+15*x-6;
}
double f2(double x){
    return x*x+4*x+6;
}
double f3(double x){
    return x*x+x*7+8;
}
double calkuj(double (*funkcja)(double),double a, double b, double krok){
    double wynik=0;
    for(double i=a;i<b;i+=krok){
        wynik+=(1.0/2*(funkcja(i)+funkcja(i+krok))*krok);
    }
    return wynik;
}


int main() {
    srand(time(NULL));
    int (*wsk)()=traf();
    traf()();
    wsk();

    cout<<calkuj(f3,0,50,0.1)<<endl;

    cout<<calkuj( [](double x){return x*x*x-3*x*x+7*x+12;} ,0,5,0.1)<<endl;

    return 0;
}