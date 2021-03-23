--z poprzednich zajêæ plus ma³e przeróbki dla pañstwa
go
create TRIGGER NrIndeksu ON Student FOR INSERT
AS
DECLARE StudCursor CURSOR FOR 
		SELECT IdOsoba, NrIndeksu, DataRekrutacji FROM INSERTED WHERE NrIndeksu is null
DECLARE @Id int, @Nr varchar(10), @Data varchar(20) 
OPEN StudCursor;
FETCH NEXT FROM StudCursor INTO @Id, @Nr, @Data
WHILE @@FETCH_STATUS = 0
BEGIN
	IF @Nr IS NULL
	BEGIN
		UPDATE Student
		SET NrIndeksu = (SELECT 's' + Cast((Max(Cast(Right(Rtrim(NrIndeksu), Len(Rtrim(NrIndeksu)) - 1) As Int)) + 1) As Varchar) FROM Student)
		WHERE IdOsoba = @Id
	END;
	FETCH NEXT FROM StudCursor INTO @Id, @Nr, @Data
END;
CLOSE StudCursor;
DEALLOCATE StudCursor;
GO
alter PROCEDURE DodajStudenta @Imie VARCHAR(30), @Nazwisko VARCHAR(30), @DataUrodzenia VARCHAR(20), @PLEC CHAR(1), @Panstwo int
AS
BEGIN
	IF NOT EXISTS ((SELECT 1 FROM Osoba WHERE Osoba.Imie = @Imie AND Osoba.Nazwisko = @Nazwisko AND Osoba.DataUrodzenia = @DataUrodzenia AND Osoba.PLEC = @PLEC))
	BEGIN
		INSERT INTO Osoba (Imie, Nazwisko, DataUrodzenia, PLEC, Panstwo)
		VALUES(@Imie, @Nazwisko, @DataUrodzenia, @PLEC, @Panstwo)
		INSERT INTO Student (IDOsoba, DATAREKRUTACJI) VALUES((SELECT IDOsoba FROM Osoba WHERE Osoba.Imie = @Imie AND Osoba.Nazwisko = @Nazwisko AND Osoba.DataUrodzenia = @DataUrodzenia AND Osoba.PLEC = @PLEC), GETDATE())
	END;
	ELSE
	BEGIN
		IF NOT EXISTS (SELECT 1 FROM Student WHERE Student.IDOsoba = (SELECT IDOsoba FROM Osoba WHERE Osoba.Imie = @Imie AND Osoba.Nazwisko = @Nazwisko AND Osoba.DataUrodzenia = @DataUrodzenia AND Osoba.PLEC = @PLEC))
		BEGIN
			INSERT INTO Student (IDOsoba, DATAREKRUTACJI) VALUES((SELECT IDOsoba FROM Osoba WHERE Osoba.Imie = @Imie AND Osoba.Nazwisko = @Nazwisko AND Osoba.DataUrodzenia = @DataUrodzenia AND Osoba.PLEC = @PLEC), GETDATE())
		END;
	END;
END;
GO;
use uczelnia
--panstwo
alter table Osoba add Panstwo int null;
--z poprzednich zajec 
--alter table osoba add Plec char(1) null;
--alter table dbo.student alter column NrIndeksu char(10) null;

--nasza zmienna tablicowa
DECLARE @tabTest table (id int identity(1, 1), imie varchar(32), nazwisko varchar(62), dataUrodzenia date, panstwo varchar(54))

INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Adela','Ananas','1997-02-15','Mo³dawia')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Alojzy','Arbuz','1999-12-05','Ukraina')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Barnaba','Burak','2000-02-03','Polska')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Benita','Brukiew','1999-09-05','Niemcy')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Cyprian','Cz¹ber','1999-06-21','Polska')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Celestyna','Ciecierzyca','2000-06-03','S³owacja')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Delfina','Durian','1999-01-30','Francja')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Dionizy','Daktyl','1999-11-13','Grecja')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Filomena','Fenku³','2000-04-12','Turcja')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Gerwazy','Groszek','1999-11-27','Portugalia')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Horacy','Hiacynt','2000-08-09','Hiszpania')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('Iluminata','Imbir','2000-07-04','Austria')
INSERT INTO @tabTest(imie, nazwisko, dataUrodzenia, panstwo) VALUES ('January','Jarmu¿','1999-06-12','Rumunia')

