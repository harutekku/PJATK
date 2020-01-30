-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-01-30 20:00:38.09

-- tables
-- Table: Autor
CREATE TABLE Autor (
    idAutora int  NOT NULL,
    Imie varchar(20)  NOT NULL,
    Nazwisko varchar(30)  NOT NULL,
    CONSTRAINT Autor_pk PRIMARY KEY (idAutora)
);

-- Table: Czytelnik
CREATE TABLE Czytelnik (
    idCzytelnika int  NOT NULL,
    Imie varchar(20)  NOT NULL,
    Nazwisko varchar(30)  NOT NULL,
    PESEL varchar(11)  NOT NULL,
    AdresZamieszkania varchar(30)  NOT NULL,
    CONSTRAINT Czytelnik_pk PRIMARY KEY  (idCzytelnika)
);

-- Table: Egzemplarz
CREATE TABLE Egzemplarz (
    idEgzemplarza int  NOT NULL,
    Wydanie_idWydania int  NOT NULL,
    rokZakupienia int  NOT NULL,
    CONSTRAINT Egzemplarz_pk PRIMARY KEY  (idEgzemplarza)
);

-- Table: Kategoria
CREATE TABLE Kategoria (
    idKategorii int  NOT NULL,
    nazwaKategorii varchar(15)  NOT NULL,
    CONSTRAINT Kategoria_pk PRIMARY KEY  (idKategorii)
);

-- Table: Ksiazka
CREATE TABLE Ksiazka (
    idKsiazki int  NOT NULL,
    tytulKsiazki varchar(20)  NOT NULL,
    Autor_idAutora int  NOT NULL,
    Kategoria_idKategorii int  NOT NULL,
    opisKsiazki varchar(200)  NOT NULL,
    CONSTRAINT Ksiazka_pk PRIMARY KEY  (idKsiazki)
);

-- Table: Pracownik
CREATE TABLE Pracownik (
    idPracownika int  NOT NULL,
    imie varchar(20)  NOT NULL,
    nazwisko varchar(30)  NOT NULL,
    Rola_idRoli int  NOT NULL,
    dodatekDoPensji int  NOT NULL,
    CONSTRAINT Pracownik_pk PRIMARY KEY  (idPracownika)
);

-- Table: Rola
CREATE TABLE Rola (
    idRoli int  NOT NULL,
    nazwaRoli varchar(15)  NOT NULL,
    podstawowaPensja int  NOT NULL,
    obowiazki varchar(100)  NOT NULL,
    CONSTRAINT Rola_pk PRIMARY KEY  (idRoli)
);

-- Table: Wydanie
CREATE TABLE Wydanie (
    idWydania int  NOT NULL,
    numerISBN varchar(13)  NOT NULL,
    Wydawnictwo_idWydawnictwa int  NOT NULL,
    Ksiazka_idKsiazki int  NOT NULL,
    dataWydania date  NOT NULL,
    CONSTRAINT Wydanie_pk PRIMARY KEY  (idWydania)
);

-- Table: Wydawnictwo
CREATE TABLE Wydawnictwo (
    idWydawnictwa int  NOT NULL,
    nazwaWydawnictwa varchar(50)  NOT NULL,
    CONSTRAINT Wydawnictwo_pk PRIMARY KEY  (idWydawnictwa)
);

-- Table: Wypozyczenie
CREATE TABLE Wypozyczenie (
    idWypozyczenia int  NOT NULL,
    Egzemplarz_idEgzemplarza int  NOT NULL,
    Czytelnik_idCzytelnika int  NOT NULL,
    Pracownik_idPracownika int  NOT NULL,
    dataWypozyczenia date  NOT NULL,
    dataZwrotu date  NULL,
    CONSTRAINT Wypozyczenie_pk PRIMARY KEY  (idWypozyczenia)
);



-- trzeba puszczac oddzielenie



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
