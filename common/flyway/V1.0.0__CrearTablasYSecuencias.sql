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
