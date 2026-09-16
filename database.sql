drop sequence seq_authors;
drop sequence seq_books;
drop table authors;
drop table books;


CREATE TABLE AUTHORS (
    ID                                              VARCHAR( 10 ),
    NAME                                            VARCHAR( 30 ),
    NATIONALITY                                     VARCHAR( 30 ),

    CONSTRAINT "PK_AUTHORS"                         PRIMARY KEY ( ID ),
    CONSTRAINT "NN_AUTHORS.NAME"                    CHECK ( NAME IS NOT NULL )
);

CREATE TABLE BOOKS (
    ID                                              VARCHAR( 10 ),
    TITLE                                           VARCHAR( 60 ),
    YEAR                                            VARCHAR( 5 ),
    AUTHOR_ID                                       VARCHAR( 10 ),

    CONSTRAINT "PK_BOOKS"                           PRIMARY KEY ( ID ),
    CONSTRAINT "NN_BOOKS.TITLE"                     CHECK ( TITLE IS NOT NULL ),
    CONSTRAINT "FK_BOOKS_AUTHOR_ID"                 FOREIGN KEY ( AUTHOR_ID ) REFERENCES AUTHORS ( ID )
);

CREATE SEQUENCE seq_authors                         MINVALUE 1 MAXVALUE 999999998 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_books                           MINVALUE 1 MAXVALUE 999999998 START WITH 1 INCREMENT BY 1;


INSERT INTO AUTHORS (ID, NAME, NATIONALITY) VALUES ('A001', 'Gabriel García Márquez', 'Colombia');
INSERT INTO AUTHORS (ID, NAME, NATIONALITY) VALUES ('A002', 'J.K. Rowling', 'Reino Unido');
INSERT INTO AUTHORS (ID, NAME, NATIONALITY) VALUES ('A003', 'George Orwell', 'Reino Unido');
INSERT INTO AUTHORS (ID, NAME, NATIONALITY) VALUES ('A004', 'Jane Austen', 'Reino Unido');
INSERT INTO AUTHORS (ID, NAME, NATIONALITY) VALUES ('A005', 'Miguel de Cervantes', 'España');
INSERT INTO AUTHORS (ID, NAME, NATIONALITY) VALUES ('A006', 'Mark Twain', 'Estados Unidos');
INSERT INTO AUTHORS (ID, NAME, NATIONALITY) VALUES ('A007', 'Franz Kafka', 'Austria');
INSERT INTO AUTHORS (ID, NAME, NATIONALITY) VALUES ('A008', 'F. Scott Fitzgerald', 'Estados Unidos');
INSERT INTO AUTHORS (ID, NAME, NATIONALITY) VALUES ('A009', 'Leo Tolstoy', 'Rusia');
INSERT INTO AUTHORS (ID, NAME, NATIONALITY) VALUES ('A010', 'Haruki Murakami', 'Japón');


INSERT INTO BOOKS (ID, TITLE, YEAR, AUTHOR_ID) VALUES ('B001', 'Cien años de soledad', '1967', 'A001');
INSERT INTO BOOKS (ID, TITLE, YEAR, AUTHOR_ID) VALUES ('B002', 'El amor en los tiempos del cólera', '1985', 'A001');
INSERT INTO BOOKS (ID, TITLE, YEAR, AUTHOR_ID) VALUES ('B003', 'Harry Potter y la piedra filosofal', '1997', 'A002');
INSERT INTO BOOKS (ID, TITLE, YEAR, AUTHOR_ID) VALUES ('B004', '1984', '1949', 'A003');
INSERT INTO BOOKS (ID, TITLE, YEAR, AUTHOR_ID) VALUES ('B005', 'Orgullo y prejuicio', '1813', 'A004');
INSERT INTO BOOKS (ID, TITLE, YEAR, AUTHOR_ID) VALUES ('B006', 'Don Quijote de la Mancha', '1605', 'A005');
INSERT INTO BOOKS (ID, TITLE, YEAR, AUTHOR_ID) VALUES ('B007', 'Las aventuras de Tom Sawyer', '1876', 'A006');
INSERT INTO BOOKS (ID, TITLE, YEAR, AUTHOR_ID) VALUES ('B008', 'El gran Gatsby', '1925', 'A008');
INSERT INTO BOOKS (ID, TITLE, YEAR, AUTHOR_ID) VALUES ('B009', 'Ana Karenina', '1878', 'A009');
INSERT INTO BOOKS (ID, TITLE, YEAR, AUTHOR_ID) VALUES ('B010', 'Kafka en la orilla', '2002', 'A010');

COMMIT;