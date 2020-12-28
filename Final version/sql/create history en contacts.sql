drop table contacts;
drop table history;


create table contacts (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
sort varchar(20),
addedBy varchar(20),
CSeen varchar(20) );

create table history (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
username varchar(20) ,
idContact int );


