--Czesc I
--zadanie1
SELECT Przedmiot, Symbol
FROM Tabela;

--zadanie2
SELECT IdStopien AS '*', Stopien AS '*', Skrot AS '*'
FROM StopnieTytuly;

--zadanie3
SELECT NrGrupy, IdRokAkademicki, SemestrNauki
FROM Grupa;

--zadanie4
SELECT Imie, Nazwisko, YEAR(DataUrodzenia)
FROM Osoba
ORDER BY YEAR(DataUrodzenia) DESC, Nazwisko ASC;

--zadanie5
SELECT (Imie || Nazwisko) AS "Pracownicy i studenci"
FROM Osoba
ORDER BY Nazwisko ASC;

--zadanie6
SELECT (Imie || Nazwisko || YEAR(DataUrodzenia)) AS "Student lub dydaktyk"
FROM Osoba
ORDER BY YEAR(DataUrodzenia) DESC, Nazwisko ASC;

--zadanie7
SELECT DISTINCT(Imie)
FROM OSOBA
ORDER BY Imie ASC;

--zadanie8
SELECT DISTINCT(YEAR(DataRekrutacji)) AS "Lata rekrutacji"
FROM Student
ORDER BY YEAR(DataRekrutacji) DESC;

--zadanie9 9.	Sprawdź, ile miesięcy, dni i lat upłyneło od daty rekrutacji każdego studenta (podaj tylko numer indeksu). W MS SQL Server użyj funkcji Datediff() i Getdate(), w ORACLE funkcji EXTRACT
SELECT NrIndeksu, DATEDIFF(MONTH,DataRekrutacji,GETDATE()) AS 'Months' , 
DATEDIFF(DAY,DataRekrutacji,GETDATE()) AS 'Days', 
DATEDIFF(YEAR,DATAREKRUTACJI,GETDATE()) AS 'Years' 
FROM OSOBA JOIN STUDENT 
ON OSOBA.IDOSOBA = STUDENT.IDOSOBA;

--CzescII
--zadanie1
SELECT Imie
FROM Osoba
WHERE Imie LIKE 'K%';

--zadanie2
SELECT Nazwisko 
FROM Osoba 
WHERE Imie LIKE '%A%' AND IMIE NOT LIKE '%B%';

--zadanie3
SELECT Imie, Nazwisko
FROM Osoba
WHERE Imie LIKE '_____';

--zadanie4
SELECT Imie, Nazwisko
FROM Osoba
WHERE DataUrodzenia IS NOT NULL;

--zadanie5
SELECT Imie, Nazwisko
FROM Osoboa
WHERE DataUrodzenia IS NULL;

--zadanie6
SELECT Imie, Nazwisko
FROM Osoba
WHERE Plec IS NULL;

--CzescIII
--zadanie1 1.	Wypisz imiona i nazwiska wszystkich studentów.
SELECT Imie, Nazwisko
FROM OSOBA JOIN STUDENT
ON OSOBA.IDOSOBA = STUDENT.IDOSOBA;

--zadanie2
SELECT Imie, Nazwisko
FROM OSOBA JOIN DYDAKTYK
ON OSOBA.IDOSOBA = DYDAKTYK.IDOSOBA;

--zadanie3
SELECT Imie, Nazwisko
FROM Osoba JOIN DYDAKTYK
ON OSOBA.IDOSOBA = DYDAKTYK.IDOSOBA
JOIN STUDENT
ON OSOBA.IDOSOBA = STUDENT.IDOSOBA
WHERE STUDENT.IDOSOBA = DYDAKTYK.IDOSOBA;

--zadanie4 4.	Znajdź imiona,  nazwiska i datę rekrutacji studentów zarekrutowanych pomiędzy 1 lipca i 30 września 2012 roku.
SELECT IMIE, NAZWISKO, DATAREKRUTACJI
FROM OSOBA JOIN STUDENT
ON OSOBA.IDOSOBA = STUDENT.IDSTUDENT
WHERE DATAREKRUTACJI BETWEEN '2012-07-01' AND '2012-09-30';

--zadanie5
SELECT IMIE, NAZWISKO
FROM OSOBA JOIN DYDAKTYK
ON OSOBA.IDOSOBA = DYDAKTYK.IDOSOBA
WHERE DYDAKTYK.IDSTOPIEN IS NULL;

--zadanie6
SELECT IMIE, NAZWISKO
FROM OSOBA JOIN DYDAKTYK
ON OSOBA.IDOSOBA = DYDAKTYK.IDOSOBA
WHERE DYDAKTYK.PODLEGA IS NULL;

