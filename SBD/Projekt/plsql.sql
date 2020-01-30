--procedura pokazuje wszystkie ksiazki wypozyczone przez danego klienta
set serveroutput on;
create or replace procedure pokaz_ksiazki (v_pesel varchar2) is
    cursor peselkowy is select tytulKsiazki from Ksiazka k 
        left join wydanie w on idKsiazki=Ksiazka_idKsiazki 
        left join egzemplarz e on idWydania=Wydanie_idWydania
        left join wypozyczenie wyp on idEgzemplarza=Egzemplarz_idEgzemplarza
        left join Czytelnik c on Czytelnik_idCzytelnika=idCzytelnika
        where c.pesel=v_pesel;
    v_tytul varchar2(20);
begin
    open peselkowy;
    dbms_output.put_line('Lista wypozyczonych ksiazek:');
    loop
    fetch peselkowy into v_tytul;
    exit when peselkowy%notfound;
    dbms_output.put_line(v_tytul);
    end loop;
    close peselkowy;
end;

call pokaz_ksiazki('56031918973');

--procedura sprawdza czy klient ma wypozyczone ksiazki
create or replace procedure czy_ksiazki (v_pesel varchar2) is
    v_ile int;
begin
    select count(1) into v_ile from Ksiazka k 
        left join wydanie w on idKsiazki=Ksiazka_idKsiazki 
        left join egzemplarz e on idWydania=Wydanie_idWydania
        left join wypozyczenie wyp on idEgzemplarza=Egzemplarz_idEgzemplarza
        left join Czytelnik c on Czytelnik_idCzytelnika=idCzytelnika
        where c.pesel=v_pesel;
    if v_ile>0 then
        dbms_output.put_line('Klient ma wypozyczone '||v_ile||' ksiazek');
    else
        dbms_output.put_line('Klient nie ma wypozyczonych ksiazek');
    end if;
    
end;

call czy_ksiazki('56031918973');
call czy_ksiazki('56031918972');

--wyzwalacz blokuje zmiane peselu
create or replace trigger zmieniajacy
    after update of pesel on czytelnik
    for each row
begin
    raise_application_error(-20001,'Nie mozna zmieniac pesela');
end;


update czytelnik set pesel='123' where idczytelnika=2;

--wyzwalacz blokuje dodanie pracownika z dodatkiem do pensji na start
create or replace trigger dodatek
    after insert on pracownik
    for each row
begin
    if :new.dodatekdopensji>0 then
        raise_application_error(-20001,'Nie mozna dodac pracownika z dodatkiem do pensji');
    end if;
end;

insert into pracownik values ('8','Maslo','Pomocy','3','300');