use [2019SBD];

--zadanie 3
alter table osoba add Plec char(1) null;

DECLARE plec_kursor CURSOR FOR SELECT idOsoba, imie from osoba;
declare @idosoba integer, @imie varchar(20), @plec char(1);
open plec_kursor;
fetch next from plec_kursor into @idosoba, @imie;
while @@Fetch_status = 0
begin
	if @imie like 'Barnaba' 
	begin
		set @plec = 'm';
	end;
	else if right(@imie, 1) like 'a'
	begin
		set @plec='k';
	end;
	else
	begin
		set @plec='m';
	end;
	update osoba set plec=@plec where IdOsoba=@idosoba;
	fetch next from plec_kursor into @idosoba, @imie;
end;
close plec_kursor;
deallocate plec_kursor;
GO
--zadanie 4
CREATE TABLE Rekrutacja (Imie Varchar(32), Nazwisko Varchar(32), DataUrodzenia Date, Obywatelstwo Varchar(16));

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
declare @imie varchar(20), @nazwisko varchar(20), @dataUrodzenia date, @obywatelstwo varchar(20), @plec char(1), @panstwo int;
open rekrutacja_kursor;
fetch next from rekrutacja_kursor into @imie, @nazwisko, @dataurodzenia, @obywatelstwo;
declare @numerStudenta int = (SELECT Max(RIGHT(rTrim(NrIndeksu), Len(NrIndeksu) - 1)) + 1 FROM Student);
while @@Fetch_status = 0
begin
	if @imie like 'Barnaba' 
	begin
		set @plec = 'm';
	end;
	else if right(@imie, 1) like 'a'
	begin
		set @plec='k';
	end;
	else
	begin
		set @plec='m';
	end;
	select @panstwo = idPanstwo from Panstwo where Panstwo like @obywatelstwo;
	insert into Osoba (Nazwisko, Imie,DataUrodzenia,Plec,idPanstwo) values (@nazwisko, @imie, @dataUrodzenia, @plec, @panstwo);
	declare @idoso int = (select max(idOsoba) from osoba where imie like @imie and Nazwisko like @nazwisko);
	insert into Student (IdOsoba, NrIndeksu, DataRekrutacji) values (@idoso, concat('s',@numerStudenta), GETDATE());
	set @numerStudenta = @numerStudenta+1;

	fetch next from rekrutacja_kursor into @imie, @nazwisko, @dataurodzenia, @obywatelstwo;
end;
close rekrutacja_kursor;
deallocate rekrutacja_kursor;

GO



--BLOK 2 
--zad 1

--create procedure dane_studentow @rok date
alter procedure dane_studentow @rok date
as
begin
	select imie, nazwisko, miasto, nrIndeksu from osoba left join student on osoba.IdOsoba=Student.IdOsoba left join Miasto on osoba.idMiasto=Miasto.idMiasto where student.DataRekrutacji = @rok;
end;
exec dane_studentow '2019-11-22'
go

--zad 2
-- create procedure ile_studentow @rok date, @ile int output
alter procedure ile_studentow @rok date, @ile int output
as
begin
	set @ile = (select count(imie) from osoba left join student on osoba.IdOsoba=Student.IdOsoba left join Miasto on osoba.idMiasto=Miasto.idMiasto where student.DataRekrutacji = @rok);
end;
declare @ile1 int=0;
exec ile_studentow '2019-11-22', @ile1 output;
print @ile1;
go


alter procedure ile_studentow @rok date
as
begin
	declare @ile int;
	set @ile = (select count(imie) from osoba left join student on osoba.IdOsoba=Student.IdOsoba left join Miasto on osoba.idMiasto=Miasto.idMiasto where student.DataRekrutacji = @rok);
	return @ile
end;
declare @ile2 int=0;
exec @ile2 =ile_studentow '2019-11-22';
print @ile2;
go






select * from osoba;
select * from Rekrutacja;
select * from student;