--zadanie7 7.	Wypisz imiona i nazwiska wszystkich dydaktyków posiadających stopień doktora.
SELECT Imie, Nazwisko
FROM OSOBA JOIN DYDAKTYK
ON OSOBA.IDOSOBA = DYDAKTYK.IDOSOBA
LEFT JOIN STOPNIETYTULY 
ON DYDAKTYK.IDSTOPIEN = STOPNIETYTULY.IDSTOPIEN
WHERE STOPNIETYTULY.STOPIEN = 'Doktor';

--8Wypisz Imiona i nazwiska studentów, którzy otrzymali ocenę 2 z przedmiotu Analiza matematyczna I w roku 2013.
SELECT IMIE, NAZWISKO
FROM Osoba JOIN STUDENT ON OSOBA.IDOSOBA = STUDENT.IDOSOBA
JOIN OCENA ON STUDENT.IDOSOBA = OCENA.IDSTUDENT
JOIN PRZEDMIOT ON OCENA.IDPRZEDMIOT = PRZEDMIOT.IDPRZEDMIOT
WHERE PRZEDMIOT.PRZEDMIOT = 'Analiza matematyczna i';

--9I10Wypisz imiona, nazwiska i stopnie naukowe wszystkich dydaktyków. Uwzględnij dydaktyków, którzy nie mają stopnia.
SELECT IMIE, NAZWISKO, ISNULL(STOPNIETYTULY.STOPIEN, 'BRAK')
FROM OSOBA 
JOIN DYDAKTYK ON OSOBA.IDOSOBA = DYDAKTYK.IDOSOBA
LEFT JOIN STOPNIETYTULY ON DYDAKTYK.IDSTOPIEN = STOPNIETYTULY.IDSTOPIEN;

--11.Wypisz bez powtórzeń nazwy przedmiotów, z których wystawiono oceny.
SELECT DISTINCT(PRZEDMIOT)
FROM PRZEDMIOT JOIN OCENA
ON PRZEDMIOT.IDPRZEMIOT = OCENA.IDOCENA;

--12.Wypisz Imiona i nazwiska studentów, nazwę przedmiotu oraz ocenę, jaką z tego przedmiotu otrzymał. Wynik posortuj po nazwie przedmiotu i ocenie malejąco.
SELECT IMIE, NAZWISKO, PRZEDMIOT, OCENA
FROM OSOBA JOIN STUDENT ON OSOBA.IDOSOBA = STUDENT.IDOSOBA
JOIN OCENA ON OCENA.IDSTUDENT = OSOBA.IDOSOBA
JOIN PRZEDMIOT ON OCENA.IDPRZEDMIOT = PRZEDMIOT.IDPRZEDMIOT
ORDER BY PRZEDMIOT.PRZEDMIOT DESC, OCENA.OCENA DESC;

--13.Wypisz imiona i nazwiska dydaktyków wraz z imionami i nazwiskami ich przełożonych.
SELECT OSOBA.IMIE, OSOBA.NAZWISKO, CONCAT(D2.Imie,D2.Nazwisko) AS 'SZEF'
FROM OSOBA JOIN DYDAKTYK D1 ON OSOBA.IDOSOBA = D1.IDOSOBA
JOIN OSOBA D2 ON D1.Podlega = D2.IDOSOBA;

--14.Wypisz imiona i nazwiska studentów, uzyskane przez nich oceny z przedmiotów (wypisz ich nazwy) oraz imiona i nazwiska dydaktyków, którzy te oceny wystawili.
SELECT O.IMIE, O.NAZWISKO, OCENA, P.PRZEDMIOT, O2.IMIE, O2.NAZWISKO
FROM OSOBA O JOIN STUDENT S ON O.IDOSOBA = S.IDOSOBA
JOIN OCENA ON OCENA.IDSTUDENT = S.IDOSOBA
JOIN PRZEDMIOT P ON OCENA.IDPRZEDMIOT = P.IDPRZEDMIOT
JOIN DYDAKTYK D1 ON OCENA.IdDydaktyk = D1.IDOSOBA
JOIN OSOBA O2 ON O2.IDOSOBA = D1.IDOSOBA;

--15.Wypisz w jednej kolumnie imiona i nazwiska wszystkich studentów z dopiskiem „student”  oraz wszystkich dydaktyków z dopiskiem „dydaktyk”. Kolumnę nazwij „Rola w uczelni.
SELECT CONCAT(O.IMIE,O.NAZWISKO) AS 'ROLA W UCZELNI'
FROM OSOBA O JOIN STUDENT S ON O.IDOSOBA = S.IDSTUDENT
JOIN DYDAKTYK D ON D.IDOSOBA = O.IDOSOBA;

--16.Wypisz imiona i nazwiska wszystkich osób będących jednocześnie studentami i dydaktykami.
SELECT IMIE, NAZWISKO
FROM OSOBA JOIN STUDENT ON OSOBA.IDOSOBA = STUDENT.IDOSOBA
JOIN DYDAKTYK ON STUDENT.IDOSOBA = DYDAKTYK.IDOSOBA;