--edytowanie tablicy pod k¹tem pañstw - zamiana s³owa na id i dodanie panstwa do tabeli panstwa jeœli nie istnieje
BEGIN
	DECLARE KURSOR CURSOR FOR SELECT id, panstwo FROM @tabTest;
    DECLARE @id int, @NAZWAPANSTWA VARCHAR(30);
    OPEN KURSOR;
    FETCH NEXT FROM KURSOR INTO @id, @NAZWAPANSTWA;
    WHILE @@FETCH_STATUS = 0
        BEGIN
            IF NOT EXISTS (SELECT 1 FROM PANSTWO WHERE Panstwo = @NAZWAPANSTWA)
                BEGIN
                    INSERT INTO Panstwo
                    VALUES (@NAZWAPANSTWA)
                END;
			update @tabTest set panstwo = (select idpanstwo from Panstwo where panstwo = @NAZWAPANSTWA) where id=@id;
            FETCH NEXT FROM KURSOR INTO @id, @NAZWAPANSTWA;
        END;
    CLOSE KURSOR;
    DEALLOCATE KURSOR;
END;
--dodanie studentów do bazy danych wraz z p³ciami
Begin
DECLARE dodaj CURSOR FOR 
	SELECT imie, nazwisko, dataurodzenia, panstwo FROM @tabTest
DECLARE @imie varchar(32), @nazwisko varchar(62), @dataUrodzenia date, @panstwo int, @plec char(1)
OPEN dodaj;
FETCH NEXT FROM dodaj INTO @imie, @nazwisko, @dataurodzenia, @panstwo
WHILE @@FETCH_STATUS = 0
	BEGIN
	set @plec = case when @imie = 'Barnaba' then 'm'
					when RIGHT(@imie,1) = 'a' then 'k'
					else 'm'
				end
	EXEC DodajStudenta @imie, @nazwisko, @dataUrodzenia, @plec, @panstwo
	FETCH NEXT FROM dodaj INTO @imie, @nazwisko, @dataurodzenia, @panstwo
END;
CLOSE dodaj;
DEALLOCATE dodaj;
end

go
--dodanie roku akademickiego
insert into RokAkademicki values('2021_22','2021-10-01','2022-08-31');
--komenda do wylistowania nowych studentow z bazy
SELECT NTILE(2) OVER(PARTITION BY YEAR(DataRekrutacji) ORDER BY nazwisko), YEAR(DataRekrutacji), Imie, nazwisko FROM Osoba O JOIN Student s ON O.IdOsoba = s.idosoba where Datarekrutacji = '2021-03-23';
--dodanie nowych grup
insert into grupa (nrgrupy, semestrnauki, IdRokAkademicki) values ('WIs I.1', 1, '2021_22'),('WIs I.2', 1, '2021_22'),('WIs II.1', 2, '2021_22'),('WIs II.2', 2, '2021_22');

--przypisanie studentów do nowych grup
begin
DECLARE grupska CURSOR FOR 
	SELECT NTILE(2) OVER(PARTITION BY YEAR(DataRekrutacji) ORDER BY nazwisko), s.idOsoba FROM Osoba O JOIN Student s ON O.IdOsoba = s.idosoba where Datarekrutacji = '2021-03-23'
DECLARE @nrgrupy int, @idOsoba int
OPEN grupska;
FETCH NEXT FROM grupska INTO @nrgrupy, @idOsoba
WHILE @@FETCH_STATUS = 0
	BEGIN
	insert into studentgrupa select @idOsoba, idgrupa from grupa where idrokakademicki = '2021_22' and SUBSTRING(nrgrupy,len(nrgrupy),1)=@nrgrupy 
	FETCH NEXT FROM grupska INTO @nrgrupy, @idOsoba
END;
CLOSE grupska;
DEALLOCATE grupska;
end





--rzeczy których nie rozumiem ale nie chce usuwaæ z kodu
DECLARE @licznik Int;
INSERT INTO  (empno, ename, job, sal, hiredate)
OUTPUT INSERTED.empno, INSERTED.Ename,
INSERTED.job, INSERTED.sal, INSERTED.hiredate
INTO @Out --tu nie mo¿e pojawiæ siê œrednik !!!
SELECT Isnull(Max(empno), 0) + 1, 'Pink', 'ANALYST', 1200,
Getdate() FROM emp;
SELECT @empno = empno FROM @Out;
