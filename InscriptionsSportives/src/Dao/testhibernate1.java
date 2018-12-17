package Dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import inscriptions.Competition;
import inscriptions.Inscriptions;

public class testhibernate1 {

public static void main(String[] args)  {
	
	EntityManagerFactory emf = null ; 
	EntityManager em = null ; 
	
	try {
		emf = Persistence.createEntityManagerFactory("Unit_Inscription");
		em = emf.createEntityManager();
		
		//Recuperer toute les competitions
		 List<Competition> compets = em.createQuery( "from Competition", Competition.class )
		 .getResultList();
		 System.out.println("----------------------------");
		 System.out.println("Voici les competitions avant l'ajout :");
		 for (Competition compet : compets) {
			 System.out.println( "-"+ compet ); 
		 }
		 System.out.println("----------------------------");
		 System.out.println("----------------------------");
		 System.out.println( "Insertion d'un nouvelle competition" );
		 Boolean alreadycreated=false;
		 EntityTransaction transac = em.getTransaction();
		 transac.begin();
		 Competition compettest = Inscriptions.getInscriptions().createCompetition("Compet d'hibernate", LocalDate.parse("2018-12-18"), false);
		 for (Competition compet : compets) {
		 if (compet.compareTo(compettest)==0) {
		 System.out.println("la compétition "+compettest+" existe deja");
		 alreadycreated = true;
		 compettest=compet;}
		 }
		 if (!alreadycreated) {
			 System.out.println(compettest+"vient d'être ajouté a la base de donnée");
		 em.persist( compettest );
		 }
		 
		 System.out.println("----------------------------"); 
		 System.out.println("----------------------------");
		 
		 if(alreadycreated) {
		 System.out.println( "- Modification de la competition deja crée" );    
		 System.out.println(compettest+"va etre modifiée");
		 compettest.setNom("Compet d'hibernate updated");
		 em.persist( compettest );}
		 System.out.println("----------------------------"); 
		 System.out.println("----------------------------");
		   //Recuperer toute les competitions
		  compets = em.createQuery( "from Competition", Competition.class )
		    .getResultList();
		 System.out.println("Voici les competitions apres l'ajout :");
		 for (Competition compet : compets) {
			 System.out.println( "-"+ compet );
			 
		}
	   	transac.commit();
	}
	catch(PersistenceException exception){
	     System.out.println("Problem de persistence");
	     exception.printStackTrace();
	}

	finally {
		if (em != null) em.close();
		if(emf !=null) emf.close();
	}
	
	
	
}	
	
}