--17.Wypisz w jednej kolumnie imiona i nazwiska studentów, poprzedzając je słowem „Pani”  w przypadku kobiet i „Pan” w przypadku mężczyzn.
ALTER TABLE OSOBA ADD PLEC INT;
UPDATE OSOBA SET PLEC = 0 WHERE IMIE LIKE '%A';
UPDATE OSOBA SET PLEC = 1 WHERE IMIE NOT LIKE '%A';

SELECT IIF(imie LIKE '%a','PANI ','PAN ')+IMIE+' '+NAZWISKO
FROM OSOBA O JOIN STUDENT S
ON O.IDOSOBA = S.IDOSOBA;
--dwie opcje
ALTER TABLE Osoba ADD Plec Varchar(1)
UPDATE Osoba set Plec = 'K' WHERE Imie LIKE '%a'
UPDATE Osoba set Plec = 'M' WHERE Imie NOT LIKE '%a'

SELECT CAST(
				CASE WHEN Plec LIKE 'K'
					THEN 'Pani ' + Imie + ' ' + Nazwisko
				ELSE
					'Pan ' + Imie + ' ' + Nazwisko
				END AS Varchar) as Personalia

FROM Student
INNER JOIN Osoba ON Student.IdOsoba = Osoba.IdOsoba;

--18.Znajdź imię, nazwisko i daty rekrutacji studentów o numerach indeksów s3045, s3162, s3177.
SELECT IMIE, NAZWISKO, DATAREKRUTACJI
FROM OSOBA JOIN STUDENT ON OSOBA.IDOSOBA = STUDENT.IDOSOBA
WHERE NRINDEKSU = 's3045' OR NRINDEKSU='s3162' OR NRINDEKSU='s3177';

--19.Znajdź imiona, nazwiska, daty rekrutacji i numery indeksów studentek (pań) zarekrutowanych w 2012 roku lub mających nazwisko rozpoczynające się na literę ‘B’
SELECT IMIE, NAZWISKO, DATAREKRUTACJI, NRINDEKSU
FROM OSOBA JOIN STUDENT ON OSOBA.IDOSOBA = STUDENT.IDOSOBA
WHERE (PLEC = 0 AND DATEDIFF(DAY,DataRekrutacji,GETDATE()) = 2012) OR (PLEC = 0 AND NAZWISKO LIKE 'B%');

--20.Wypisz bez powtórzeń imiona i nazwiska studentów (panów), którzy mają wystawioną ocenę zarówno z przedmiotu „Administracja systemów operacyjnych” jak i „Relacyjne bazy danych”. 
SELECT DISTINCT Imie, NAZWISKO
FROM OSOBA JOIN STUDENT ON OSOBA.IDOSOBA = STUDENT.IDOSOBA
WHERE EXISTS(SELECT 1
	FROM OCENA
	JOIN PRZEDMIOT P ON P.IDPRZEDMIOT = OCENA.IDPRZEDMIOT 
	WHERE PRZEDMIOT = 'Relacyjne bazy danych' AND IDSTUDENT = STUDENT.IDOSOBA) 
	AND EXISTS (SELECT 1
	FROM OCENA
	JOIN PRZEDMIOT P ON P.IDPRZEDMIOT = OCENA.IDPRZEDMIOT 
	WHERE PRZEDMIOT = 'Administracja systemów operacyjnych' AND IDSTUDENT = STUDENT.IDOSOBA);
	
--lub

SELECT os1.nazwisko FROM Osoba os1
INNER JOIN Student s1 ON s1.IdOsoba = os1.IdOsoba
INNER JOIN Ocena oc1 ON os1.IdOsoba = oc1.IdStudent
INNER JOIN Przedmiot p1 ON p1.IdPrzedmiot = oc1.IdPrzedmiot
WHERE p1.Przedmiot = 'Relacyjne bazy danych' AND os1.IdOsoba IN (
	SELECT Osoba.IdOsoba FROM Osoba
	INNER JOIN Student ON Student.IdOsoba = Osoba.IdOsoba
	INNER JOIN Ocena ON Student.IdOsoba = Ocena.IdStudent
	INNER JOIN Przedmiot ON Przedmiot.IdPrzedmiot = Ocena.IdPrzedmiot
	WHERE Przedmiot.Przedmiot = 'Administracja systemów operacyjnych' AND os1.IdOsoba = Osoba.IdOsoba
	);

--21.Wypisz nazwiska wszystkich studentów, którzy mają wystawioną ocenę z przedmiotu „Relacyjne bazy danych”, ale nie mają oceny z przedmiotu „Administracja systemów operacyjnych”.

