--1
SET SERVEROUTPUT ON
create or replace procedure daneZRoku 
    (rok in int)
is
begin
    declare
        im varchar(20); naz varchar(20); nri varchar(20);
        cursor cur is (select imie, nazwisko, nrindeksu from student left join osoba on student.idosoba=osoba.idosoba where extract(year from datarekrutacji) like rok);
    begin
        open cur;
        loop
            fetch cur into im, naz, nri;
            exit when cur%NOTFOUND;
            dbms_output.put_line(im||' '||naz||' '||nri);
        end loop;
        close cur;
    end;
end;
begin
    daneZRoku(2014);
end;
--2
create or replace procedure ileStud
    (rok in out int)
is
begin
    select count(1) into rok from student left join osoba on student.idosoba=osoba.idosoba where extract(year from datarekrutacji) like rok;
end;

declare
    var1 int:=2012;
begin
    ileStud(var1);
    dbms_output.put_line(var1);
end;
