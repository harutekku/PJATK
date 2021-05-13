--zad1
CREATE DATABASE University;
\c university
CREATE SCHEMA acc;
CREATE SCHEMA dea;
CREATE SCHEMA tea;

-- tables
-- Table: Account
CREATE TABLE acc.Account (
    IdAccount int  NOT NULL,
    Number varchar(100)  NOT NULL,
    CreatedAt date  NOT NULL,
    Student_IdStudent int  NOT NULL,
    CONSTRAINT Account_pk PRIMARY KEY (IdAccount)
);

-- Table: Classes
CREATE TABLE tea.Classes (
    IdClasses int  NOT NULL,
    Date date  NOT NULL,
    Time time  NOT NULL,
    IdTeacher int  NOT NULL,
    IdSubject int  NOT NULL,
    CONSTRAINT Classes_pk PRIMARY KEY (IdClasses)
);

-- Table: Grade
CREATE TABLE tea.Grade (
    IdGrade int  NOT NULL,
    Value decimal(5,2)  NOT NULL,
    CreatedAt date  NOT NULL,
    IdClasses int  NOT NULL,
    IdStudent int  NOT NULL,
    CONSTRAINT Grade_pk PRIMARY KEY (IdGrade)
);

-- Table: Payment
CREATE TABLE acc.Payment (
    IdPayment int  NOT NULL,
    Amount decimal(8,2)  NOT NULL,
    CreatedAt date  NOT NULL,
    IdAccount int  NOT NULL,
    CONSTRAINT Payment_pk PRIMARY KEY (IdPayment)
);

-- Table: Person
CREATE TABLE dea.Person (
    IdPerson int  NOT NULL,
    FirstName varchar(100)  NOT NULL,
    LastName varchar(100)  NOT NULL,
    Email varchar(100)  NOT NULL,
    Address varchar(100)  NOT NULL,
    CONSTRAINT Person_pk PRIMARY KEY (IdPerson)
);

-- Table: Semester
CREATE TABLE dea.Semester (
    IdSemester int  NOT NULL,
    Name varchar(100)  NOT NULL,
    CONSTRAINT Semester_pk PRIMARY KEY (IdSemester)
);

-- Table: SemesterEntry
CREATE TABLE dea.SemesterEntry (
    IdSemesterEntry int  NOT NULL,
    IdStudent int  NOT NULL,
    IdStudies int  NOT NULL,
    IdCurrentSemester int  NOT NULL,
    IdStartSemester int  NOT NULL,
    CreatedAt timestamp  NOT NULL,
    CONSTRAINT SemesterEntry_pk PRIMARY KEY (IdSemesterEntry)
);

-- Table: Student
CREATE TABLE dea.Student (
    IdStudent int  NOT NULL,
    IndexNumber varchar(100)  NOT NULL,
    CONSTRAINT Student_pk PRIMARY KEY (IdStudent)
);

-- Table: Student_Classes
CREATE TABLE tea.Student_Classes (
    IdStudent int  NOT NULL,
    IdClasses int  NOT NULL,
    CONSTRAINT Student_Classes_pk PRIMARY KEY (IdClasses,IdStudent)
);

-- Table: Studies
CREATE TABLE dea.Studies (
    IdStudies int  NOT NULL,
    Name varchar(100)  NOT NULL,
    Description varchar(100)  NOT NULL,
    NumberOfSemesters int  NOT NULL,
    CONSTRAINT Studies_pk PRIMARY KEY (IdStudies)
);

-- Table: Subject
CREATE TABLE tea.Subject (
    IdSubject int  NOT NULL,
    Name varchar(100)  NOT NULL,
    IdStudies int  NOT NULL,
    CONSTRAINT Subject_pk PRIMARY KEY (IdSubject)
);

-- Table: Teacher
CREATE TABLE dea.Teacher (
    IdTeacher int  NOT NULL,
    Position varchar(100)  NOT NULL,
    CONSTRAINT Teacher_pk PRIMARY KEY (IdTeacher)
);

-- foreign keys
-- Reference: Account_Student (table: Account)
ALTER TABLE acc.Account ADD CONSTRAINT Account_Student
    FOREIGN KEY (Student_IdStudent)
    REFERENCES dea.Student (IdStudent)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Classes_Subject (table: Classes)
ALTER TABLE tea.Classes ADD CONSTRAINT Classes_Subject
    FOREIGN KEY (IdSubject)
    REFERENCES tea.Subject (IdSubject)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Classes_Teacher (table: Classes)
ALTER TABLE tea.Classes ADD CONSTRAINT Classes_Teacher
    FOREIGN KEY (IdTeacher)
    REFERENCES dea.Teacher (IdTeacher)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Grade_Classes (table: Grade)
