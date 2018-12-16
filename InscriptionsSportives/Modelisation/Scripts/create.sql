#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------
DROP DATABASE inscription;
CREATE DATABASE inscription DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
Use inscription;

#------------------------------------------------------------
# Table: Competition
#------------------------------------------------------------

CREATE TABLE Competition(
        NumCompet     Int  Auto_increment  NOT NULL ,
        LibelleCompet Varchar (50) NOT NULL ,
        Date_cloture  Date NOT NULL ,
        EstenEquipe   Bool NOT NULL
	,CONSTRAINT Competition_PK PRIMARY KEY (NumCompet)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Candidat
#------------------------------------------------------------

CREATE TABLE Candidat(
        num_candidat Int  Auto_increment  NOT NULL ,
        nom_candidat Varchar (50) NOT NULL
	,CONSTRAINT Candidat_PK PRIMARY KEY (num_candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Personne
#------------------------------------------------------------

CREATE TABLE Personne(
        num_candidat Int NOT NULL ,
        prenom       Varchar (50) NOT NULL ,
        mail         Varchar (50) NOT NULL ,
        nom_candidat Varchar (50) NOT NULL
	,CONSTRAINT Personne_PK PRIMARY KEY (num_candidat)
	)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Equipe
#------------------------------------------------------------

CREATE TABLE Equipe(
        num_candidat Int NOT NULL ,
        nom_candidat Varchar (50) NOT NULL
	,CONSTRAINT Equipe_PK PRIMARY KEY (num_candidat)
	)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: constituer
#------------------------------------------------------------

CREATE TABLE constituer(
        num_candidat Int NOT NULL ,
        NumCompet    Int NOT NULL
	,CONSTRAINT constituer_PK PRIMARY KEY (num_candidat,NumCompet)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: composer
#------------------------------------------------------------

CREATE TABLE composer(
        num_candidat_Personne Int NOT NULL ,
        num_candidat_Equipe Int NOT NULL
	,CONSTRAINT composer_PK PRIMARY KEY (num_candidat_Personne,num_candidat_Equipe)
)ENGINE=InnoDB;

