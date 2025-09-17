DELETE FROM LIBRARY.ROLE_ENTITY r WHERE r.role in ('STAFF', 'ADMIN','LIBRARIAN');
insert into LIBRARY.ROLE_ENTITY r (r.role) values ('STAFF');
insert into LIBRARY.ROLE_ENTITY r (r.role) values ('ADMIN');
insert into LIBRARY.ROLE_ENTITY r (r.role) values ('LIBRARIAN');
