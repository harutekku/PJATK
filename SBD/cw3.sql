use [2019SBD];

7.1
Insert into osoba (nazwisko, imie, dataUrodzenia) VALUES ('Modrep', 'Judasz', '1995-05-22');
insert into student values (32,'5150', '2014-09-02');
Insert into osoba (nazwisko, imie, dataUrodzenia) VALUES ('Włodarz', 'Kazimierz', '1992-08-12');
insert into dydaktyk (idOsoba, idStopien) Select IdOsoba, idStopien from Osoba, StopnieTytuly where Stopien like 'Magister' and nazwisko like 'Włodarz';
7.2
update dydaktyk set podlega=i.idOsoba from (select idOsoba from osoba where imie like 'Kajetan' and nazwisko like 'Kalafior') i 
where dydaktyk.idOsoba = (select idOsoba from osoba where imie like 'Kazimierz' and nazwisko like 'Włodarz');
7.3
update student set NrIndeksu='s'+NrIndeksu from (select idOsoba from student where NrIndeksu not like 's%') i where student.idOsoba=i.IdOsoba;

8.1
create table Miasto (idMiasto INT PRIMARY KEY IDENTITY (1,1), Miasto VARCHAR(50) NOT NULL);
8.2
Alter table Miasto add constraint panstwo_miasto foreign key (IdPanstwo) references Panstwo(idPanstwo);


update student set nrIndeksu = Replace(nrIndeksu, 'ss', '') where NrIndeksu like 'ss%';
select * from miasto;
select * from panstwo;
select * from osoba;
select * from dydaktyk;
select * from student;
select * from StopnieTytuly;
