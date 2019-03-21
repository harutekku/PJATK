#include <iostream>
#include <fstream>
#include <string>
using std::cout;
using std::cin;
using std::endl;
using std::string;

struct student {
	int id;
	string imie;
	string nazwisko;
	double ocena_z_pjc;
};

student baza[100];
int index = 0;

void wyswietl_baze(){
    for(int i=0;i<=index;i++){
        cout<<baza[i].id<<" "<<baza[i].imie<<" "<<baza[i].nazwisko<<" "<<baza[i].ocena_z_pjc<<endl;
    }
}

bool odczytaj_baze(){
    std::fstream file;
    file.open("baza.csv",std::ios::in);
    if(!file.good()){
        cout<<"Error"<<endl;
        return 0;
    }
    while(true){
        int a;string b, c; double d;
        file>>a>>b>>c>>d;
        if(!file.fail()){
            baza[index].id=a;
            baza[index].imie=b;
            baza[index].nazwisko=c;
            baza[index].ocena_z_pjc=d;
            index++;
        }
        else{
            index--;
            break;
        }
    }
    wyswietl_baze();
    return 1;
}

void zapisz_zmiany(){
    std::fstream file;
    file.open("baza.csv",std::ios::out|std::ios::trunc);
    for(int i=0;i<=index;i++){
        file<<baza[i].id<<" "<<baza[i].imie<<" "<<baza[i].nazwisko<<" "<<baza[i].ocena_z_pjc<<endl;
    }
    file.close();
}

void wstaw_rekord()
{
    if (index == 99) {
        cout << "Brak miejsca na studentow. Postaw nowy budynek.";
    }
    else{
        index++;
        baza[index].id = index;
        cout << "Podaj imie:\t";
        cin >> baza[index].imie;
        cout << "Podaj nazwisko:\t";
        cin >> baza[index].nazwisko;
        cout << "Podaj ocene z PJC:\t";
        cin >> baza[index].ocena_z_pjc;
        std::fstream file;
        file.open("baza.csv",std::ios::out|std::ios::app);
        file<<baza[index].id<<" "<<baza[index].imie<<" "<<baza[index].nazwisko<<" "<<baza[index].ocena_z_pjc;
        file.close();
    }
}
void usun_rekord(){
    int id;
    cout<<endl<<"Ktory rekord chcesz usunac?"<<endl;
    cin>>id;
    for(int i=id;i<index;i++){
        baza[i].imie=baza[i+1].imie;
        baza[i].nazwisko=baza[i+1].nazwisko;
        baza[i].ocena_z_pjc=baza[i+1].ocena_z_pjc;
    }
    baza[index].id=0;
    baza[index].imie="";
    baza[index].nazwisko="";
    baza[index].ocena_z_pjc=0;
    index--;
    zapisz_zmiany();
}
void nadpisz_rekord(){
    int id;
    cout<<endl<<"Ktory rekord chcesz nadpisac?"<<endl;
    cin>>id;
    cout << "Podaj imie:\t";
    cin >> baza[id].imie;
    cout << "Podaj nazwisko:\t";
    cin >> baza[id].nazwisko;
    cout << "Podaj ocene z PJC:\t";
    cin >> baza[id].ocena_z_pjc;
    zapisz_zmiany();
}
void odczytaj_rekord(){
    int id;
    cout<<endl<<"Ktory rekord chcesz odczytac?"<<endl;
    cin>>id;
    cout<<baza[id].id<<" "<<baza[id].imie<<" "<<baza[id].nazwisko<<" "<<baza[id].ocena_z_pjc<<endl;
}

int main()
{
    if(odczytaj_baze()==0)return 1;
    //cout<<index<<endl;
	while (true)
	{
		cout <<endl<<endl<< "Menu glowne" << endl;
		cout << "[0] - wstaw rekord" << endl;
        cout << "[1] - usun rekord" << endl;
        cout << "[2] - nadpisac rekord" << endl;
        cout << "[3] - odczytac rekord" << endl;
		cout << "[wszystko inne] - koniec programu" << endl;
		char akcja;
		cin >> akcja;
		switch (akcja) {
            case '0':
                wstaw_rekord();
                wyswietl_baze();
                break;
            case '1':
                usun_rekord();
                wyswietl_baze();
                break;
            case '2':
                nadpisz_rekord();
                wyswietl_baze();
                break;
            case '3':
                odczytaj_rekord();
                break;
		    default:
			    return 0;
		}
	}
}