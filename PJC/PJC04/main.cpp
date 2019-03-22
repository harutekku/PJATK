#include <iostream>
using std::cout,std::cin,std::endl;

struct house
{
    std::string city;
    std::string street;
    int number;
};

void modify(int x){
    x++;
    cout<<x<<endl;
}
void modify(int* x){
    (*x)++;
    cout<<*x<<endl;
}
int main() {
    //int size;
    //cin >> size;
    //int* tab=new int [size];
    //cout<<tab<<endl<<endl<<*tab<<endl;
    //cout<<size<<endl<<&size<<endl;


    int x = 10;

    cout << "adres zmiennej x: " << &x << endl;
    cout << "wyświetlenie wartości zmiennej x za pomocą jej adresu: " << *(&x) << endl;


    int y=5;

    int* px=&x;

    *px+=3;
    cout<<x<<endl;
    cout<<*px<<endl;

    px=&y;
    //px++;
    (*px)++;
    cout<<*px<<endl<<y<<endl;

    x=10;
    modify(x);
    cout<<x<<endl;
    modify(&x);
    cout<<x<<endl;

    {
        int size =5;
        //cout << "Podaj rozmiar tablicy: ";
        //cin >> size;

        int *arr = new int[size];

        for (int i = 0; i < size; ++i)
        {
            *(arr+i) = i+100;
        }
        cout<<*arr+1<<endl;

        delete[] arr;
    }


    {
        house *h = new house();

        //  *h.city = "Warszawa";
        (*h).street = "Koszykowa";
        h->number = 86;

        delete h;
    }

    return 0;
}