#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Competition
#------------------------------------------------------------

CREATE TABLE Competition(
        NumCompet     Int NOT NULL ,
        LibelleCompet Varchar (50) NOT NULL ,
        Date_cloture  Date NOT NULL ,
        EstenEquipe   Bool NOT NULL
	,CONSTRAINT Competition_PK PRIMARY KEY (NumCompet)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Candidat
#------------------------------------------------------------

CREATE TABLE Candidat(
        id_candidat  Int NOT NULL ,
        nom_candidat Varchar (50) NOT NULL
	,CONSTRAINT Candidat_PK PRIMARY KEY (id_candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Personne
#------------------------------------------------------------

CREATE TABLE Personne(
        id_candidat  Int NOT NULL ,
        prenom       Varchar (5) NOT NULL ,
        mail         Varchar (5) NOT NULL ,
        nom_candidat Varchar (50) NOT NULL
	,CONSTRAINT Personne_PK PRIMARY KEY (id_candidat)

	,CONSTRAINT Personne_Candidat_FK FOREIGN KEY (id_candidat) REFERENCES Candidat(id_candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Equipe
#------------------------------------------------------------

CREATE TABLE Equipe(
        id_candidat  Int NOT NULL ,
        nom_candidat Varchar (50) NOT NULL
	,CONSTRAINT Equipe_PK PRIMARY KEY (id_candidat)

	,CONSTRAINT Equipe_Candidat_FK FOREIGN KEY (id_candidat) REFERENCES Candidat(id_candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: participer
#------------------------------------------------------------

CREATE TABLE participer(
        id_candidat Int NOT NULL ,
        NumCompet   Int NOT NULL
	,CONSTRAINT participer_PK PRIMARY KEY (id_candidat,NumCompet)

	,CONSTRAINT participer_Candidat_FK FOREIGN KEY (id_candidat) REFERENCES Candidat(id_candidat)
	,CONSTRAINT participer_Competition0_FK FOREIGN KEY (NumCompet) REFERENCES Competition(NumCompet)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: composer
#------------------------------------------------------------

CREATE TABLE composer(
        id_candidat        Int NOT NULL ,
        id_candidat_Equipe Int NOT NULL
	,CONSTRAINT composer_PK PRIMARY KEY (id_candidat,id_candidat_Equipe)

	,CONSTRAINT composer_Personne_FK FOREIGN KEY (id_candidat) REFERENCES Personne(id_candidat)
	,CONSTRAINT composer_Equipe0_FK FOREIGN KEY (id_candidat_Equipe) REFERENCES Equipe(id_candidat)
)ENGINE=InnoDB;