SELECT O.IMIE, O.NAZWISKO
FROM OSOBA O JOIN STUDENT S ON O.IDOSOBA = S.IDOSOBA
WHERE EXISTS(SELECT 1
	FROM OCENA
	JOIN PRZEDMIOT P ON P.IDPRZEDMIOT = OCENA.IDPRZEDMIOT 
	WHERE PRZEDMIOT = 'Relacyjne bazy danych' AND IDSTUDENT = S.IDOSOBA) 
	AND NOT EXISTS (SELECT 1
	FROM OCENA
	JOIN PRZEDMIOT P ON P.IDPRZEDMIOT = OCENA.IDPRZEDMIOT 
	WHERE PRZEDMIOT = 'Administracja systemów operacyjnych' AND IDSTUDENT = S.IDOSOBA);
	
--LUB

SELECT os1.nazwisko FROM Osoba os1
INNER JOIN Student s1 ON s1.IdOsoba = os1.IdOsoba
INNER JOIN Ocena oc1 ON os1.IdOsoba = oc1.IdStudent
INNER JOIN Przedmiot p1 ON p1.IdPrzedmiot = oc1.IdPrzedmiot
WHERE p1.Przedmiot = 'Relacyjne bazy danych' AND os1.IdOsoba NOT IN (
	SELECT Osoba.IdOsoba FROM Osoba
	INNER JOIN Student ON Student.IdOsoba = Osoba.IdOsoba
	INNER JOIN Ocena ON Student.IdOsoba = Ocena.IdStudent
	INNER JOIN Przedmiot ON Przedmiot.IdPrzedmiot = Ocena.IdPrzedmiot
	WHERE Przedmiot.Przedmiot = 'Administracja systemów operacyjnych' AND os1.IdOsoba = Osoba.IdOsoba
	);


--21.Wypisz nazwiska wszystkich studentów, którzy mają wystawioną ocenę z przedmiotu „Relacyjne bazy danych”, ale nie mają oceny z przedmiotu „Administracja systemów operacyjnych”.


SELECT OS.NAZWISKO
FROM OSOBA OS JOIN STUDENT ON OS.IDOSOBA = STUDENT.IDOSOBA
JOIN OCENA ON STUDENT.IDOSOBA = OCENA.IDOSOBA
JOIN PRZEDMIOT ON PRZEDMIOT.IDPRZEDMIOT = OCENA.IDPRZEDMIOT
WHERE PRZEDMIOT.PRZEDMIOT = 'Relacyjne bazy danych' AND OS.IDOSOBA NOT IN(
SELECT OS2.IDOSOBA
FROM OSOBA OS2 JOIN STUDENT ON OS2.IDOSOBA = STUDENT.IDOSOBA
JOIN OCENA ON STUDENT.IDOSOBA = OCENA.IDOSOBA
JOIN PRZEDMIOT ON PRZEDMIOT.IDPRZEDMIOT = OCENA.IDPRZEDMIOT
WHERE PRZEDMIOT.PRZEDMIOT = 'Administracja systemów operacyjnych' AND OS.IDOSOBA = OS2.IDOSOBA);

--CzescIV

--1.Znajdź liczbę przedmiotów zapisanych w bazie. 
SELECT COUNT(1)
FROM Przedmiot;

--2.Policz studentów, którzy zapisali się na studia w 2012 r.

SELECT COUNT(1)
FROM STUDENT
WHERE YEAR(DATAREKRUTACJI) = '2012';

--3.Znajdź średnią, najlepszą i najgorszą z ocen wystawionych z przedmiotu o skrócie AM1.
SELECT AVG(OCENA), MAX(OCENA), MIN(OCENA)
FROM OCENA JOIN PRZEDMIOT ON OCENA.IDPRZEDMIOT = PRZEDMIOT.IDPRZEDMIOT
WHERE PRZEDMIOT.SYMBOL = 'AM1';

--4.Znajdź liczbę wystawionych ocen oraz średnią ocenę z każdego przedmiotu. Podaj nazwę przedmiotu.
SELECT COUNT(OCENA), AVG(OCENA), PRZEDMIOT.PRZEDMIOT
FROM OCENA JOIN PRZEDMIOT ON OCENA.IDPRZEDMIOT = PRZEDMIOT.IDPRZEDMIOT
GROUP BY PRZEDMIOT.PRZEDMIOT;

