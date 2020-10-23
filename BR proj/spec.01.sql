
use kubbit
--Jakub Paw³owicz s18688

--1.5
SELECT imie + ' ' + nazwisko as 'Pracownicy i studenci' from osoba order by nazwisko;
--1.7
select distinct imie from osoba order by imie;

--2.3
select imie, nazwisko from osoba where imie like '_____';
--2.5
select * from osoba where dataUrodzenia is null;

--3.12
select imie, nazwisko, przedmiot, ocena from osoba o join student s on o.idosoba=s.idosoba join ocena oc on oc.idstudent=s.idosoba join przedmiot p on oc.idprzedmiot=p.idprzedmiot order by przedmiot,ocena desc;
--3.16
select imie, nazwisko from osoba o join student s on o.idosoba=s.idosoba join dydaktyk d on o.idosoba=d.idosoba;

--4.1
select count(*) as 'Liczba przedmiotow' from przedmiot ;
--4.4
select count(ocena), avg(ocena), przedmiot from ocena o join przedmiot p on p.idprzedmiot=o.idprzedmiot group by p.przedmiot;

--5.3
select imie, nazwisko from osoba o join student s on o.idosoba=s.idosoba where s.datarekrutacji=(select datarekrutacji from student s join osoba o on o.idosoba=s.idosoba where nazwisko = 'Ananas' and imie = 'Alberta');
--5.4
select imie, nazwisko, nrindeksu from osoba o join student s on o.idosoba=s.idosoba where datarekrutacji=(select max(datarekrutacji) from student);

--6.3
select imie, nazwisko from osoba o join student s on o.idosoba=s.idosoba where s.idosoba not in (select idstudent from ocena);
--6.4
select imie, nazwisko from osoba o join student s on o.idosoba=s.idosoba where s.idosoba in (select idstudent from ocena where ocena=2);
