
//Lista Egzemplarzy ktore nie zostaly jeszcze oddane
select * from wypozyczenie where dataZwrotu is null;

//Lista Egzemplarzy kupionych w 2019 roku
select * from egzemplarz where rokZakupienia=2019;

//lista autorow o imieniu Diana
select * from Autor where imie = 'Diana';

//lista pracownikow z dodatkami do pensji
select * from Pracownik where DodatekDoPensji <> 0;

//lista pracownikow o roli bibliotekarz
select * from Pracownik where Rola_idRoli=(Select idRoli from Rola where nazwaRoli='Bibliotekarz');




//Lista ksiazek wraz z autorami
select tytulKsiazki, Imie, nazwisko from Ksiazka join Autor on Autor_idAutora=idAutora;

//Pensje wyplacane pracownikom
select imie, nazwisko, dodatekDoPensji+podstawowaPensja as Pensja from Pracownik join Rola on idRoli=Rola_idRoli;

//Lista spotkan pracownikow z klientami
select distinct pracownik.imie, pracownik.nazwisko, czytelnik.imie, czytelnik.nazwisko from pracownik join wypozyczenie on idPracownika=Pracownik_idPracownika join Czytelnik on idCzytelnika=Czytelnik_idCzytelnika;

//lista kategorii jakie pisza autorzy
select distinct imie, nazwisko, nazwaKategorii from autor join ksiazka on idAutora=autor_idautora join kategoria on idKategorii=Kategoria_idKategorii order by nazwisko;

//Lista ksiazek z kategorii Obyczajowe
select tytulKsiazki from ksiazka join kategoria on idkategorii=kategoria_idKategorii where nazwaKategorii='Obyczajowe';





//Ile ksiazek napisal kazdy autor
select imie, nazwisko, count(1) from (select * from autor join ksiazka on idAutora=autor_idautora) group by imie, nazwisko;

//ile ksiazek wypozyczyl kazdy czytelnik
select imie, nazwisko, count(1) from czytelnik join wypozyczenie on idCzytelnika=Czytelnik_idCzytelnika group by imie, nazwisko;

//Lista wydawcow ktorzy wydali jedna ksiazke wiecej niz raz;
select nazwaWydawnictwa from wydawnictwo join wydanie on idWydawnictwa=wydawnictwo_idWydawnictwa group by nazwaWydawnictwa having count(1)>1;

//Role ktore sa wykonywane przez wiecej niz 3 osoby
select nazwaRoli from rola join pracownik on idRoli=rola_idRoli group by nazwaRoli having count(1)>2;

//Minimalne, œrednie i maksymalne zarobki w bibliotece
select min(pensja),avg(pensja),max(pensja) from (select dodatekDoPensji+podstawowaPensja as pensja, nazwaRoli from Pracownik join Rola on idRoli=Rola_idRoli) where nazwaRoli<>'Szef';

