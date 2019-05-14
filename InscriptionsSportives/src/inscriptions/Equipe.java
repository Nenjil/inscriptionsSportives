package inscriptions;

import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.*;

import Passerelle.Passerelle;


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
	@Transient
	private  Inscriptions inscriptions;
	@ManyToMany(mappedBy="equipes", cascade = CascadeType.PERSIST)
	
	@OrderBy("num_candidat asc")
	private SortedSet<Personne> membres = new TreeSet<>();
	

	
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
		if(Inscriptions.HIBERNATE && !Inscriptions.LOADING)
			Passerelle.save(this);
	}
	
	//Constructeur normal
	@SuppressWarnings("unused")
	private Equipe() {
		inscriptions= Inscriptions.getInscriptions();
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
		if(Inscriptions.HIBERNATE)
			Passerelle.save(this);
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
		if(Inscriptions.HIBERNATE)
			Passerelle.delete(membre);
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
