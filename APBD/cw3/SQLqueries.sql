select IdEnrollment, semester, s.IdStudy, s.IdStudy from Enrollment e left join Studies s on e.IdStudy=s.IdStudy where name='IT' and semester=1
select MAX(IdEnrollment) from Enrollment;
go;
ALTER PROC promote
   @stud varchar(20),
   @sem int
AS
BEGIN
    declare @oldId int;
    declare @newId int;
    declare @studId int;
    SELECT @studId = idStudy from studies where name=@stud;
    SELECT @oldId = max(IdEnrollment) from Enrollment where idStudy=@studId and semester=@sem;
    SELECT @newId = max(IdEnrollment) from Enrollment where idStudy=@studId and semester=1+@sem;
    if @newId is null 
        begin
        select @newId = max(IdEnrollment)+1 from Enrollment;
        insert into Enrollment (IdEnrollment, Semester, IdStudy, StartDate) values(@newId, @sem+1, @studId, GETDATE());
        end
    update student set IdEnrollment = @newId where IdEnrollment = @oldId;
    select * from Enrollment where idEnrollment = @newId;
END

GO;

exec promote 'IT', 3

select * from Studies;
select * from Enrollment;
select * from Student;
select * from Roles;
SELECT * FROM information_schema.tables;

create table Roles (
    IdRole int not null primary key,
    Role nvarchar(20) not null
);

insert into Roles (IdRole,Role) values (1, 'Admin'),(2, 'Moderator'),(3,'Student');
insert into Roles (IdRole,Role) values (4, 'Employee');

create table StudentRoles (
    IndexNumber nvarchar(100) not null foreign key references Student(IndexNumber),
    idRole int not null foreign key references Roles(IdRole)
);

insert into StudentRoles (IndexNumber,idRole) values ('s1000',1),('s1000',2),('s1000',3),('s1001',2),('s1001',3),('s1002',3);
insert into StudentRoles (IndexNumber,idRole) values ('s1000',4);

select * from student s left join StudentRoles sr on s.IndexNumber=sr.IndexNumber left join roles r on sr.idRole=r.IdRole;