--4
CREATE LOGIN User2 WITH PASSWORD = 'P@ssw0rd';
CREATE USER User2 FOR LOGIN User2;
GRANT SELECT ON testowa TO User2;
--5
GRANT UPDATE(dwa) on testowa TO User2;
--6
GRANT SELECT TO User2;
REVOKE SELECT TO User2;
--7
CREATE ROLE testowarola;
sp_addrolemember testowarola, User2;
GRANT INSERT TO testowarola;
--8
DENY INSERT TO User2;
--9
sp_addrolemember db_ddladmin, User2;
--10
CREATE SCHEMA schemat;
ALTER SCHEMA schemat TRANSFER dbo.druga;
select * from druga;
select * from schemat.druga;
--11
GRANT SELECT ON schema::schemat TO User2;