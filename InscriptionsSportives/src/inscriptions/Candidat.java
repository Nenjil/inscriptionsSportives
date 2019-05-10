package inscriptions;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.*;

import Passerelle.Passerelle;


/**
 * Candidat à un événement sportif, soit une personne physique, soit une équipe.
 *
 */

@Entity @Table(name="Candidat")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Candidat implements Comparable<Candidat>, Serializable
{
	@Transient
	private static final long serialVersionUID = -6035399822298694746L;
	
	@Transient
	private Inscriptions inscriptions;
    @Id @GeneratedValue( strategy=GenerationType.IDENTITY )
	private int num_candidat;
	@Column (name="nom_candidat")
    private String nom;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "constituer", joinColumns = @JoinColumn(name = "num_candidat"),
    inverseJoinColumns = @JoinColumn(name = "NumCompet"))
   
	private Set<Competition> competitions;
	
	
	Candidat(Inscriptions inscriptions, String nom)
	{
		this.inscriptions = inscriptions;	
		this.nom = nom;
		competitions = new TreeSet<>();
		if(Inscriptions.HIBERNATE && !Inscriptions.LOADING)
			Passerelle.save(this);
	}
	//constructeur sans parametres qui fetch la derniere insctance d'inscription pour hibernate
	Candidat() {
		//inscriptions = Inscriptions.getInscriptions();
	}
	
	@SuppressWarnings("unused")
	private int getNum_candidat() {
		return num_candidat;
	}

	@SuppressWarnings("unused")
	private void setNum_candidat(int num_candidat) {
		this.num_candidat = num_candidat;
	}

	/**
	 * Retourne le nom du candidat.
	 * @return
	 */
	
	public String getNom()
	{
		return nom;
	}

	/**
	 * Modifie le nom du candidat.
	 * @param nom
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}

	/**
	 * Retourne toutes les compétitions auxquelles ce candidat est inscrit.s
	 * @return
	 */

	public Set<Competition> getCompetitions()
	{
		return Collections.unmodifiableSet(competitions);
	}
	
	boolean add(Competition competition)
	{
		return competitions.add(competition);
	}

	boolean remove(Competition competition)
	{
		return competitions.remove(competition);
	}

	/**
	 * Supprime un candidat de l'application.
	 */
	
	public void delete()
	{
		for (Competition c : competitions) {
			c.remove(this);
		}
		inscriptions.delete(this);
	}
	
	@Override
	public int compareTo(Candidat o)
	{
		return getNom().compareTo(o.getNom());
	}
	
	@Override
	public String toString()
	{
		return "\n" + getNom() + " -> inscrit � " + getCompetitions();
	}
}
