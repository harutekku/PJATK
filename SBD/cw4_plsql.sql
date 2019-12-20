--1
set serveroutput on
declare
    counter int;
begin
    select count(1) into counter from osoba;
    dbms_output.put_line('W tabeli jest '||counter||' rekordów');
end;

--2
set serveroutput on
declare
    counter int;
begin
    select count(1) into counter from dydaktyk;
    if counter<16 then
        declare
            osId osoba.idosoba%type;
        begin
            select max(idosoba) into osId from osoba;
            osId:=osId+1;
            insert into osoba(idosoba,nazwisko,imie) values(osId,'Cykoria','Celestyna');
            insert into dydaktyk(idosoba,idstopien) values(osId,(select idstopien from stopnietytuly where stopien='Doktor'));
            dbms_output.put_line('Zatrudniono Pania Celestyne Cykorie');
        end;
    else
        dbms_output.put_line('Brak wolnych etatów');
    end if;
end;

--3
alter table osoba add Plec char(1) null;
declare 
    cursor plec_kursor is SELECT idOsoba, imie from osoba;
    idos int;
    imos varchar(20);
    plos char(1);
begin
    open plec_kursor;
    loop
        fetch plec_kursor into idos, imos;
        exit when plec_kursor%notfound;
        if imos like 'Barnaba' then
            begin
                plos := 'm';
            end;
        elsif substr(imos,-1) like 'a' then
            begin
                plos := 'k';
            end;
        else
            begin
                plos := 'm';
            end;
        end if;
        update osoba set plec=plos where IdOsoba=idos;
    end loop;
    close plec_kursor;
end;


select * from osoba;
select * from stopnietytuly;
select * from dydaktyk;
