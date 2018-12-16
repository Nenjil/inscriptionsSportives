package inscriptions;

import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.*;


/**
 * Représente une Equipe. C'est-à-dire un ensemble de personnes pouvant 
 * s'inscrire à une compétition.
 * 
 */
@Entity @Table(name="Equipe")
@PrimaryKeyJoinColumn(name="num_candidat")
public class Equipe extends Candidat
{
	@Transient
	private static final long serialVersionUID = 4147819927233466035L;
	
	@ManyToMany()
	@JoinTable(name = "composer", joinColumns = @JoinColumn(name = "num_candidat_Personne"),
    inverseJoinColumns = @JoinColumn(name = "num_candidat_Equipe"))
	@OrderBy("num_candidat asc")
	private SortedSet<Personne> membres = new TreeSet<>();
	
	// constructeur vide pour hibernate
	public Equipe() {
		
	}
	
	/*
	Equipe(Inscriptions inscriptions, String nom, SortedSet<Personne> membres)
	{
		super(inscriptions, nom);
		membres = this.membres;
	}
	*/
	Equipe(Inscriptions inscriptions, String nom)
	{
		super(inscriptions, nom);
	}

	/**
	 * Retourne l'ensemble des personnes formant l'équipe.
	 */
	
	public SortedSet<Personne> getMembres()
	{
		return Collections.unmodifiableSortedSet(membres);
	}
	
	/**
	 * Ajoute une personne dans l'équipe.
	 * @param membre
	 * @return
	 */

	public boolean add(Personne membre)
	{
		membre.add(this);
		return membres.add(membre);
	}

	/**
	 * Supprime une personne de l'équipe. 
	 * @param membre
	 * @return
	 */
	
	public boolean remove(Personne membre)
	{
		membre.remove(this);
		return membres.remove(membre);
	}

	/**
	 * Retourne les personnes que l'on peut ajouter dans cette équipe.
	 * @return les personnes que l'on peut ajouter dans cette équipe.
	 */
	
	public Set<Personne> getPersonnesAAjouter()
	{	
		SortedSet<Personne> AAjouter = new TreeSet<>();
		for (Personne personne : Inscriptions.getInscriptions().getPersonnes()) {
			if (!getMembres().contains(personne))
				AAjouter.add(personne);
		}
		return AAjouter;
	}//Fini
	
	@Override
	public void delete()
	{
		super.delete();
	}
	
	@Override
	public String toString()
	{
		return "Equipe " + super.toString();
	}
}
