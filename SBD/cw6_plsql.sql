--1
Create or replace trigger ocenowy
before delete on ocena
begin
    raise_application_error(-20001,'Nie usuwamy ocen');
end;

delete from ocena where ocena=3;

--2 
create or replace trigger dydaktyczny 
before delete on dydaktyk for each row
declare v_podlegajacy int;
begin
    select count(1) into v_podlegajacy from dydaktyk d left join dydaktyk p on d.idosoba=p.podlega where p.idosoba is null and d.idosoba = :old.idOsoba;
    if v_podlegajacy != 0 then 
        raise_application_error(-20001,'Dydaktyk ma podwladnych');
    end if;
end;
--zostawiamy, nie dziala, potrzeba by procedury    

delete from dydaktyk where idosoba=1;
    
select count(1) from dydaktyk d left join dydaktyk p on d.idosoba=p.podlega where p.idosoba is null and d.idosoba=1;

--3
create or replace trigger studentowy
before insert on student for each row
declare v_eska student.nrindeksu%type;
begin
    if :new.nrindeksu is null then
        select 's' || Cast(Max(Substr(NrIndeksu, 2)) + 1 As Varchar(12)) into v_eska from Student;
        :new.nrindeksu:=v_eska;
        dbms_output.put_line('Wygenerowano '||v_eska);
    end if;
end;

insert into student (idosoba, datarekrutacji) values (32, '2015-04-02');

select * from student;


--5
create or replace trigger place
before insert or update on dydaktyk for each row
begin
    if :new.placa<2000 then
        :new.placa:=2000;
    end if;
end;
    
update dydaktyk set placa = 3000 where idosoba=1;
select * from dydaktyk;

--6
create table budzet (Wartosc int not null, DataAktualizacji Date);
insert into budzet select sum(nvl(placa, 0)), sysdate from dydaktyk;

select * from budzet
select nvl(sum(placa), 0), sysdate from dydaktyk;

