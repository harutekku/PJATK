use [2019SBD];

--zadanie 3
alter table osoba add Plec char(1) null;

DECLARE plec_kursor CURSOR FOR SELECT idOsoba, imie from osoba;declare @idosoba integer, @imie varchar(20), @plec char(1);open plec_kursor;fetch next from plec_kursor into @idosoba, @imie;while @@Fetch_status = 0begin	if @imie like 'Barnaba' 	begin		set @plec = 'm';	end;	else if right(@imie, 1) like 'a'	begin		set @plec='k';	end;	else	begin		set @plec='m';	end;	update osoba set plec=@plec where IdOsoba=@idosoba;	fetch next from plec_kursor into @idosoba, @imie;end;close plec_kursor;deallocate plec_kursor;GO--zadanie 4CREATE TABLE Rekrutacja (Imie Varchar(32), Nazwisko Varchar(32), DataUrodzenia Date, Obywatelstwo Varchar(16));

INSERT INTO Rekrutacja (Imie, Nazwisko, DataUrodzenia, Obywatelstwo)
VALUES ('Adela','Ananas','1997-02-15','Mołdawia'),
	   ('Alojzy','Arbuz','1998-09-13','Ukraina'),
	   ('Barnaba','Burak','1999-01-21','Polska'),
	   ('Benita','Brukiew','1998-12-22','Niemcy'),
	   ('Cyprian','Cząber','1996-08-30','Polska'),
	   ('Celestyna','Ciecierzyca','1995-06-03','Słowacja'),
	   ('Delfina','Durian','1996-10-30','Francja'),
	   ('Dionizy','Daktyl','1997-09-21','Grecja');


DECLARE rekrutacja_kursor CURSOR FOR SELECT imie, nazwisko, DataUrodzenia, Obywatelstwo from Rekrutacja;
declare @imie varchar(20), @nazwisko varchar(20), @dataUrodzenia date, @obywatelstwo varchar(20), @plec char(1);
open rekrutacja_kursor;fetch next from rekrutacja_kursor into @imie, @nazwisko, @dataurodzenia, @obywatelstwo;while @@Fetch_status = 0begin	if imie like 'Barnaba' 	begin		set @plec = 'm';	end;	else if right(@imie, 1) like 'a'	begin		set @plec='k';	end;	else	begin		set @plec='m';	end;	update osoba set plec=@plec where IdOsoba=@idosoba;	fetch next from plec_kursor into @idosoba, @imie;end;close plec_kursor;deallocate plec_kursor;declare @maxIndeks integer;SELECT @maxIndeks = Max(RIGHT(rTrim(NrIndeksu), Len(NrIndeksu) - 1)) + 1 FROM Student;select * from osoba;select * from Rekrutacja;select * from student;