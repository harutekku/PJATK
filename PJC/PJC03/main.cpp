#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>
#include <vector>
#include <string>
#define rozmiar 200


struct punkt{
    int x,y,z;
};

punkt nowy_punkt(){
    punkt a;
    a.x=rand();
    a.y=rand();
    a.z=rand();
    return a;
}

int main() {

    int wrt[rozmiar];
    //std::cout<<wrt[0]<<std::endl;
    //jakies liczby wyskakuja XD
    for(int i=0;i<rozmiar;i++){
        wrt[i]=i;
    }
    //std::cout<<wrt[rozmiar]<<std::endl;
    //tak samo brum brum


    std::srand(std::time(nullptr));
    std::cout<<"[0, " << RAND_MAX << "]: " << std::rand() << std::endl;


    int n;
    std::cout<<"Podaj liczbe"<<std::endl;
    std::cin>>n;
    punkt* lista=new punkt[n];
    for(int i=0;i<n;i++){
        lista[i].x=rand();
        lista[i].y=rand();
        lista[i].z=rand();
    }

    std::fstream file;
    file.open("results.csv", std::ios::out);
    if(!file.good()){
        std::cout<<"Error"<<std::endl;
        return 1;
    }
    for(int i=0;i<n;i++){
        file<<lista[i].x<<" "<<lista[i].y<<" "<<lista[i].z<<std::endl;
    }

    file.close();
    //std::cout<<"Success"<<std::endl;



    file.open("results.csv", std::ios::in);
    std::vector<int> dane[3];
    int tmp;
    while(file>>tmp){
        dane[0].push_back(tmp);
        for(int i=1;i<3;i++){
            file>>tmp;
            dane[i].push_back(tmp);
        }
    }
    for(int i=0;i<dane[0].size();i++){
        for(int j=0;j<3;j++){
            std::cout<<dane[j][i]<<" ";
        }
        std::cout<<std::endl;
    }


    return 0;
}