////////////////// 1
//1
select deptno, ename, mgr from emp;
//2
select * from emp;
//3
select empno, ename, 12*sal from emp;
//4
select empno, ename, 12*(sal+250) from emp;
//5
select empno, ename, 12*sal "Roczna" from emp;
//6
select empno, ename, 12*sal "Placa roczna" from emp;
//7 
select empno || ' ' || ename "zatrudniony" from emp;
//8
select 'Pracownik '||empno || ' ' || ename||' pracuje w dziale nr '||deptno "Informacje o pracownikach" from emp;
//9
select empno, ename, 12*sal- NVL(comm,0) from emp;
//10
select deptno from emp;
//11
select DISTINCT deptno from emp;
//12
select DISTINCT deptno,job from emp;
//13
select * from emp order by ename;
//14
select * from emp order by hiredate desc;
//15
select * from emp order by deptno, sal desc;


///////////////// 2
//1
select ename, empno, job, sal, deptno from emp where job='CLERK';
//2
select deptno,dname from dept where deptno>20;
//3
select empno,ename from emp where comm>sal;
//4
select * from emp where sal between 1000 and 2000;
//5
select * from emp where mgr in (7902, 7566, 7788);
//6
select * from emp where ename like 'S%';
//7
select * from emp where ename like '____';
//8
select empno, ename, job from emp where mgr is null;
//9
select * from emp where sal not between 1000 and 2000;
//10
select empno, ename, deptno from emp where ename not like 'M%';
//11
select ename from emp where mgr is not null;
//12
select ename, deptno, sal, job from emp where job like 'CLERK' and sal between 1000 and 2000;
//13
select ename, deptno, sal, job from emp where job like 'CLERK' or sal between 1000 and 2000;
//14
select ename, sal, job from emp where (job like 'MANAGER' and sal>1500) or job like 'SALESMAN';
//15
select ename, sal, job from emp where job in ('MANAGER','SALESMAN') and sal>1500;
//16
select ename, job, deptno from emp where job like 'MANAGER' or (job like 'CLERK' and deptno=10);
//17
select * from salgrade;
//18
select * from dept;
//19
select * from emp where 12*sal- NVL(comm,0)>24000 or 12*sal- NVL(comm,0)<12000;
//20
select empno, job, deptno from emp order by deptno, job;
//21
select distinct job from emp;
//22
select * from emp where deptno=10 or deptno=20 order by ename;
//23
select ename, job from emp where deptno=20 and job like 'CLERK';
//24
select ename from emp where ename like '%TH%' or  ename like '%LL%';
//25
select ename, job, sal from emp where mgr is not null;
//26
select ename, 12*sal-nvl(comm,0) from emp;
//27
select ename, deptno, hiredate from emp where EXTRACT(year from hiredate) = 1982;
//28
select ename, sal*12, comm from emp where job like 'SALESMAN' and sal>comm order by sal, ename;


//////////////// 3
//1 
select * from emp,dept;
//2 
select * from emp,dept where emp.deptno=dept.deptno;
//3
select * from emp full outer join deptno on emp.deptno=dept.deptno;


///////////////// 4
//1
Select Avg(sal) "średnia placa" from emp;
//2
select Min(sal) from emp where job like 'CLERK';
//3
select count(ename) from emp where deptno=20;
//4
select avg(sal), job from emp group by job;
//5
select avg(sal), job from emp where job not like 'MANAGER' group by job;
//6
select avg(sal), job, deptno from emp group by job, deptno;
//7
select max(sal), job from emp group by job;
//8
select avg(sal), count(1) from emp group by deptno having count(1)>3;
//9
select job from emp group by job having avg(sal)>=3000;
//10
select avg((sal-nvl(comm,0))*12), avg(sal-nvl(comm,0)) from emp group by job;
//11
select deptno from emp group by deptno having count(1)>3;
//12
select count(1) from emp group by empno;
//13
select * from emp; ///////////////////////////////////////////bad
//14
select count(1) from emp join dept on emp.deptno=dept.deptno where loc like 'DALLAS';
//15
select max(hisal) from salgrade;
//16
select count(1) from emp group by sal having count(1)>1;
//17
select (hisal+losal)/2 from salgrade where grade=2;
//18
select k.ename, count(1) from emp k join emp p on k.empno=p.mgr group by k.ename;
//19
select losal+hisal from salgrade where grade=1;