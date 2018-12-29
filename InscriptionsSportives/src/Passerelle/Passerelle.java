package Passerelle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

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
	
	 static void save(Object o)
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
		inscription = setPersonneFromBdd(inscription);
		inscription = setEquipesFromBdd(inscription);
		inscription = setCompetitionsFromBdd(inscription);
		inscription = setCompetCandidatsFromBdd(inscription);
	
		return inscription; 
	 }
	
	// !!!!!!!! Cette methode est a revoir car  trop gourmande 3 boucles imbriquées !!!!!!!!!
	private static Inscriptions setJoueursFromBdd(Inscriptions inscription) {
		@SuppressWarnings("unchecked")
		List<Personne> membres =  em.createQuery("select membres from Equipe ").getResultList();
		//pour chaque equipe deja crée dans java dans (le garbage) 
		for (Equipe equipe : inscription.getEquipes()) {
			// on recupere les membres d'equipe de la bDD
			for (Personne membre : membres) {
				// on parcoure chaque equipe de ses membres
				for (Equipe team : membre.getEquipes()) {
					// si l'equipe de la bdd est egale a l'equipe crée dans java on y ajoute tout ses membres
					if(team.getNom().equals(equipe.getNom()) && !equipe.getMembres().contains(membre)) {
						equipe.add(membre);
					//	System.out.println(membre.getNom()+" ajouté a "+equipe.getNom());
					}				
				}	
			}	
		}
		System.out.println(inscription);
		return inscription;
	}

	// !!!!!!!! Cette methode est a revoir car  trop gourmande 3 boucles imbriquées !!!!!!!!!
	private static Inscriptions setCompetCandidatsFromBdd(Inscriptions inscription) {
		@SuppressWarnings("unchecked")
		List<Candidat> candidats = em.createQuery("select candidats from Competition").getResultList();
		for (Competition compet : inscription.getCompetitions()) {
			for (Candidat candidat : candidats) {
				for (Competition compett : candidat.getCompetitions()) {
					if(compett.getNom().equals(compet.getNom())){
						if(candidat instanceof Personne)
							compet.add((Personne)candidat);
						else
							compet.add((Equipe)candidat);
					}
				}
			}	
		}	
		return inscription;
	}
	

	private static Inscriptions setEquipesFromBdd(Inscriptions inscription) {
		
		@SuppressWarnings("unchecked")
		List<Equipe> equipes =  em.createQuery("from Equipe").getResultList();
		for (Equipe equipe : equipes) {
			if(!inscription.getCandidats().contains(equipe)) {
				inscription.createEquipe(equipe.getNom());	
			}
		}
		inscription = setJoueursFromBdd(inscription);
		
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

	private static Inscriptions setCompetitionsFromBdd(Inscriptions inscription) {
		@SuppressWarnings("unchecked")
		List<Competition>compets = em.createQuery("from Competition").getResultList();
		for (Competition compet : compets) {
		//System.out.println(compet.getNom()+ " "+ compet.getDateCloture() + " "+ compet.estEnEquipe());
		inscription.createCompetition(compet.getNom(),compet.getDateCloture(),compet.estEnEquipe());
		}

		return inscription;
	}
}