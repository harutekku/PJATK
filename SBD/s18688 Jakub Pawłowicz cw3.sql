use [2019SBD];

7.1
insert into osoba (nazwisko, imie, dataUrodzenia) values ('Modrep', 'Judasz', '1995-05-22');
insert into student values (32,'5150', '2014-09-02');
insert into osoba (nazwisko, imie, dataUrodzenia) values ('Włodarz', 'Kazimierz', '1992-08-12');
insert into dydaktyk (idOsoba, idStopien) select IdOsoba, idStopien from Osoba, StopnieTytuly where Stopien like 'Magister' and nazwisko like 'Włodarz';
7.2
update dydaktyk set podlega=i.idOsoba from (select idOsoba from osoba where imie like 'Kajetan' and nazwisko like 'Kalafior') i 
where dydaktyk.idOsoba = (select idOsoba from osoba where imie like 'Kazimierz' and nazwisko like 'Włodarz');
7.3
update student set NrIndeksu='s'+NrIndeksu from (select idOsoba from student where NrIndeksu not like 's%') i where student.idOsoba=i.IdOsoba;

8.1
create table Miasto (idMiasto int primary key identity (1,1), Miasto varchar(50) not null);
8.2
alter table Miasto add idPanstwo int foreign key (IdPanstwo) references Panstwo(idPanstwo);
8.3
insert into miasto (Miasto, IdPanstwo) select 'Warszawa', IdPanstwo from Panstwo where Panstwo = 'Polska';
insert into miasto (Miasto, IdPanstwo) select 'Poznań', IdPanstwo from Panstwo where Panstwo = 'Polska';
insert into miasto (Miasto, IdPanstwo) select 'Kraków', IdPanstwo from Panstwo where Panstwo = 'Polska';
insert into miasto (Miasto, IdPanstwo) select 'Londyn', IdPanstwo from Panstwo where Panstwo = 'Anglia';
insert into miasto (Miasto, IdPanstwo) select 'Berlin', IdPanstwo from Panstwo where Panstwo = 'Niemcy';
insert into miasto (Miasto, IdPanstwo) select 'Nowy Jork', IdPanstwo from Panstwo where Panstwo = 'USA';
8.4
alter table osoba add idMiasto int foreign key (idMiasto) references miasto(idMiasto);
8.5
update osoba set idMiasto = (select idMiasto from miasto where miasto = 'Warszawa') where idOsoba <= 8;
update osoba set idMiasto = (select idMiasto from miasto where miasto = 'Kraków') where idOsoba in (10,12,14);
update osoba set idMiasto = (select idMiasto from miasto where miasto = 'Poznań') where Imie like 'G%' or Imie like 'H%' or imie like 'J%';
8.8
create table katedra (idKatedra int primary key not null identity(1,1), katedra varchar(50) not null);
8.9
alter table dydaktyk add idKatedra int foreign key (idKatedra) references katedra(idKatedra) on delete set null;
8.10
alter table katedra add idOsoba int foreign key (idOsoba) references dydaktyk(iOsoba) on delete no action;
8.11
insert into katedra (Katedra) values ('Bazy danych');
insert into katedra (Katedra) values ('Inżynieria oprogramowania');
insert into katedra (Katedra) values ('Sztuczna inteligencja');
8.13
update katedra set idOsoba = (select idOsoba from osoba where imie = 'Bazyli' and nazwisko = 'Brokuł') where katedra = 'Bazy danych';
update katedra set idOsoba = (select idOsoba from osoba where imie = 'Kunegunda' and nazwisko = 'Karp') where katedra = 'Sztuczna inteligencja';
8.19
alter table osoba add constraint date check (dataUrodzenia < '1999-01-01' and dataUrodzenia > '1900-01-01');
insert into osoba (nazwisko, imie, dataUrodzeniam idMiasto, idPanstwo) values ('Rogal', 'Kuba','2005-10-10', 1, 1);


dodatkowo
insert into osoba (nazwisko, imie, dataUrodzenia, idMiasto, idPanstwo) values ('Kornel', 'Rafał', '1995-05-22');
insert into student values (33, 's5155', '2014-09-06');
insert into ocena (idStudent, idPrzedmiot, dataWystawienia, idDydaktyk, ocena) select idOsoba, '1', '2019-10-10', '1', '5' from osoba where imie = 'Rafał' and nazwisko = 'Kornel';
delete from osoba where imie = 'Rafał' and nazwisko = 'Kornel'; 




update student set nrIndeksu = Replace(nrIndeksu, 'ss', '') where NrIndeksu like 'ss%';
select * from katedra;
select * from miasto;
select * from panstwo;
select * from osoba;
select * from dydaktyk;
select * from student;
select * from StopnieTytuly;
