drop table tests;

create table tests (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
username varchar(20),
status varchar(20),
notified varchar(20),
seen varchar(20),
docname varchar(20),
FOREIGN KEY(docname) REFERENCES USERS(username));
