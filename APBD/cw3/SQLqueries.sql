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
        insert into Enrollment values(@newId, @sem+1, @studId, GETDATE());
        end
    update student set IdEnrollment = @newId where IdEnrollment = @oldId;
    select * from Enrollment where idEnrollment = @newId;
END

GO;

exec promote 'IT', 3

select * from Studies;
select * from Enrollment;
select * from Student;
