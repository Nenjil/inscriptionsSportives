package CommandLine.Personne.Menus;

import java.io.IOException;
import java.util.ArrayList;
import commandLineMenus.*;
import inscriptions.*;

public class ListPersonneDeleteEquipe  {
	final ArrayList<Equipe> equipes = new ArrayList<>();
	private Inscriptions inscriptions;
	private Personne personne;
	
	public ListPersonneDeleteEquipe(Personne personne){
		inscriptions = Inscriptions.getInscriptions();
		this.personne = personne;
	}
	
	protected Menu equipesListPersonne() {
		//rempli l'arraylist "equipes" d'equipes;
		fillEquipes();
		//cree un menu d'equipe
		List<Equipe> menu = new List<Equipe>("Suppression d'une personne dans une equipe","ste",
				new ListData<Equipe>()		
				{
					// Returns the data needed to refresh the list 
					// each time it is displayed. 
					public java.util.List<Equipe> getList()
					{
						return equipes;
					}	
				},
				new ListAction<Equipe>()
				{				
					// Triggered each time an item is selected
					public void itemSelected(int index, Equipe equipe)
					{
							equipe.remove(personne);
							System.out.println(personne.getPrenom()+" "+equipe.getNom()+" a bien été suprrimé de l'équipe "+equipe.getNom());
							try {
								inscriptions.sauvegarder();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
					}
				});
			menu.addQuit("q");
	        menu.addBack("b");
	        
			return menu; }

	private void fillEquipes() {
		for (Equipe equipe : inscriptions.getEquipes()) {
		equipes.add(equipe);	
		}
	
	}

	
}


	
