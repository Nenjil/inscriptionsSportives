package inscriptions;

import java.io.Serializable;

import java.util.Collections;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;

import Passerelle.Passerelle;

/**
 * Représente une compétition, c'est-à-dire un ensemble de candidats 
 * inscrits à un événement, les inscriptions sont closes à la date dateCloture.
 *
 */
@Entity @Table(name="Competition")
public class Competition implements Comparable<Competition>, Serializable
{
	
	@Transient
	private static final long serialVersionUID = -2882150118573759729L;
	@Transient
	private Inscriptions inscriptions;
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY )
	private int NumCompet;
	@Column (name="LibelleCompet")
	private String nom;
    @ManyToMany(mappedBy="competitions", cascade = CascadeType.PERSIST)
	private Set<Candidat> candidats;
	 @Column (name="Date_cloture")
	private LocalDate dateCloture;
	 @Column (name="EstEnEquipe")
	private boolean enEquipe = false;
	
	// Constructeur sans param�tres pour hibernate
	@SuppressWarnings("unused")
	private Competition() {
		//inscriptions=Inscriptions.getInscriptions();
	}

	//Constructeur normal
	Competition(Inscriptions inscriptions, String nom, LocalDate dateCloture, boolean enEquipe)
	{
		this.enEquipe = enEquipe;
		this.inscriptions = inscriptions;
		this.nom = nom;
		this.dateCloture = dateCloture;
		candidats = new TreeSet<>();
		if(Inscriptions.HIBERNATE)
			Passerelle.save(this);
	}
	
	// getter et setter private pour hibernate
	@SuppressWarnings("unused")
	private int getNumCompet() {
		return NumCompet;
	}

	@SuppressWarnings("unused")
	private void setNumCompet(int numCompet) {
		NumCompet = numCompet;
	}
	
	
	/**
	 * Retourne le nom de la compétition.
	 * @return
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Modifie le nom de la compétition.
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom ;
		if(Inscriptions.HIBERNATE)
			Passerelle.save(this);
	}
	
	/**
	 * Retourne vrai si les inscriptions sont encore ouvertes, 
	 * faux si les inscriptions sont closes.
	 * @return
	 */
	
	public boolean inscriptionsOuvertes()
	{
		
		LocalDate d = LocalDate.now();
		return d.isBefore(getDateCloture()) ;
		// TODO retourner vrai si et seulement si la date système est antérieure à la date de clôture.
		
	}
	
	/**
	 * Retourne la date de cloture des inscriptions.
	 * @return
	 */
	
	public LocalDate getDateCloture()
	{
		return dateCloture;
	}
	
	/**
	 * Est vrai si et seulement si les inscriptions sont réservées aux équipes.
	 * @return
	 */
	
	public boolean estEnEquipe()
	{
		return enEquipe;
	}
	
	/**
	 * Modifie la date de cloture des inscriptions. Il est possible de la reculer 
	 * mais pas de l'avancer.
	 * @param dateCloture
	 */
	
	public void setDateCloture(LocalDate dateCloture)
	{
		// TODO vérifier que l'on avance pas la date.
		if(dateCloture.isAfter(this.dateCloture))
		{
			this.dateCloture = dateCloture;
		}
		
	}
	
	/**
	 * Retourne l'ensemble des candidats inscrits.
	 * @return
	 */
	
	public Set<Candidat> getCandidats()
	{
		return Collections.unmodifiableSet(candidats);
	}
	
	/**
	 * Inscrit un candidat de type Personne à la compétition. Provoque une
	 * exception si la compétition est réservée aux équipes ou que les 
	 * inscriptions sont closes.
	 * @param personne
	 * @return
	 */
	
	public boolean add(Personne personne)
	{
		// TODO vérifier que la date de clôture n'est pas passée
		if( !inscriptionsOuvertes())
			throw new RuntimeException("la date d'inscriptions est deja pass�e ("+this.dateCloture.toString()+"), nous ne pouvons donc pas ajouter "+personne.getNom());
		else if  (enEquipe)
		throw new RuntimeException( "Cette competition est en equipe. Nous ne pouvons donc pas ajouter"+personne.getNom());

		personne.add(this);
		if(Inscriptions.HIBERNATE)
			Passerelle.save(this);
		return candidats.add(personne);

		
		
	}

	/**
	 * Inscrit un candidat de type Equipe à la compétition. Provoque une
	 * exception si la compétition est réservée aux personnes ou que 
	 * les inscriptions sont closes.
	 * @param personne
	 * @return
	 */

	public boolean add(Equipe equipe)
	{
		// TODO vérifier que la date de clôture n'est pas passée
		if (!enEquipe || !inscriptionsOuvertes())
			throw new RuntimeException();
		equipe.add(this);
		if(Inscriptions.HIBERNATE)
			Passerelle.save(this);
		return candidats.add(equipe);
		
	}
	
	/**
	 * Retourne les personnes que l'on peut inscrire à cette competition.
	 * @return les personnes que l'on peut inscrire � cette comp�tition.
	 */
	
	public Set<Personne> getPersonnesAInscrire()
	{
		Set<Personne> aInscrire = new TreeSet<>(inscriptions.getPersonnes());
		aInscrire.removeAll(getCandidats());
		/*for(Personne personne : inscriptions.getPersonnes())
		{
			if(!getCandidats().contains(personne))
			{
				aInscrire.add(personne);
			}
		}*/
		// TODO retourner les personnes que l'on peut inscrire à cette compétition.
		return aInscrire ;
	}
	
	/**
	 * Retourne les equipes que l'on peut inscrire à cette competition.
	 * @return les equipes que l'on peut inscrire à cette competition.
	 */
	
	public Set<Equipe> getEquipesAInscrire()
	{
		Set<Equipe> aInscrire = new TreeSet<>(inscriptions.getEquipes());
		// le removeall supprime toutes les equipes qui sont deja inscrites pour n'avoir que les equipes non inscrites
		aInscrire.removeAll(getCandidats());
		// TODO retourner les personnes que l'on peut inscrire à cette compétition.
		return aInscrire ;
	}


	/**
	 * Désinscrit un candidat.
	 * @param candidat
	 * @return
	 */
	
	public boolean remove(Candidat candidat)
	{
		candidat.remove(this);
		if(Inscriptions.HIBERNATE)
			Passerelle.delete(this);
		return candidats.remove(candidat);
	
	}
	
	/**
	 * Supprime la compétition de l'application.
	 */
	
	public void delete()
	{
		for (Candidat candidat : candidats)
			remove(candidat);
		inscriptions.delete(this);
		if(Inscriptions.HIBERNATE)
			
			Passerelle.delete(this);
	}
	
	@Override
	public int compareTo(Competition o)
	{
		return getNom().compareTo(o.getNom());
	}
	
	@Override
	public String toString()
	{
		return getNom();
	}
}
