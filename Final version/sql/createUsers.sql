drop table tests;
drop table groups;
drop table users;


create table users (
username varchar(20) primary key,
password varchar(20) );

create table groups (
username varchar(20) references users primary key,
groupp varchar(20) );

insert into users values ('bastien', 'moenaert');
insert into users values ('gilles', 'moenaert'); 
insert into users values ('antoine', 'moenaert');
insert into users values ('christian', 'moenaert');
insert into users values ('karim', 'hakou');


insert into groups values ('bastien', 'user' );
insert into groups values ('gilles', 'user' );
insert into groups values ('antoine', 'user' );
insert into groups values ('christian', 'user' );
insert into groups values ('karim', 'docter' );


create table tests (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
username varchar(20),
status varchar(20),
notified varchar(20),
seen varchar(20),
docname varchar(20),
FOREIGN KEY(docname) REFERENCES USERS(username));