--5.Znajdź liczbę wystawionych ocen oraz średnią ocenę każdego studenta z każdego przedmiotu. Podaj nazwę przedmiotu, imię i nazwisko studenta
SELECT COUNT(OCENA), AVG(OCENA), OSOBA.IMIE, OSOBA.NAZWISKO, PRZEDMIOT.PRZEDMIOT
FROM OSOBA JOIN STUDENT ON OSOBA.IDOSOBA = STUDENT.IDOSOBA
JOIN OCENA ON OCENA.IDSTUDENT = STUDENT.IDOSOBA
JOIN PRZEDMIOT ON OCENA.IDPRZEDMIOT = PRZEDMIOT.IDPRZEDMIOT
GROUP BY OSOBA.IMIE, OSOBA.NAZWISKO, PRZEDMIOT.PRZEDMIOT;

--6.Z wyniku zadania poprzedniego wyeliminuj przypadki wystawienia jednemu studentowi więcej niż jednej oceny z jednego przedmiotu. Wynik posortuj po nazwie przedmiotu rosnąco i średniej ocen malejąco.
SELECT COUNT(OCENA), AVG(OCENA), OSOBA.IMIE, OSOBA.NAZWISKO, PRZEDMIOT.PRZEDMIOT
FROM OSOBA JOIN STUDENT ON OSOBA.IDOSOBA = STUDENT.IDOSOBA
JOIN OCENA ON OCENA.IDSTUDENT = STUDENT.IDOSOBA
JOIN PRZEDMIOT ON OCENA.IDPRZEDMIOT = PRZEDMIOT.IDPRZEDMIOT
GROUP BY OSOBA.IMIE, OSOBA.NAZWISKO, PRZEDMIOT.PRZEDMIOT
HAVING COUNT(OCENA) = 1
ORDER BY PRZEDMIOT.PRZEDMIOT ASC, AVG(OCENA) DESC;

--7.Nie stosując podapytania znajdź nazwy przedmiotów, z których wystawino więcej niż 5 ocen pozytwnych (>=5). 
SELECT PRZEDMIOT.PRZEDMIOT, COUNT(OCENA)
FROM PRZEDMIOT JOIN OCENA ON PRZEDMIOT.IDPRZEDMIOT = OCENA.IDPRZEDMIOT
WHERE OCENA > 2
GROUP BY PRZEDMIOT.PRZEDMIOT
HAVING COUNT(OCENA) >= 5;

--CzescV

--1.Stosując podzapytanie znajdź imiona i nazwiska studentów.

SELECT IMIE, NAZWISKO
FROM OSOBA
WHERE OSOBA.IDOSOBA IN (SELECT STUDENT.IDOSOBA FROM STUDENT);

--2.Stosując podzapytanie znajdź nazwy przedmiotów, z których wystawiono oceny.
SELECT PRZEDMIOT.PRZEDMIOT
FROM PRZEDMIOT
WHERE PRZEDMIOT.IDPRZEDMIOT IN (SELECT OCENA.IDPRZEDMIOT FROM OCENA);

--3.Znajdż imiona i nazwiska studentów, którzy rozpoczęli studia w tym samym roku co Alberta Ananas.
SELECT O1.IMIE, O1.NAZWISKO
FROM OSOBA O1 JOIN STUDENT S1 ON O1.IDOSOBA = S1.IDOSOBA
WHERE YEAR(S1.DATAREKRUTACJI) IN (SELECT YEAR(S2.DATAREKRUTACJI)
								FROM OSOBA O2 JOIN STUDENT S2 ON O2.IDOSOBA = S2.IDOSOBA
								WHERE O2.IMIE = 'Alberta' AND O2.NAZWISKO = 'Ananas');
--4.Znajdź imię, nazwisko i numer indeksu ostatnio przyjętego na uczelnię studenta.

SELECT O1.IMIE, O1.NAZWISKO, S1.NRINDEKSU
FROM OSOBA O1 JOIN STUDENT S1 ON O1.IDOSOBA = S1.IDOSOBA
WHERE DATAREKRUTACJI = (SELECT MAX(DATAREKRUTACJI)
						FROM STUDENT);

--5.Znajdź studentów, którzy mają średnią ocen wyższą niż średnia ocen Hieronima Kapusty
SELECT O1.IMIE, O1.Nazwisko, AVG(OCENA)
FROM OSOBA O1 JOIN STUDENT S1 ON O1.IDOSOBA = S1.IDOSOBA
JOIN OCENA ON S1.IDOSOBA = OCENA.IdStudent
GROUP BY O1.IMIE, O1.Nazwisko
HAVING AVG(OCENA) > (SELECT AVG(OCENA)
					FROM OCENA JOIN STUDENT S2 ON OCENA.IDSTUDENT = S2.IDOSOBA
					JOIN OSOBA O2 ON O2.IdOsoba = S2.IdOsoba
					WHERE O2.IMIE = 'Hieronim' AND O2.NAZWISKO = 'Kapusta');
				
				
