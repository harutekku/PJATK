SELECT * FROM emp;
SELECT * FROM dept;
select ename, job, sal, deptno from emp;
select ename, job, sal*12, deptno from emp;
select 'Pracownik ' || ename ||' pracuje na stanowisku '||job||' w dziale '||deptno from emp;
select ename Nazwisko , job Stanowisko, sal*12 "Placa roczna", deptno "Numer dzialu" from emp;
select ename Nazwisko , job Stanowisko, sal*12 "Placa roczna", deptno "Numer dzialu" from emp order by ename Asc;
select ename Nazwisko , job Stanowisko, sal*12 "Placa roczna", deptno "Numer dzialu" from emp order by ename desc;
select ename Nazwisko , job Stanowisko, sal*12 "Placa roczna", deptno "Numer dzialu" from emp order by ename;
select ename Nazwisko , job Stanowisko, sal*12 "Placa roczna", deptno "Numer dzialu" from emp order by job, 12*sal desc;
select job from emp order by job;
select distinct job from emp;
select distinct ename, job from emp order by job;

select ename, job, sal, deptno from emp where sal>2000;
select ename, job, sal, deptno from emp where sal>2000 and deptno=20;
select ename, job, sal, deptno from emp where deptno=20 or deptno=10 or deptno=40;
select ename, job, sal, deptno from emp where deptno in(20,10,40);
select ename, job, sal, deptno from emp where deptno not in(20,10,40);
select ename, job, sal, deptno from emp where sal>=1600 and sal <=3000;
select ename, job, sal, deptno from emp where sal between 1600 and 3000;
select ename, job, sal, deptno from emp where sal not between 1600 and 3000;
select ename, job, sal, deptno from emp where ename like 'KIN_';
select ename, job, sal, deptno from emp where ename like '%TH%';
select ename, job, sal, deptno from emp where ename not like 'KIN_';
select ename, job, sal, deptno from emp where ename = 'KING';

select ename, job, deptno from emp;
select dname, loc, deptno from dept;
select ename, job, dname, loc, emp.deptno edeptno, dept.deptno Ddeptno from emp,dept where dept.deptno=emp.deptno order by ename;
select ename, job, dname, loc, emp.deptno edeptno, dept.deptno Ddeptno from emp join dept on dept.deptno=emp.deptno order by ename;
select ename, job, deptno from emp where deptno is null;

select job, sum(sal*12) from emp group by job;


select * from emp;
--3 i 6
select ename, sal*12 "Placa Roczna" from emp;
--9
select ename, sal*12+NVL(comm,0) "Placa Roczna" from emp;
--11
select distinct deptno from emp;
--13
select * from emp order by deptno, sal desc;
--2
select dname, deptno from dept where deptno>20;
--4
select * from emp where sal between 1000 and 2000;
--5
select * from emp where mgr in(7902, 7566, 7788);
--6
select * from emp where ename like 'S%';
--9
select empno, ename, job from emp where sal not between 1000 and 2000;
--24
select ename from emp where ename like '%TH%' or ename like '%LL%';
--26
select ename, sal*12+NVL(comm,0) from emp;
--3
select ename, dept.deptno, loc from emp,dept where dept.deptno=emp.deptno order by ename;
--4
select ename, loc, dname from emp,dept where dept.deptno=emp.deptno and sal>1500;
--7
select ename, job from emp join dept on dept.deptno=emp.deptno where loc like 'DALLAS';

--dodatkowe

update emp set sal=sal*1.1 where ename ='SMITH';

insert into emp (empno, ename, job, sal, deptno, hiredate) values (7935,'PINK','SALESMAN',1200, 20, SYSDATE);

select * from emp;
