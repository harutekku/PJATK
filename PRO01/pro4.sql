use kubbit

--1
SELECT	ename,
	CASE
	       WHEN deptno=30 THEN 'Sprzedaz'
		   WHEN deptno=10 THEN 'ksiegowosc'
	       ELSE 'Pozostali'
	END
FROM emp

--2
SELECT	ename,
	CASE
	       WHEN deptno=30 THEN 'Sprzedaz'
		   WHEN deptno=10 and comm IS NULL THEN 'ksiegowosc bez prowizji'
		   WHEN deptno=10 and comm is not null THEN 'ksiegowosc z prowizja'
	       ELSE 'Pozostali'
	END
FROM emp

--3
SELECT * INTO nowy_emp FROM emp
UPDATE nowy_emp SET sal = 0;
UPDATE nowy_emp SET sal = emp.sal FROM emp WHERE emp.empno = nowy_emp.empno;

--4
truncate table nowy_emp;

--5
go
create function srednia_dzial(@deptno INT)
returns numeric(9,2)
as begin
	declare @srednia numeric(9,2)
	select @srednia=avg(sal) from emp where deptno=@deptno
	return @srednia
end
select * from emp where sal>dbo.srednia_dzial(10);

--6
create function pracownicy_posada (@job varchar(10))
returns table
as return(
	select * from emp where job=@job
)
select * from pracownicy_posada('salesman');
go

--8
CREATE PROCEDURE zad8 --nie dziala
--ALTER PROCEDURE zad8 
AS
BEGIN
	DECLARE kursor SCROLL CURSOR FOR SELECT * FROM emp;
	DECLARE @rand int = RAND()*(SELECT count(*) from emp)+1, @empno int, @ename VARCHAR(10), @job VARCHAR(10), @mgr int, @hiredate DATE, @sal numeric(9,2), @comm numeric(9,2), @deptno int;
	OPEN kursor;
	FETCH ABSOLUTE(@rand) next FROM kursor INTO @empno, @ename, @job, @mgr, @hiredate, @sal, @comm, @deptno;
	PRINT @empno +' '+ @ename +' '+ @job+' '+ @mgr+' '+ @hiredate+' '+ @sal+' '+ @comm+' '+ @deptno;
	CLOSE kursor;
	DEALLOCATE kursor;
END
EXEC zad8 








select * from emp;
select * from dept;
select * from salgrade;
select * from nowy_emp;