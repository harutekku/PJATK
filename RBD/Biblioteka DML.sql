insert into rola values ('1','Szef','4000','Szefowanie');
insert into rola values ('2','Kierownik','3000','Kierownikowanie');
insert into rola values ('3','Bibliotekarz','2500','Wypozyczanie ksiazek');

insert into pracownik values ('1','Miron','Sawicki','1','500');
insert into pracownik values ('2','Kazia','Kucharska','2','300');
insert into pracownik values ('3','Oliwia','Walczak','3','0');
insert into pracownik values ('4','Kuba','Jasiñski','2','100');
insert into pracownik values ('5','Tekla','WoŸniak','3','100');
insert into pracownik values ('6','Hieronim','Kalinowski','3','0');
insert into pracownik values ('7','Renard','Kaczmarek','3','300');

insert into Czytelnik values ('1','Oskar','Borkowski','56031918973','Lwowska 80 42-400 Zawiercie');
insert into Czytelnik values ('2','Urjasz','Paw³owski','87052478070','Alpejska 95 85-790 Bydgoszcz');
insert into Czytelnik values ('3','Tytus','Ostrowski','40021803877','Biskupiñska 43 60-463 Poznañ');
insert into Czytelnik values ('4','Sylwia','Kwiatkowska','81071786769','Wawelska 9 80-034 Gdañsk');
insert into Czytelnik values ('5','Józefa','Kowalczyk','57040300245','Szronowa 73 60-375 Poznañ');

insert into kategoria values ('1','Obyczajowe');
insert into kategoria values ('2','Nauka');
insert into kategoria values ('3','Historyczne');
insert into kategoria values ('4','Przygodowe');
insert into kategoria values ('5','Poezja');

insert into autor values ('1','Nicholas','Sparks');
insert into autor values ('2','Aleksander','Kamiñski');
insert into autor values ('3','Henryk','Sienkiewicz');
insert into autor values ('4','Bruce','Cameron');
insert into autor values ('5','Diana','Setterfield');
insert into autor values ('6','Diana','Gabaldon');

insert into ksiazka values ('1','List w butelce','1','1','Smutna historia o znalezionym liœcie');
insert into ksiazka values ('2','Szczêœciarz','1','1','Opowieœæ o podró¿y ¿o³nierza do ukochanej');
insert into ksiazka values ('3','Kamienie na szaniec','2','3','Opowieœæ o bohaterach szarych szeregów');
insert into ksiazka values ('4','Latarnik','3','4','Opowieœæ o ¿yciu latarnika');
insert into ksiazka values ('5','Ogniem i mieczem','3','3','Opowieœæ o historii Polski');
insert into ksiazka values ('6','By³ sobie pies','4','4','Przygodowa o piesku');
insert into ksiazka values ('7','Trzynasta opowieœæ','5','1','Opowieœæ o dwóch przyjació³kach');
insert into ksiazka values ('8','Podró¿niczka','5','4','Opowieœæ o kobiecie która uciek³a podczas wojny');

insert into wydawnictwo values ('1','Albatros');
insert into wydawnictwo values ('2','Nasza ksiêgarnia');
insert into wydawnictwo values ('3','Pañstwowe Zak³ady Wydawnictw Szkolnych');
insert into wydawnictwo values ('4','Zielona Sowa');
insert into wydawnictwo values ('5','Wydawnictwo Kobiece');
insert into wydawnictwo values ('6','Amber');
insert into wydawnictwo values ('7','Œwiat Ksi¹¿ki');

insert into wydanie values ('1','9788379856770','1','1','2015/11/23');
insert into wydanie values ('2','9788379856794','1','2','2015/12/24');
insert into wydanie values ('3','9788310126542','2','3','2014/02/26');
insert into wydanie values ('4','9788365755599','3','4','2000/01/01');
insert into wydanie values ('5','9788376235103','4','5','2010/01/01');
insert into wydanie values ('6','9788365506986','5','6','2017/02/15');
insert into wydanie values ('7','9788324162529','6','7','2017/03/09');
insert into wydanie values ('8','9788379439256','7','8','2015/05/06');
insert into wydanie values ('9','9788375178340','7','4','2017/02/22');
insert into wydanie values ('10','9788308060155','7','5','2016/02/20');

insert into Egzemplarz values ('1','1','2018');
insert into Egzemplarz values ('2','2','2018');
insert into Egzemplarz values ('3','3','2018');
insert into Egzemplarz values ('4','4','2018');
insert into Egzemplarz values ('5','5','2018');
insert into Egzemplarz values ('6','6','2018');
insert into Egzemplarz values ('7','7','2018');
insert into Egzemplarz values ('8','8','2018');
insert into Egzemplarz values ('9','9','2018');
insert into Egzemplarz values ('10','10','2018');
insert into Egzemplarz values ('11','9','2019');
insert into Egzemplarz values ('12','10','2019');
insert into Egzemplarz values ('13','9','2019');
insert into Egzemplarz values ('14','10','2019');
insert into Egzemplarz values ('15','6','2019');

insert into wypozyczenie values ('1','9','3','3','2018/05/11','2018/06/14');
insert into wypozyczenie values ('2','10','2','3','2018/05/13','2018/08/15');
insert into wypozyczenie values ('3','3','4','3','2018/06/01','2018/06/10');
insert into wypozyczenie values ('4','10','3','5','2018/06/14','2018/09/10');
insert into wypozyczenie values ('5','2','1','5','2018/09/10','2018/10/13');
insert into wypozyczenie values ('6','4','3','6','2018/09/10','2018/12/20');
insert into wypozyczenie values ('7','5','2','7','2018/09/10','2019/01/22');
insert into wypozyczenie values ('8','6','3','7','2018/09/10','2018/12/20');
insert into wypozyczenie values ('9','10','1','3','2018/10/13','2019/01/20');
insert into wypozyczenie values ('10','14','3','6','2018/12/20','2019/02/04');
insert into wypozyczenie values ('11','15','1','5','2019/01/20','');
insert into wypozyczenie values ('12','7','2','7','2019/01/22','2019/04/03');
insert into wypozyczenie values ('13','8','4','5','2019/01/30','');
insert into wypozyczenie values ('14','3','3','5','2019/02/04','');
insert into wypozyczenie values ('15','1','2','3','2019/04/03','');

commit;
