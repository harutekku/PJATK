use [moja1]





--zad 1
CREATE PROCEDURE DodajStudenta @Imie VARCHAR(30), @Nazwisko VARCHAR(30), @DataUrodzenia VARCHAR(20), @PLEC CHAR(1)
AS
BEGIN
	IF NOT EXISTS ((SELECT 1 FROM Osoba WHERE Osoba.Imie = @Imie AND Osoba.Nazwisko = @Nazwisko AND Osoba.DataUrodzenia = @DataUrodzenia AND Osoba.PLEC = @PLEC))
	BEGIN
		INSERT INTO Osoba (Imie, Nazwisko, DataUrodzenia, PLEC)
		VALUES(@Imie, @Nazwisko, @DataUrodzenia, @PLEC)
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

DROP PROCEDURE DodajStudenta

EXEC DodajStudenta 'Jakub', 'Maslany', '2000-10-20', 'M'





alter table osoba add Plec char(1) null;
alter table dbo.student alter column NrIndeksu char(10) null;
select * from student;
select * from osoba;
insert into student values (3,null,'2014-09-19')

SELECT 's' + Cast((Max(Cast(Right(Rtrim(Nr_Indeksu), Len(Rtrim(Nr_Indeksu)) - 1) As Int)) + 1) As Varchar) FROM Student;


GO
--zad 2

ALTER TRIGGER NrIndeksu ON Student FOR INSERT
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