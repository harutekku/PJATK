-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2019-03-29 13:56:10.473

-- tables
-- Table: Autor
CREATE TABLE Autor (
    idAutora integer  NOT NULL,
    Imie varchar2(20)  NOT NULL,
    Nazwisko varchar2(30)  NOT NULL,
    CONSTRAINT Autor_pk PRIMARY KEY (idAutora)
) ;

-- Table: Czytelnik
CREATE TABLE Czytelnik (
    idCzytelnika integer  NOT NULL,
    Imie varchar2(20)  NOT NULL,
    Nazwisko varchar2(30)  NOT NULL,
    PESEL char(11)  NOT NULL,
    AdresZamieszkania varchar2(30)  NOT NULL,
    CONSTRAINT Czytelnik_pk PRIMARY KEY (idCzytelnika)
) ;

-- Table: Egzemplarz
CREATE TABLE Egzemplarz (
    idEgzemplarza integer  NOT NULL,
    Wydanie_idWydania integer  NOT NULL,
    rokZakupienia integer  NOT NULL,
    CONSTRAINT Egzemplarz_pk PRIMARY KEY (idEgzemplarza)
) ;

-- Table: Kategoria
CREATE TABLE Kategoria (
    idKategorii integer  NOT NULL,
    nazwaKategorii varchar2(15)  NOT NULL,
    CONSTRAINT Kategoria_pk PRIMARY KEY (idKategorii)
) ;

-- Table: Ksiazka
CREATE TABLE Ksiazka (
    idKsiazki integer  NOT NULL,
    tytulKsiazki varchar2(20)  NOT NULL,
    Autor_idAutora integer  NOT NULL,
    Kategoria_idKategorii integer  NOT NULL,
    opisKsiazki varchar2(200)  NOT NULL,
    CONSTRAINT Ksiazka_pk PRIMARY KEY (idKsiazki)
) ;

-- Table: Pracownik
CREATE TABLE Pracownik (
    idPracownika integer  NOT NULL,
    imie varchar2(20)  NOT NULL,
    nazwisko varchar2(30)  NOT NULL,
    Rola_idRoli integer  NOT NULL,
    dodatekDoPensji integer  NOT NULL,
    CONSTRAINT Pracownik_pk PRIMARY KEY (idPracownika)
) ;

-- Table: Rola
CREATE TABLE Rola (
    idRoli integer  NOT NULL,
    nazwaRoli varchar2(15)  NOT NULL,
    podstawowaPensja integer  NOT NULL,
    obowiazki varchar2(100)  NOT NULL,
    CONSTRAINT Rola_pk PRIMARY KEY (idRoli)
) ;

-- Table: Wydanie
CREATE TABLE Wydanie (
    idWydania integer  NOT NULL,
    numerISBN integer  NOT NULL,
    Wydawnictwo_idWydawnictwa integer  NOT NULL,
    Ksiazka_idKsiazki integer  NOT NULL,
    dataWydania date  NOT NULL,
    CONSTRAINT Wydanie_pk PRIMARY KEY (idWydania)
) ;

-- Table: Wydawnictwo
CREATE TABLE Wydawnictwo (
    idWydawnictwa integer  NOT NULL,
    nazwaWydawnictwa varchar2(50)  NOT NULL,
    CONSTRAINT Wydawnictwo_pk PRIMARY KEY (idWydawnictwa)
) ;

-- Table: Wypozyczenie
CREATE TABLE Wypozyczenie (
    idWypozyczenia integer  NOT NULL,
    Egzemplarz_idEgzemplarza integer  NOT NULL,
    Czytelnik_idCzytelnika integer  NOT NULL,
    Pracownik_idPracownika integer  NOT NULL,
    dataWypozyczenia date  NOT NULL,
    dataZwrotu date  NULL,
    CONSTRAINT Wypozyczenie_pk PRIMARY KEY (idWypozyczenia)
) ;

-- foreign keys
-- Reference: Egzemplarz_Wydanie (table: Egzemplarz)
ALTER TABLE Egzemplarz ADD CONSTRAINT Egzemplarz_Wydanie
    FOREIGN KEY (Wydanie_idWydania)
    REFERENCES Wydanie (idWydania);

-- Reference: Ksiazka_Autor (table: Ksiazka)
ALTER TABLE Ksiazka ADD CONSTRAINT Ksiazka_Autor
    FOREIGN KEY (Autor_idAutora)
    REFERENCES Autor (idAutora);

-- Reference: Ksiazka_Kategoria (table: Ksiazka)
ALTER TABLE Ksiazka ADD CONSTRAINT Ksiazka_Kategoria
    FOREIGN KEY (Kategoria_idKategorii)
    REFERENCES Kategoria (idKategorii);

-- Reference: Pracownik_Rola (table: Pracownik)
ALTER TABLE Pracownik ADD CONSTRAINT Pracownik_Rola
    FOREIGN KEY (Rola_idRoli)
    REFERENCES Rola (idRoli);

-- Reference: Wydanie_Ksiazka (table: Wydanie)
ALTER TABLE Wydanie ADD CONSTRAINT Wydanie_Ksiazka
    FOREIGN KEY (Ksiazka_idKsiazki)
    REFERENCES Ksiazka (idKsiazki);

-- Reference: Wydanie_Wydawnictwo (table: Wydanie)
ALTER TABLE Wydanie ADD CONSTRAINT Wydanie_Wydawnictwo
    FOREIGN KEY (Wydawnictwo_idWydawnictwa)
    REFERENCES Wydawnictwo (idWydawnictwa);

-- Reference: Wypozyczenie_Czytelnik (table: Wypozyczenie)
ALTER TABLE Wypozyczenie ADD CONSTRAINT Wypozyczenie_Czytelnik
    FOREIGN KEY (Czytelnik_idCzytelnika)
    REFERENCES Czytelnik (idCzytelnika);

-- Reference: Wypozyczenie_Egzemplarz (table: Wypozyczenie)
ALTER TABLE Wypozyczenie ADD CONSTRAINT Wypozyczenie_Egzemplarz
    FOREIGN KEY (Egzemplarz_idEgzemplarza)
    REFERENCES Egzemplarz (idEgzemplarza);

-- Reference: Wypozyczenie_Pracownik (table: Wypozyczenie)
ALTER TABLE Wypozyczenie ADD CONSTRAINT Wypozyczenie_Pracownik
    FOREIGN KEY (Pracownik_idPracownika)
    REFERENCES Pracownik (idPracownika);

-- End of file.




select * from dept;
insert into dept (deptno, dname, loc) values (50,'LOGISTIC','NEW ORLEAN');
update dept set loc = 'DETROIT' where deptno = 50;
delete from dept where deptno = 50;