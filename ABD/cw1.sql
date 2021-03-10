exec sys.sp_spaceused;

exec sys.sp_help;

CREATE TABLE Persons2 (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
) on second;

CREATE TABLE Persons3 (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
);


select * from Persons;
select * from moja1.dbo.Persons;


CREATE DATABASE moja2 ON
(NAME = moja_dat, 
FILENAME = 'C:\Users\s18688\Desktop\mojadat.mdf',
SIZE = 2MB, MAXSIZE = UNLIMITED, FILEGROWTH = 5MB)
LOG ON
(NAME = moja_log,
FILENAME = 'C:\Users\s18688\Desktop\mojalog.ldf',
SIZE = 1MB, MAXSIZE = UNLIMITED, FILEGROWTH = 5MB)
GO
