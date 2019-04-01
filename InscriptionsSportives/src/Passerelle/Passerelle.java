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
	
	 static void delete(Object o)
	 {
	  EntityTransaction transaction = em.getTransaction();
	  transaction.begin();
	  em.remove(o);
	  transaction.commit();
	  transaction = null;
	  em.flush();
	 }
	
	 public static void save(Object o)
	 {
		 EntityTransaction transaction = em.getTransaction();
		 transaction.begin();
		 em.persist(o);
		 transaction.commit();
		 transaction = null;
		 em.flush();
	
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
				Equipe team = inscription.createEquipe(equipe.getNom());
				@SuppressWarnings("unchecked")
				List<Personne> membres =  em.createQuery("select membres from Equipe as equipe where equipe.nom=:team ").setParameter("team", team.getNom()).getResultList();
				for (Personne membre :membres) {
					if(!team.getMembres().contains(membre)) {
						//System.out.println("Avant"+team.getMembres());
						team.add(membre);
						//System.out.println(membre.getNom()+" ajoute à "+ team.getNom());
						//System.out.println("Apres"+team.getMembres());
					}
					
				}
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
				System.out.println(compete.getNom()+" a ete crée");
//				@SuppressWarnings({ "unchecked", "unused" })
//				List<Candidat> candidats = em.createQuery("select candidats from Competition as compet where compet.nom =:nom and compet.dateCloture=:date ")
//				.setParameter("nom", compete.getNom()).setParameter("date", compete.getDateCloture()).getResultList();
//				for (Candidat candi : compet.getCandidats()) {
//					if(!compete.getCandidats().contains(candi)) {
//						if(candi instanceof Personne) {
//						compete.add((Personne) candi);}
//						else {
//							compete.add((Equipe) candi);
//						}
//					System.out.println(candi.getNom()+"a ete ajouté a la compet : "+compete.getNom());
//					}
//				}
			}
		}

		return inscription;
	}
}