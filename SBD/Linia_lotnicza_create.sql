-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2019-10-25 08:37:36.186

-- tables
-- Table: Piloci
CREATE TABLE Piloci (
    idPilota integer  NOT NULL,
    Imie varchar2(20)  NOT NULL,
    Nazwisko varchar2(30)  NOT NULL,
    PESEL varchar2(11)  NOT NULL,
    CONSTRAINT Piloci_pk PRIMARY KEY (idPilota)
) ;

-- Table: Rejs
CREATE TABLE Rejs (
    idRejsu varchar2(20)  NOT NULL,
    Samoloty_idSamolotu integer  NOT NULL,
    dataLotu timestamp  NOT NULL,
    idPilotaKapitana integer  NOT NULL,
    uprawnieniaKapitana integer  NOT NULL,
    idPilotaDrugiego integer  NOT NULL,
    uprawnieniaDrugiegoPilota integer  NOT NULL,
    CONSTRAINT Rejs_pk PRIMARY KEY (idRejsu)
) ;

-- Table: Samoloty
CREATE TABLE Samoloty (
    idSamolotu integer  NOT NULL,
    numerSeryjny integer  NOT NULL,
    dataZakupienia date  NOT NULL,
    Typ_samolotu_idTypuSamolotu integer  NOT NULL,
    CONSTRAINT Samoloty_pk PRIMARY KEY (idSamolotu)
) ;

-- Table: Typ_samolotu
CREATE TABLE Typ_samolotu (
    idTypuSamolotu integer  NOT NULL,
    nazwaTypu varchar2(20)  NOT NULL,
    CONSTRAINT Typ_samolotu_pk PRIMARY KEY (idTypuSamolotu)
) ;

-- Table: Uprawnienia_drugiego_pilota
CREATE TABLE Uprawnienia_drugiego_pilota (
    Piloci_idPilota integer  NOT NULL,
    Typ_samolotu_idTypuSamolotu integer  NOT NULL,
    uprawnienia varchar2(50)  NOT NULL,
    CONSTRAINT Uprawnienia_drugiego_pilota_pk PRIMARY KEY (Piloci_idPilota,Typ_samolotu_idTypuSamolotu)
) ;

-- Table: Uprawnienia_kapitanskie
CREATE TABLE Uprawnienia_kapitanskie (
    Piloci_idPilota integer  NOT NULL,
    Typ_samolotu_idTypuSamolotu integer  NOT NULL,
    uprawnienia varchar2(50)  NOT NULL,
    CONSTRAINT Uprawnienia_kapitanskie_pk PRIMARY KEY (Piloci_idPilota,Typ_samolotu_idTypuSamolotu)
) ;

-- foreign keys
-- Reference: Rejs_Samoloty (table: Rejs)
ALTER TABLE Rejs ADD CONSTRAINT Rejs_Samoloty
    FOREIGN KEY (Samoloty_idSamolotu)
    REFERENCES Samoloty (idSamolotu);

-- Reference: Rejs_Uprawnienia_drugiego_pilota (table: Rejs)
ALTER TABLE Rejs ADD CONSTRAINT Rejs_Uprawnienia_drugiego_pilota
    FOREIGN KEY (idPilotaDrugiego,uprawnieniaDrugiegoPilota)
    REFERENCES Uprawnienia_drugiego_pilota (Piloci_idPilota,Typ_samolotu_idTypuSamolotu);

-- Reference: Rejs_Uprawnienia_kapitanskie (table: Rejs)
ALTER TABLE Rejs ADD CONSTRAINT Rejs_Uprawnienia_kapitanskie
    FOREIGN KEY (idPilotaKapitana,uprawnieniaKapitana)
    REFERENCES Uprawnienia_kapitanskie (Piloci_idPilota,Typ_samolotu_idTypuSamolotu);

-- Reference: Samoloty_Typ_samolotu (table: Samoloty)
ALTER TABLE Samoloty ADD CONSTRAINT Samoloty_Typ_samolotu
    FOREIGN KEY (Typ_samolotu_idTypuSamolotu)
    REFERENCES Typ_samolotu (idTypuSamolotu);

-- Reference: Uprawnienia_drugiego_pilota_Piloci (table: Uprawnienia_drugiego_pilota)
ALTER TABLE Uprawnienia_drugiego_pilota ADD CONSTRAINT Uprawnienia_drugiego_pilota_Piloci
    FOREIGN KEY (Piloci_idPilota)
    REFERENCES Piloci (idPilota);

-- Reference: Uprawnienia_drugiego_pilota_Typ_samolotu (table: Uprawnienia_drugiego_pilota)
ALTER TABLE Uprawnienia_drugiego_pilota ADD CONSTRAINT Uprawnienia_drugiego_pilota_Typ_samolotu
    FOREIGN KEY (Typ_samolotu_idTypuSamolotu)
    REFERENCES Typ_samolotu (idTypuSamolotu);

-- Reference: Uprawnienia_pilotow_Piloci (table: Uprawnienia_kapitanskie)
ALTER TABLE Uprawnienia_kapitanskie ADD CONSTRAINT Uprawnienia_pilotow_Piloci
    FOREIGN KEY (Piloci_idPilota)
    REFERENCES Piloci (idPilota);

-- Reference: Uprawnienia_pilotow_Typ_samolotu (table: Uprawnienia_kapitanskie)
ALTER TABLE Uprawnienia_kapitanskie ADD CONSTRAINT Uprawnienia_pilotow_Typ_samolotu
    FOREIGN KEY (Typ_samolotu_idTypuSamolotu)
    REFERENCES Typ_samolotu (idTypuSamolotu);

-- End of file.

