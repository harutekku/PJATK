//1.
select imie, nazwisko from osoba 
			inner join student on osoba.idosoba=student.idosoba 
			inner join ocena on ocena.idstudent=student.idosoba 
			inner join przedmiot on ocena.idprzedmiot=przedmiot.idprzedmiot 
			where symbol like 'RBD' and ocena>4;
//2
select count(ocena), przedmiot from ocena 
			right join przedmiot on ocena.idprzedmiot = przedmiot.idprzedmiot 
			group by przedmiot;
//3
select imie, nazwisko from osoba 
			inner join student on osoba.idosoba=student.idosoba 
					where DATEDIFF(Year,DataRekrutacji,GETDATE()) = 
							(select DATEDIFF(Year,DataRekrutacji,GETDATE()) from student 
							inner join osoba on osoba.idosoba=student.idosoba 
							where imie like 'Salomon' and nazwisko like 'Seler');
//4
select imie, nazwisko from osoba 
			inner join dydaktyk on osoba.idOsoba=dydaktyk.idOsoba 
			left join ocena on dydaktyk.idOsoba=ocena.idDydaktyk 
			where ocena is null;
//5
delete from osoba where imie = 'Sykstus' and nazwisko = 'Szczaw'; 

