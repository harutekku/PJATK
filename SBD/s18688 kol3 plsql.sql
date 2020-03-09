--Jakub Pawlowicz s18688


--1

set serveroutput on;
declare
    v_krasnale int;
begin
    select count(1) into v_krasnale from magazyn where nazwa = 'krasnal ogrodowy';
    if v_krasnale = 1 then
        select stan into v_krasnale from magazyn where nazwa = 'krasnal ogrodowy';
        if v_krasnale>=5 then 
            update magazyn set stan = v_krasnale-1 where nazwa = 'krasnal ogrodowy';
        else
            dbms_output.put_line('Jest mniej niz 5 krasnali');
        end if;
    else
        raise_application_error(-20001,'Nie istnieje taki rekord');
    end if;
end;



--2

create or replace trigger towarowy
after insert or update on magazyn
for each row
begin
    if inserting then
        if :new.cena < 0 then
            raise_application_error(-20001, 'Podano cene mniejsza od zera');
        end if;
    elsif updating then
        if :new.nazwa != :old.nazwa then
            raise_application_error(-20001, 'Nie mozna zmieniac nazwy towaru');
        end if;
        if :new.cena > :old.cena*1.1 or :new.cena < :old.cena*0.9 then
            raise_application_error(-20002, 'Za wysoka zmiana ceny towaru');
        end if;
    end if;
end;