--6.Znajdź imiona i nazwiska studentów, którzy rozpoczęli studia w tym samym roku co Gryzelda Gruszka i mają średnią ocen wyższą niż średnia ocen Seweryna Selera. 
SELECT O1.IMIE, O1.NAZWISKO
FROM OSOBA O1 JOIN STUDENT S1 ON O1.IDOSOBA = S1.IDOSOBA
JOIN OCENA OC1 ON S1.IDOSOBA = OC1.IDSTUDENT
WHERE YEAR(S1.DATAREKRUTACJI) = (
SELECT YEAR(S2.DATAREKRUTACJI)
FROM OSOBA O2 
JOIN STUDENT S2 ON O2.IDOSOBA = S2.IDOSOBA
WHERE O2.IMIE = 'Gryzelda' AND O2.NAZWISKO = 'Gruszka')
GROUP BY O1.IMIE, O1.NAZWISKO
HAVING AVG(OC1.OCENA) > (
SELECT AVG(OC2.OCENA)
FROM OSOBA O3 
JOIN STUDENT S3 ON O3.IDOSOBA = S3.IDOSOBA
JOIN OCENA OC2 ON O3.IDOSOBA = OC2.IdStudent
WHERE O3.IMIE = 'Seweryn' AND O3.NAZWISKO = 'Seler');

--7.Znajdź studenta z najwyższą średnią ocen.
SELECT O1.IMIE, O1.NAZWISKO, AVG(OC1.Ocena) AS srednia_ocen 
FROM OSOBA O1
JOIN STUDENT S1 ON O1.IDOSOBA = S1.IDOSOBA
JOIN OCENA OC1 ON OC1.IDSTUDENT = S1.IDOSOBA
GROUP BY O1.IMIE, O1.NAZWISKO
HAVING AVG(OC1.OCENA) = (
SELECT MAX(srednia_ocen)
FROM (
SELECT O1.IMIE, O1.NAZWISKO, AVG(OC1.Ocena) AS srednia_ocen 
FROM OSOBA O1
JOIN STUDENT S1 ON O1.IDOSOBA = S1.IDOSOBA
JOIN OCENA OC1 ON OC1.IDSTUDENT = S1.IDOSOBA
GROUP BY O1.IMIE, O1.NAZWISKO
)os);

--CzescVI
-- 1.	Dla każdego rocznika rekrutacji znajdź pierwszego zarekrutowanego studenta.

SELECT os.Imie, os.Nazwisko, stud.dataRekrutacji FROM Osoba os
INNER JOIN Student stud ON stud.IdOsoba = os.IdOsoba
GROUP BY os.Imie, os.Nazwisko, stud.DataRekrutacji
HAVING stud.dataRekrutacji = (SELECT MAX(dataRekrutacji) FROM Student);

--2.	Znajdź studentów (imię i nazwisko) z najwyższą oceną z każdego przedmiotu (podaj jego nazwę).

SELECT O1.IMIE, O1.NAZWISKO, P1.PRZEDMIOT
FROM OSOBA O1 JOIN STUDENT S1 ON O1.IDOSOBA = S1.IDOSOBA
JOIN OCENA OC1 ON OC1.IDSTUDENT = S1.IDOSOBA
JOIN PRZEDMIOT P1 ON OC1.IDPRZEDMIOT = P1.IDPRZEDMIOT
WHERE OC1.OCENA = (SELECT MAX(OC2.OCENA)
FROM OCENA OC2 JOIN PRZEDMIOT P2 ON OC2.IDPRZEDMIOT = P2.IDPRZEDMIOT)
GROUP BY O1.IMIE, O1.NAZWISKO, P1.PRZEDMIOT;

--3.Znajdź imiona i nazwiska studentów, którzy nie mają jeszcze żadnej oceny.

SELECT O1.IMIE, O1.NAZWISKO
FROM OSOBA O1 JOIN STUDENT S1 ON O1.IDOSOBA = S1.IDOSOBA
JOIN OCENA OC1 ON OC1.IDSTUDENT = S1.IDOSOBA
WHERE NOT EXISTS (SELECT OCENA
FROM OCENA);


--4.	Znajdź imiona i nazwiska studentów, którzy otrzymali co najmniej jedną oceną niedostateczną.
SELECT O1.IMIE, O1.NAZWISKO
FROM OSOBA O1 JOIN STUDENT S1 ON O1.IDOSOBA = S1.IDOSOBA
JOIN OCENA OC1 ON OC1.IDSTUDENT = S1.IDOSOBA
WHERE OC1.OCENA = 2
GROUP BY O1.IMIE, O1.NAZWISKO
HAVING COUNT(OC1.OCENA) > 0;

-- 5.	Wypisz imiona, nazwiska i stopnie dydaktyków, którzy nie mają podwładnych. 