ALTER TABLE tea.Grade ADD CONSTRAINT Grade_Classes
    FOREIGN KEY (IdClasses)
    REFERENCES tea.Classes (IdClasses)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Grade_Student (table: Grade)
ALTER TABLE tea.Grade ADD CONSTRAINT Grade_Student
    FOREIGN KEY (IdStudent)
    REFERENCES dea.Student (IdStudent)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Payment_Account (table: Payment)
ALTER TABLE acc.Payment ADD CONSTRAINT Payment_Account
    FOREIGN KEY (IdAccount)
    REFERENCES acc.Account (IdAccount)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: SemesterEntry_Semester1 (table: SemesterEntry)
ALTER TABLE dea.SemesterEntry ADD CONSTRAINT SemesterEntry_Semester1
    FOREIGN KEY (IdCurrentSemester)
    REFERENCES dea.Semester (IdSemester)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: SemesterEntry_Semester2 (table: SemesterEntry)
ALTER TABLE dea.SemesterEntry ADD CONSTRAINT SemesterEntry_Semester2
    FOREIGN KEY (IdStartSemester)
    REFERENCES dea.Semester (IdSemester)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: SemesterEntry_Student (table: SemesterEntry)
ALTER TABLE dea.SemesterEntry ADD CONSTRAINT SemesterEntry_Student
    FOREIGN KEY (IdStudent)
    REFERENCES dea.Student (IdStudent)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: SemesterEntry_Studies (table: SemesterEntry)
ALTER TABLE dea.SemesterEntry ADD CONSTRAINT SemesterEntry_Studies
    FOREIGN KEY (IdStudies)
    REFERENCES dea.Studies (IdStudies)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Student_Person (table: Student)
ALTER TABLE dea.Student ADD CONSTRAINT Student_Person
    FOREIGN KEY (IdStudent)
    REFERENCES dea.Person (IdPerson)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Subject_Studies (table: Subject)
ALTER TABLE tea.Subject ADD CONSTRAINT Subject_Studies
    FOREIGN KEY (IdStudies)
    REFERENCES dea.Studies (IdStudies)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Table_13_Classes (table: Student_Classes)
ALTER TABLE tea.Student_Classes ADD CONSTRAINT Table_13_Classes
    FOREIGN KEY (IdClasses)
    REFERENCES tea.Classes (IdClasses)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Table_13_Student (table: Student_Classes)
ALTER TABLE tea.Student_Classes ADD CONSTRAINT Table_13_Student
    FOREIGN KEY (IdStudent)
    REFERENCES dea.Student (IdStudent)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Teacher_Person (table: Teacher)
ALTER TABLE dea.Teacher ADD CONSTRAINT Teacher_Person
    FOREIGN KEY (IdTeacher)
    REFERENCES dea.Person (IdPerson)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;



--żeby sprawdzić:
\dt *.*


--zad2

REVOKE CREATE ON SCHEMA public FROM public;
REVOKE ALL ON DATABASE university FROM public;

--zad3 i 4 

CREATE ROLE reports;
GRANT CONNECT ON DATABASE university TO reports;
GRANT USAGE ON SCHEMA acc, dea, tea TO reports;
GRANT SELECT ON ALL TABLES IN SCHEMA acc, dea, tea TO reports;
CREATE USER reports_user WITH PASSWORD 'pass1';
GRANT reports TO reports_user;

CREATE ROLE admin;
GRANT CONNECT ON DATABASE university TO admin;
GRANT USAGE ON SCHEMA acc, dea, tea TO reports;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA acc, dea, tea TO admin;
CREATE USER admin_user WITH PASSWORD 'pass1';
GRANT admin TO admin_user;

CREATE ROLE deaneary_write;
GRANT CONNECT ON DATABASE university TO deaneary_write;
GRANT USAGE ON SCHEMA dea TO reports;
GRANT UPDATE, INSERT, SELECT ON ALL TABLES IN SCHEMA dea TO deaneary_write;
CREATE USER deaneary_write_user WITH PASSWORD 'pass1';
GRANT deaneary_write TO deaneary_write_user;

CREATE ROLE teacher;
GRANT CONNECT ON DATABASE university TO teacher;
GRANT USAGE ON SCHEMA tea TO reports;
GRANT UPDATE, INSERT ON ALL TABLES IN SCHEMA tea TO teacher;
GRANT SELECT ON Student TO teacher;
CREATE USER teacher_user WITH PASSWORD 'pass1';
GRANT teacher TO teacher_user;

CREATE ROLE accounting;
GRANT CONNECT ON DATABASE university TO accounting;
GRANT USAGE ON SCHEMA acc TO reports;
GRANT INSERT ON ALL TABLES IN SCHEMA acc TO accounting;
CREATE USER accounting_user WITH PASSWORD 'pass1';
GRANT accounting TO accounting_user;



