-- -----------------------------------------------------------------------------
--             G�n�ration d'une base de donn�es pour
--                           PostgreSQL
--                        (25/1/2023 14:42:02)
-- -----------------------------------------------------------------------------
--      Nom de la base : sudpaydb
--      Projet : Accueil Win'Design 5.6 
--      Auteur : AKBARALY
--      Date de derni�re modification : 25/1/2023 14:38:18
-- -----------------------------------------------------------------------------

-- -----------------------------------------------------------------------------
--       TABLE : AVANCE
-- -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS AVANCE
   (
    ID_CONCERNER int2 NOT NULL  ,
    ID_PRENDRE int2 NOT NULL  ,
    MONTANT money NOT NULL  ,
    ID int2 NOT NULL  ,
    DATE timestamp NOT NULL  
,   CONSTRAINT PK_AVANCE PRIMARY KEY (ID)
   );

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE AVANCE
-- -----------------------------------------------------------------------------

CREATE  INDEX IF NOT EXISTS I_FK_AVANCE_SALAIRE
     ON AVANCE (ID_CONCERNER ASC)
    ;

CREATE  INDEX IF NOT EXISTS I_FK_AVANCE_PERSONNEL
     ON AVANCE (ID_PRENDRE ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : PERSONNEL
-- -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PERSONNEL
   (
    ID int2 NOT NULL  ,
    NOM varchar(255) NOT NULL  ,
    PRENOM varchar(255) NOT NULL  ,
    TEL varchar(255) NOT NULL  ,
    ADRESSE varchar(255) NOT NULL  ,
    FONCTION varchar(255) NOT NULL  ,
    SALAIRE money NOT NULL  
,   CONSTRAINT PK_PERSONNEL PRIMARY KEY (ID)
   );

-- -----------------------------------------------------------------------------
--       TABLE : SALAIRE
-- -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS SALAIRE
   (
    ID int2 NOT NULL  ,
    ID_POSSEDER int2 NOT NULL  ,
    MONTANT char(32) NOT NULL  ,
    DATE date NOT NULL  
,   CONSTRAINT PK_SALAIRE PRIMARY KEY (ID)
   );

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE SALAIRE
-- -----------------------------------------------------------------------------

CREATE  INDEX IF NOT EXISTS I_FK_SALAIRE_PERSONNEL
     ON SALAIRE (ID_POSSEDER ASC)
    ;


-- -----------------------------------------------------------------------------
--       CREATION DES REFERENCES DE TABLE
-- -----------------------------------------------------------------------------


ALTER TABLE AVANCE ADD 
     CONSTRAINT FK_AVANCE_SALAIRE
          FOREIGN KEY (ID_CONCERNER)
               REFERENCES SALAIRE (ID);

ALTER TABLE AVANCE ADD 
     CONSTRAINT FK_AVANCE_PERSONNEL
          FOREIGN KEY (ID_PRENDRE)
               REFERENCES PERSONNEL (ID);

ALTER TABLE SALAIRE ADD 
     CONSTRAINT FK_SALAIRE_PERSONNEL
          FOREIGN KEY (ID_POSSEDER)
               REFERENCES PERSONNEL (ID);


CREATE SEQUENCE IF NOT EXISTS seq_personnel_id;
ALTER TABLE personnel ALTER COLUMN id SET DEFAULT nextval('seq_personnel_id');
CREATE SEQUENCE IF NOT EXISTS seq_avance_id;
ALTER TABLE avance ALTER COLUMN id SET DEFAULT nextval('seq_avance_id');
CREATE SEQUENCE IF NOT EXISTS seq_salaire_id;
ALTER TABLE salaire ALTER COLUMN id SET DEFAULT nextval('seq_salaire_id');

alter table personnel drop column salaire;
alter table personnel add column salaire float not null default 0;
alter table salaire drop column montant;
alter table salaire add column montant float not null default 0;

CREATE TABLE IF NOT EXISTS users (
     id INTEGER NOT NULL, 
     username VARCHAR(255) NOT NULL UNIQUE, 
     email VARCHAR(255) NOT NULL, 
     password VARCHAR(255) NOT NULL, 
     id_admin BOOLEAN NOT NULL DEFAULT false,
     CONSTRAINT pk_user PRIMARY KEY(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_user_id;
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('seq_user_id');
ALTER TABLE users ADD CONSTRAINT uniq_email UNIQUE (email);
ALTER TABLE personnel ADD COLUMN user_id INTEGER;
ALTER TABLE personnel ADD CONSTRAINT fk_personnel_user FOREIGN KEY (user_id) REFERENCES users(id);
-- -----------------------------------------------------------------------------
--                FIN DE GENERATION
-- -----------------------------------------------------------------------------