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

insert into groups values ('bastien', 'user' );
insert into groups values ('gilles', 'user' );
insert into groups values ('antoine', 'user' );
insert into groups values ('christian', 'user' );