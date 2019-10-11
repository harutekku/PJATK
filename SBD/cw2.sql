//1.9;
select nrIndeksu, DATEDIFF(Year,DataRekrutacji,GETDATE()) as "Lat",DATEDIFF(MONTH,DataRekrutacji,GETDATE())-DATEDIFF(Year,DataRekrutacji,GETDATE())*12 as "Miesiecy",DATEDIFF(Day,DataRekrutacji,GETDATE())-DATEDIFF(Year,DataRekrutacji,GETDATE())*356 as "Dni" from Student;
//2.2;
select nazwisko from Osoba where imie like '%a%' and imie not like '%b%';
//3.4;
select imie, nazwisko, DataRekrutacji from Osoba inner join Student on osoba.idOsoba=Student.idOsoba where DataRekrutacji between '2012-07-01' and '2012-09-30';
//3.9
select imie, nazwisko, stopien from dydaktyk inner join osoba on osoba.idOsoba=dydaktyk.idOsoba left join Stopnietytuly on dydaktyk.idstopien=stopnietytuly.idStopien;
//3.10
select imie, nazwisko, ISNULL(stopien,'Brak') from dydaktyk inner join osoba on osoba.idOsoba=dydaktyk.idOsoba left join Stopnietytuly on dydaktyk.idstopien=stopnietytuly.idStopien;
//3.14
select s.imie as "Imie studenta" , s.nazwisko as "nazwisko studenta", ocena, d.imie, d.nazwisko from ocena inner join student on Ocena.idStudent = student.idOsoba inner join osoba s on student.idOsoba=s.idOsoba
																inner join dydaktyk on Ocena.idDydaktyk = dydaktyk.idOsoba inner join osoba d on dydaktyk.idOsoba=d.idOsoba;

//3.17


//3.21
select nazwisko from osoba inner join student on osoba.idOsoba=student.idOsoba inner join ocena on ocena.idStudent=student.idOsoba inner join Przedmiot on ocena.idPrzedmiot=przedmiot.idPrzedmiot where przedmiot like 'Relacyjne bazy danych'
except
select nazwisko from osoba inner join student on osoba.idOsoba=student.idOsoba inner join ocena on ocena.idStudent=student.idOsoba inner join Przedmiot on ocena.idPrzedmiot=przedmiot.idPrzedmiot where przedmiot like 'Administracja systemów operacyjnych';
//4.5
select osoba.idOsoba, imie, nazwisko, przedmiot, count(1), avg(ocena) from osoba 
inner join student on osoba.idOsoba=student.idOsoba
inner join ocena on ocena.idStudent=student.idOsoba 
inner join Przedmiot on ocena.idPrzedmiot=przedmiot.idPrzedmiot 
group by osoba.idosoba, osoba.imie, osoba.nazwisko, przedmiot.przedmiot order by osoba.idosoba;
//4.7
select przedmiot, ocena, count(1) from przedmiot inner join ocena on ocena.idPrzedmiot=przedmiot.idprzedmiot group by przedmiot where ocena >= 3;