SELECT Imie, Nazwisko, Stopien, Podlega FROM Osoba
INNER JOIN Dydaktyk ON Osoba.IdOsoba = Dydaktyk.IdOsoba
LEFT JOIN StopnieTytuly ON StopnieTytuly.IdStopien = Dydaktyk.IdStopien
WHERE dydaktyk.Podlega IS NULL;

========================UPDATE I TE KLIMATY

--czescVII
--zadanie1 1.	Dopisz do bazy dwa rekordy – jednego nowego studenta i jednego dydaktyka. Pamiętaj, że w MS SQL Server kolumna IdOsoba_ w tabeli OSOBA jest wyposażona we właściwość Identity, a w ORACLE takiego automatyzmu nie ma.  Dydaktykowi nadaj stopień magister. Ostatnie polecenie wykonaj jedną instrukcja SELECT
INSERT INTO Osoba(Nazwisko, Imie, DataUrodzenia)
VALUES('Biniek', 'Adam', '1997-10-09');

INSERT INTO Osoba(Nazwisko, Imie, DataUrodzenia)
VALUES('Kowalski', 'Tomek', '1999-11-09');

INSERT INTO Student 
SELECT IdOsoba, 's18313', '2013-10-11' 
FROM Osoba 
WHERE Imie = 'Tomek' AND Nazwisko = 'Kowalski';

INSERT INTO Dydaktyk
SELECT idOsoba, idStopien, NULL 
FROM Osoba, StopnieTytuly 
WHERE Skrot = 'Mgr' AND Imie = 'Adam' AND Nazwisko = 'Biniek';

--zadanie2 2.	Nowego dydaktyka zrób podwładnymi pana Kajetana Kalafiora. Zadanie należy wykonać przy użyciu JEDNEGO polecenia SQL (można założyć unikalność pary wartości imię i nazwisko).
UPDATE Dydaktyk
SET Podlega = (SELECT IdOsoba FROM Osoba WHERE Imie = 'Kajetan' AND Nazwisko = 'Kalafior');

--zadanie3 3.	Zmodyfikuj numery indeksów w tabeli STUDENT, które omyłkowo nie posiadają litery s przed właściwym numerem.
UPDATE Student
SET NrIndeksu = 's'+NrIndeksu
WHERE NrIndeksu NOT LIKE 's%';

--czescVIII
--zadanie1 1.	Utwórz tabelę MIASTO {IdMiasto PK, Miasto}. Dobierz odpowiednie typy danych. Na kolumnie klucza głównego zrealizuj autonumerowanie, na kolumnie Miasto nie dopuść wstawienia wartości NULL.
CREATE TABLE Miasto(
idMiasto int IDENTITY (1,1) NOT NULL,
Miasto varchar (255) NOT NULL
PRIMARY KEY(idMiasto)
)

SELECT *
FROM Miasto;

--zadanie 2 2.	Utwórz więzy referencyjne pomiędzy tabelami MIASTO i PANSTWO pozwalające przypisać każde miasto do jednego Państwa.
ALTER TABLE Miasto
ADD IdPanstwo INT FOREIGN KEY (IdPanstwo) REFERENCES Panstwo(IdPanstwo);

--zadanie3 3.	Przypisz kilka przykładowych miast do właściwych państw
INSERT INTO Miasto(Miasto, IdPanstwo)
SELECT 'Warszawa', IdPanstwo FROM Panstwo WHERE Panstwo = 'Polska';

INSERT INTO Miasto(Miasto, IdPanstwo)
SELECT 'Berlin', IdPanstwo FROM Panstwo WHERE Panstwo = 'Niemcy';

INSERT INTO Miasto(Miasto, IdPanstwo)
SELECT 'Praga', IdPanstwo FROM Panstwo WHERE Panstwo = 'Czechy';

INSERT INTO Miasto(Miasto, IdPanstwo)
SELECT 'Kijow', IdPanstwo FROM Panstwo WHERE Panstwo = 'Ukraina';

INSERT INTO Miasto(Miasto, IdPanstwo)
SELECT 'Moskwa', IdPanstwo FROM Panstwo WHERE Panstwo = 'Rosja';

INSERT INTO Miasto(Miasto, IdPanstwo)
SELECT 'Krakow', IdPanstwo FROM Panstwo WHERE Panstwo = 'Polska';

INSERT INTO Miasto(Miasto, IdPanstwo)
SELECT 'Poznan', IdPanstwo FROM Panstwo WHERE Panstwo = 'Polska';

--zadanie4 4.	Do tabeli OSOBA dodaj kolumnę klucza obcego IdMiasto, wskazującą na miasto zamieszkania osoby. Utwórz więzy referencyjne do tabeli MIASTO.
ALTER TABLE Osoba
ADD IdMiasto INT FOREIGN KEY (IdMiasto) REFERENCES Miasto(IdMiasto);

