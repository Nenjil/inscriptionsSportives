#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: CANDIDAT
#------------------------------------------------------------

CREATE TABLE CANDIDAT(
        Num_Candidat Int NOT NULL ,
        Nom_Candidat Varchar (20) NOT NULL
	,CONSTRAINT CANDIDAT_PK PRIMARY KEY (Num_Candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: COMPETITION
#------------------------------------------------------------

CREATE TABLE COMPETITION(
        Num_Competition  Int NOT NULL ,
        Nom_Competition  Varchar (50) NOT NULL ,
        DateCloture_Comp Date NOT NULL
	,CONSTRAINT COMPETITION_PK PRIMARY KEY (Num_Competition)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: PERSONNE
#------------------------------------------------------------

CREATE TABLE PERSONNE(
        Num_Candidat    Int NOT NULL ,
        Prenom_Personne Varchar (10) NOT NULL ,
        Mail_Personne   Varchar (50) NOT NULL ,
        Nom_Candidat    Varchar (20) NOT NULL
	,CONSTRAINT PERSONNE_PK PRIMARY KEY (Num_Candidat)

	,CONSTRAINT PERSONNE_CANDIDAT_FK FOREIGN KEY (Num_Candidat) REFERENCES CANDIDAT(Num_Candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: EQUIPE
#------------------------------------------------------------

CREATE TABLE EQUIPE(
        Num_Candidat Int NOT NULL ,
        Nom_Candidat Varchar (20) NOT NULL
	,CONSTRAINT EQUIPE_PK PRIMARY KEY (Num_Candidat)

	,CONSTRAINT EQUIPE_CANDIDAT_FK FOREIGN KEY (Num_Candidat) REFERENCES CANDIDAT(Num_Candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CONSTITUER
#------------------------------------------------------------

CREATE TABLE CONSTITUER(
        Num_Candidat    Int NOT NULL ,
        Num_Competition Int NOT NULL
	,CONSTRAINT CONSTITUER_PK PRIMARY KEY (Num_Candidat,Num_Competition)

	,CONSTRAINT CONSTITUER_CANDIDAT_FK FOREIGN KEY (Num_Candidat) REFERENCES CANDIDAT(Num_Candidat)
	,CONSTRAINT CONSTITUER_COMPETITION0_FK FOREIGN KEY (Num_Competition) REFERENCES COMPETITION(Num_Competition)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: COMPOSER
#------------------------------------------------------------

CREATE TABLE COMPOSER(
        Num_Candidat          Int NOT NULL ,
        Num_Candidat_PERSONNE Int NOT NULL
	,CONSTRAINT COMPOSER_PK PRIMARY KEY (Num_Candidat,Num_Candidat_PERSONNE)

	,CONSTRAINT COMPOSER_EQUIPE_FK FOREIGN KEY (Num_Candidat) REFERENCES EQUIPE(Num_Candidat)
	,CONSTRAINT COMPOSER_PERSONNE0_FK FOREIGN KEY (Num_Candidat_PERSONNE) REFERENCES PERSONNE(Num_Candidat)
)ENGINE=InnoDB;

