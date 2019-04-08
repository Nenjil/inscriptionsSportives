package Passerelle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public abstract class Passerelle
{
	 private static EntityManagerFactory emf = null;
	 private static EntityManager em = null;
	
	 public static void initHibernate()
	 {
		 
	  try
	  {
		emf = Persistence.createEntityManagerFactory("Unit_Inscription");
	    em = emf.createEntityManager();
	  }
	  catch (HibernateException ex)
	  {
	   throw new RuntimeException("Probleme de configuration : "
	     + ex.getMessage(), ex);
	  }
	 }
	
	 public static void open()
	 {
	  if (em == null)
	   initHibernate();
	 }
	
	 public static boolean isOpened()
	 {
	  return emf != null && em != null ;
	 }
	 
	 public static void close()
	 {
	  if (isOpened())
	   em.close();
	   emf.close();
	 }
	
	 public static void delete(Object o)
	 {
	  EntityTransaction transaction = em.getTransaction();
	  transaction.begin();
	  em.remove(o);
	  transaction.commit();
	  transaction = null;
	 // em.flush();
	 }
	
	 public static void save(Object o)
	 {
		 EntityTransaction transaction = em.getTransaction();
		 transaction.begin();
		 em.persist(o);
		 transaction.commit();
		 transaction = null;
		// em.flush();
	
	 }
	
	 @SuppressWarnings("unchecked")
	 public static <T> List<T> getData(String className)
	 {
		 List<T> results=  em.createQuery("from " + className).getResultList();
		 return results;
	 }
	
	 @SuppressWarnings("unchecked")
	 public static <T> T getData(String className, int id)
	 {
		 List<T> result =  em.createQuery("from " + className + " where num = "
	     + id).getResultList();
		 return result.get(0);
	 }
	 
	 
	public static Inscriptions getInscriptionsFromBdd(Inscriptions inscription) {		
		setPersonneFromBdd(inscription);
		setEquipesFromBdd(inscription);
		setCompetitionsFromBdd(inscription);
	
		return inscription; 
	 }
	
	private static Inscriptions setPersonneFromBdd(Inscriptions inscription) {
		@SuppressWarnings("unchecked")
		List<Personne> personnes =  em.createQuery("from Personne").getResultList();
		for (Personne personne : personnes) {
			inscription.createPersonne(personne.getNom(), personne.getPrenom(), personne.getMail());	
		}
		return inscription;
	}
	
	private static Inscriptions setEquipesFromBdd(Inscriptions inscription) {
		
		@SuppressWarnings("unchecked")
		List<Equipe> equipes =  em.createQuery("from Equipe").getResultList();
		for (Equipe equipe : equipes) {
			if(!inscription.getCandidats().contains(equipe)) {
				@SuppressWarnings("unused")
				Equipe team = inscription.createEquipe(equipe.getNom());
				
			}
		}
		return inscription;
	}

	private static Inscriptions setCompetitionsFromBdd(Inscriptions inscription) {
		@SuppressWarnings("unchecked")
		List<Competition>compets = em.createQuery("from Competition").getResultList();
		for (Competition compet : compets) {
			if(!inscription.getCompetitions().contains(compet)) {
				Competition compete =inscription.createCompetition(compet.getNom(),compet.getDateCloture(),compet.estEnEquipe());

			}
		}

		return inscription;
	}
}