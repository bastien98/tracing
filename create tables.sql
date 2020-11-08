CREATE TABLE Contacts
(
ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
SORT VARCHAR(45) NOT NULL,
ADDEDBY INTEGER,
PRIMARY KEY (ID)
);

/*INSERT INTO CONTACTS (SORT,ADDEDBY) VALUES('nauw',1);
INSERT INTO CONTACTS (SORT,ADDEDBY) VALUES('middelmatig',3);
INSERT INTO CONTACTS (SORT,ADDEDBY) VALUES('veilig',2);*/

CREATE TABLE Users
(
ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
NAME VARCHAR(45) NOT NULL,
PASSWORD VARCHAR(45),
SORT VARCHAR(45),
PRIMARY KEY (ID)
);

INSERT INTO USERS (NAME,PASSWORD, SORT) values('bastien','moenaert','user');
INSERT INTO USERS (NAME,PASSWORD, SORT) values('gilles','moenaert','user');
INSERT INTO USERS (NAME,PASSWORD, SORT) values('antoine','moenaert','user');
INSERT INTO USERS (NAME,PASSWORD, SORT) values('christian','moenaert','user');
INSERT INTO USERS (NAME,PASSWORD,SORT) values('karim','hako','user');

CREATE TABLE History
(
ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
IDCONTACT INTEGER NOT NULL,
IDUSER INTEGER NOT NULL,
PRIMARY KEY (ID)
);



CREATE TABLE TESTS
(
ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
IDUSER INTEGER NOT NULL,
STATUS VARCHAR(45),
NOTIFIED INTEGER,
PRIMARY KEY (ID)
);