--zadanie5 5.	Osoby o IdOsoba zakresu 1 – 8 zrób mieszkańcami Warszawy, o IdOsoba równym 10, 12, 14 zrób mieszkańcami Krakowa, a imionach rozpoczynających się od liter G, H, J mieszkańcami Poznania
UPDATE Osoba
SET IdMiasto = (SELECT TOP 1 IdMiasto --tylko z top 1 dziala, ale zle
FROM Miasto
WHERE (IdMiasto = 1 AND IdOsoba BETWEEN 1 AND 8) OR (IdMiasto = 6 AND IdOsoba IN (10, 12, 14)) OR (idMiasto = 7 AND Imie LIKE 'G%' OR Imie LIKE 'H%' OR Imie LIKE 'J%'));

UPDATE Osoba
SET IDMiasto = (SELECT IdMiasto
FROM Miasto
WHERE IdMiasto = 1 AND IdOsoba BETWEEN 1 AND 8);

UPDATE Osoba
SET IdMiasto = (SELECT IdMiasto
FROM Miasto
WHERE IdMiasto = 6 AND IdOsoba IN (10, 12, 14));

UPDATE Osoba
SET IdMiasto = (SELECT IdMiasto
FROM Miasto
WHERE idMiasto = 7 AND Imie LIKE 'G%' OR Imie LIKE 'H%' OR Imie LIKE 'J%');

SELECT *
FROM OSOBA;

SELECT *
FROM Miasto;

--zadanie8 8.	Utwórz tabelę KATEDRA (IdKatedra PK, Katedra Not Null). Dobierz odpowiednie typy danych dla kolumn. W MS SQL Server zrealizuj możliwość automatycznej generacji wartości klucza głównego.
CREATE TABLE Katedra(
idKatedra int IDENTITY (1,1) NOT NULL,
Katedra varchar (255) NOT NULL
PRIMARY KEY(idKatedra)
)

--zadania9 9.	Do tabeli DYDAKTYK dodaj kolumnę IdKatedra, utwórz więzy referencyjne do tabeli KATEDRA. Rolą utworzonego klucza obcego będzie przechowywanie informacji o przynależności dydaktyków do poszczególnych katedr. Więzy uzupełnij definicją akcji referencyjnej uzupełniającej wartością NULL wskaźnik do usuwanej katedry.
ALTER TABLE Dydaktyk
ADD IdKatedra INT FOREIGN KEY (IdKatedra) REFERENCES Katedra(IdKatedra) ON DELETE SET NULL;

--zadanie10 10.	Do tabeli KATEDRA dodaj kolumnę IdOsoba, utwórz więzy referencyjne do tabeli DYDAKTYK. Rolą utworzonego klucza obcego będzie przechowywanie informacji o dydaktykach będących kierownikami poszczególnych katedr. Zagwarantuj niemożność usunięcia dydaktyka będącego kierownikiem katedry.
ALTER TABLE Katedra
ADD IdOsoba INT FOREIGN KEY (IdOsoba) REFERENCES Dydaktyk(IdOsoba) ON DELETE NO ACTION;

--zadanie11 11.	Do tabeli KATEDRA wpisz katedry: Baz danych, Inżynierii oprogramowania, Sztucznej inteligencji. 
INSERT INTO Katedra (Katedra)
VALUES('Bazy danych');

INSERT INTO Katedra (Katedra)
VALUES('InZynieria oprogramowania');

INSERT INTO Katedra (Katedra)
VALUES('Sztuczna inteligencja');

--zadanie13 13.	Szefem katedry Baz Danych zrób Bazylego Brokuła, szefem katedry Sztucznej Inteligencji zrób Kunegundę Karp.
UPDATE Katedra
SET IdOsoba = (SELECT IdOsoba 
FROM OSOBA
WHERE Imie = 'Bazyl' AND Nazwisko = 'Brokuł')
WHERE Katedra = 'Bazy danych';

UPDATE Katedra
SET IdOsoba = (SELECT IdOsoba 
FROM OSOBA
WHERE Imie = 'Kunegunda' AND Nazwisko = 'Karp')
WHERE Katedra = 'Sztuczna inteligencja';

--zadanie19 19.	Do tabeli OSOBA dodaj więzy CHECK na kolumnie DataUrodzenia pilnując, aby w bazie nie pojawiły się osoby urodzone po 1999-01-01 ani przed 1900-01-01. Sprawdź działanie tych więzów
ALTER TABLE OSOBA 
ADD CONSTRAINT GoodDate 
CHECK(DataUrodzenia < '1999-01-01' AND DataUrodzenia > '1900-01-01');








































