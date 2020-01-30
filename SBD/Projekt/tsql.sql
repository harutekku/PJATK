--procedura pokazuje wszystkie ksiazki wypozyczone przez danego klienta
--create procedure pokaz_ksiazki @v_pesel varchar(11)
alter procedure pokaz_ksiazki @v_pesel varchar(11)
as
begin
	declare @v_tytul varchar(20);
    declare peselkowy cursor for select tytulKsiazki from Ksiazka k 
        left join wydanie w on idKsiazki=Ksiazka_idKsiazki 
        left join egzemplarz e on idWydania=Wydanie_idWydania
        left join wypozyczenie wyp on idEgzemplarza=Egzemplarz_idEgzemplarza
        left join Czytelnik c on Czytelnik_idCzytelnika=idCzytelnika
        where c.pesel = @v_pesel;
	print 'Lista wypozyczonych ksiazek:';
    open peselkowy;
	fetch next from peselkowy into @v_tytul;
	while @@FETCH_STATUS=0
		begin
		print @v_tytul;
		fetch next from peselkowy into @v_tytul;
		end;
	close peselkowy;
	deallocate peselkowy;
end;

exec pokaz_ksiazki '56031918973';


--procedura sprawdza czy klient ma wypozyczone ksiazki
--create procedure czy_ksiazki @v_pesel varchar(11)
alter procedure czy_ksiazki @v_pesel varchar(11)
as
begin
	declare @v_ile int;
    select @v_ile = count(1) from Ksiazka k 
        left join wydanie w on idKsiazki=Ksiazka_idKsiazki 
        left join egzemplarz e on idWydania=Wydanie_idWydania
        left join wypozyczenie wyp on idEgzemplarza=Egzemplarz_idEgzemplarza
        left join Czytelnik c on Czytelnik_idCzytelnika=idCzytelnika
        where c.pesel = @v_pesel;
    if @v_ile>0
		begin
		print 'Klient ma wypozyczone ' + cast(@V_ile as varchar) + ' ksiazek';
		end;
    else
		begin
        print 'Klient nie ma wypozyczonych ksiazek';
		end;
end;

exec czy_ksiazki '56031918973';
exec czy_ksiazki '56031918972';

--wyzwalacz blokuje zmiane peselu
create trigger zmieniajacy
	on czytelnik
	after update as
	declare @pesel varchar(11);
	declare @pesel2 varchar(11);
begin
	select @pesel = pesel from inserted;
	select @pesel2 = pesel from deleted;
	if @pesel!=@pesel2
	begin
		print 'Nie mozna zmieniac pesela' ;
		rollback
	end;
end;


update czytelnik set pesel='123' where idczytelnika=2;
update czytelnik set pesel='87052478070' where idczytelnika=2;

--wyzwalacz blokuje dodanie pracownika z dodatkiem do pensji na start
create trigger dodatek
	on pracownik
    after insert as
begin
    if (select dodatekdopensji from inserted)>0 
	begin
        print 'Nie mozna dodac pracownika z dodatkiem do pensji';
		rollback;
	end;
end;

insert into pracownik values ('8','Maslo','Pomocy','3','300');
