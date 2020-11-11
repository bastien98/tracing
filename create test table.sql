create table tests (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
username varchar(20),
status varchar(20),
notified varchar(20));
