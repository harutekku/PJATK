use s18688;

exec sp_fulltext_database 'enable';

create table test_ft (Id int identity primary key, dane varchar(max));

insert into test_ft (dane) values ('Chrząszcz brzmi w trzcinie w Szczebrzeszynie');
insert into test_ft (dane) values ('W Szczebrzeszynie chrząszcz brzmi w trzcinie');
insert into test_ft (dane) values ('W Szczebrzeszynie brzmi chrząszcz w trzcinie');
insert into test_ft (dane) values ('W Szczebrzeszynie brzmią chrząszcze w trzcinach');

select * from test_ft where contains(dane,'chrząszcz');


select * from test_ft where contains(dane,'Szczebrzeszynie AND W');
select * from test_ft where contains(dane,'Szczebrzeszynie OR W');

select * from test_ft where contains(dane,'"*chrząszcz*"');

select * from test_ft where contains(dane,'NEAR((chrząszcz,trzcinie),1,TRUE)');
select * from test_ft where contains(dane,'NEAR((chrząszcz,trzcinie),3,TRUE)');

select * from test_ft where contains(dane,'FORMSOF(INFLECTIONAL, trzcina)', LANGUAGE 'Polish');

select * from test_ft where freetext(dane,'trzcina słonecznik jezioro', language 'Polish');

select * from test_ft inner join freetexttable(test_ft, dane, 'brzmi w trzcinie', language 'Polish') as key_tbl 
	on test_ft.id = key_tbl.[key] order by key_tbl.rank desc;

select * from test_ft inner join freetexttable(test_ft, dane, 'brzmi w trzcinie', language 'Polish', 3) as key_tbl 
	on test_ft.id = key_tbl.[key] order by key_tbl.rank desc;

