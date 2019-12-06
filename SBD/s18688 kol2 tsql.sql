use [2019SBD]

--1

create table stypendium (IdOsoba integer foreign key(idOsoba) references student(idOsoba), DataPrzyznania date primary key, Kwota money not null);
go

--2

Create procedure dodaj_stypendium @imie varchar(20), @nazwisko varchar(20), @kwota money
as
begin
	if exists (select 1 from student left join osoba on student.idOsoba=osoba.idOsoba where @imie=imie and @nazwisko=nazwisko)
		begin
			declare @id int;
			select @id = (select idOsoba from osoba where @imie=imie and @nazwisko=nazwisko);
			if exists (select 1 from stypendium where @id=IdOsoba and Year(dataPrzyznania)=year(GETDATE()) )
				begin
					print 'Student otrzymal stypendium w tym roku'
				end;
			else
				begin
					insert into stypendium (IdOsoba,DataPrzyznania,Kwota) values (@id, GETDATE(), @kwota);
				end;
		end;
	else
		begin
			print 'Osoba nie jest studentem'
		end;
end;

exec dodaj_stypendium Alberta, Ananas, 500;

--3

alter table student add Srednia float;
GO

create trigger licz_srednia on ocena for insert, update, delete
as
begin
	declare @idStudent int;
	if exists (select idStudent from inserted)
		begin
			select @idStudent = (select idStudent from inserted);
		end;
	else if exists (select idStudent from deleted)
		begin
			select @idStudent = (select idStudent from deleted);
		end;
	update student set Srednia=(select AVG(ocena) from ocena where @idStudent=IdStudent) where @idStudent=IdOsoba;
end;


insert into ocena (IdStudent, IdPrzedmiot, DataWystawienia, IdDydaktyk, Ocena) values (12, 6, GETDATE(), 1, 4);

