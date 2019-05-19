package inscriptions.test;

import java.util.List;

import javax.persistence.*;

import inscriptions.Competition;

public class HibernateDemo {
	
	public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("Unit_Inscription");
            em = emf.createEntityManager();
        
            System.out.println( "- Lecture de toutes les compets -----------" );
            
            List<Competition> compets = em.createQuery( "from Competition", Competition.class ).getResultList();
            for (Competition compet : compets) {
                System.out.println( compet.getNom() + " "+ compet.getDateCloture() );
            }
         
         /*  
            System.out.println( "- Insertion d'une nouvelle compet ----------" );
            
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            
            Competition newCompet = Inscriptions.getInscriptions().createCompetition("CompetTest", LocalDate.parse("2019-12-18"), false);
            em.persist( newCompet );
    
            List<Competition> results = em.createQuery("from Competition", Competition.class).getResultList();
            for( Competition compet : results) {
                System.out.println( compet );
            }
    
            System.out.println( "- Modification d'une compet --------------" );
        
            newCompet.setNom("CompetModifiée");;
            em.persist( newCompet );
            
            results = em.createQuery("from Competition", Competition.class).getResultList();
            for( Competition compet : results) {
                System.out.println( compet );
            }
    /*
           System.out.println( "- Suppression d'une compet ---------------" );
            
            em.remove( newCompet );
            
            results = em.createQuery("from Competition", Competition.class).getResultList();
            for( Competition compet : results) {
                System.out.println( compet );
            }      
            trans.commit();*/
        } finally {
            if ( em != null ) em.close();
            if ( emf != null ) emf.close();
        }
    }

}
