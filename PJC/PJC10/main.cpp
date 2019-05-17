#include <iostream>
#include <string>
#include <vector>


class Country{
    int population;
    std::string name;
public:
    Country(std::string name, int population):name(name),population(population){};
    // ++A
    Country& operator++()
    {
        ++population;
        // todo
        return *this;
    }

    // A++
    Country operator++(int)
    {
        ++(*this);
        return *this;
    }
    int get_population(){
        return population;
    }

    friend std::ostream& operator<<(std::ostream& strm, const Country& country){
        return strm << "Country(" << country.name <<" "<<country.population<< ")";
    }
};


class Grupa;

class Student{
    int id;
    std::vector<Grupa*> grupy;
public:
    Student(int id):id(id){};
    void dodajDoGrupy(Grupa* grupa){
        grupy.push_back(grupa);
    }
    ~Student();
};

class Grupa{
    std::vector<Student*> studenci;
    std::string nazwa;
public:
    Grupa(std::string nazwa):nazwa(nazwa){

    };
    ~Grupa(){

    };
    void dodajStudenta(Student* student){
        studenci.push_back(student);
        student->dodajDoGrupy(this);
    }
    void removeStudent(Student* student){
        std::vector<Student*>::iterator position = std::find(studenci.begin(), studenci.end(), student);
        if (position != studenci.end()) {
            std::cout << this->nazwa << ": Usuwanie dowiazania grupy u studenta" << std::endl;
            studenci.erase(position);

            if (!studenci.size()) {
                std::cout << this->nazwa << ": Usuwanie grupy" << std::endl;
                delete this;
            }
            return;
        }
    }
};

Student::~Student() {
    std::cout << "Student zaczyna sie usuwac" << std::endl;
    for (std::vector<Grupa*>::iterator it = grupy.begin(); it != grupy.end(); ++it)
    {
        (*it)->removeStudent(this);
    }
}


int main(){
    Country kraj("polsza",1000000);
    kraj++;
    std::cout << "Populacja wzrosła o 1. Wynosi: " << kraj.get_population() << std::endl;
    ++kraj;
    std::cout << "Populacja wzrosła o 1. Wynosi: " << kraj.get_population() << std::endl;

    std::cout<<kraj<<std::endl;



    Student* s0 = new Student(0);
    Grupa* sg0 = new Grupa("Grupa A");
    Grupa* sg1 = new Grupa("Grupa B");
    sg0->dodajStudenta(s0);
    sg1->dodajStudenta(s0);
    delete s0;
    return 0;
}