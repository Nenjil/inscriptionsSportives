#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------
DROP DATABASE inscription;
CREATE DATABASE inscription DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
Use inscription;

#------------------------------------------------------------
# Table: CANDIDAT
#------------------------------------------------------------

CREATE TABLE CANDIDAT(
        Num_Candidat Int  Auto_increment NOT NULL ,
        Nom_Candidat Varchar (30) NOT NULL
	,CONSTRAINT CANDIDAT_PK PRIMARY KEY (Num_Candidat)
)ENGINE=InnoDB;



#------------------------------------------------------------
# Table: COMPETITION
#------------------------------------------------------------

CREATE TABLE COMPETITION(
        Num_Competition  Int  Auto_increment NOT NULL ,
        Nom_Competition  Varchar (50) NOT NULL ,
        DateCloture Date NOT NULL,
		EstenEquipe   Bool NOT NULL
	,CONSTRAINT COMPETITION_PK PRIMARY KEY (Num_Competition)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: PERSONNE
#------------------------------------------------------------

CREATE TABLE PERSONNE(
        Num_Candidat    Int NOT NULL ,
        Prenom_Personne Varchar (30) NOT NULL ,
        Mail_Personne   Varchar (50) NOT NULL ,
        Nom_Candidat    Varchar (30) NOT NULL
	,CONSTRAINT PERSONNE_PK PRIMARY KEY (Num_Candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: EQUIPE
#------------------------------------------------------------

CREATE TABLE EQUIPE(
        Num_Candidat Int NOT NULL ,
        Nom_Candidat Varchar (30) NOT NULL
	,CONSTRAINT EQUIPE_PK PRIMARY KEY (Num_Candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CONSTITUER
#------------------------------------------------------------

CREATE TABLE CONSTITUER(
        Num_Candidat    Int NOT NULL ,
        Num_Competition Int NOT NULL
	,CONSTRAINT CONSTITUER_PK PRIMARY KEY (Num_Candidat,Num_Competition)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: COMPOSER
#------------------------------------------------------------

CREATE TABLE COMPOSER(
        Num_candidat        Int NOT NULL ,
        Num_candidat_Equipe Int NOT NULL
	,CONSTRAINT composer_PK PRIMARY KEY (Num_candidat,Num_candidat_Equipe)

)ENGINE=InnoDB;