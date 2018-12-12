package CommandLine.Personne.Menus;

import java.io.IOException;
import java.util.ArrayList;
import commandLineMenus.*;
import inscriptions.*;

public class ListAddEquipesPersonne  {
	final ArrayList<Equipe> equipes = new ArrayList<>();
	private Inscriptions inscriptions;
	private Personne personne;
	
	public ListAddEquipesPersonne(Personne personne){
		inscriptions = Inscriptions.getInscriptions();
		this.personne = personne;
	}
	
	protected Menu equipesListPersonne() {
		//rempli l'arraylist d'equipes;
		fillEquipes();
		//cree un menu d'equipe
		List<Equipe> menu = new List<Equipe>("ajout d'une personne dans une equipe","ate",
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
							equipe.add(personne);
							System.out.println(personne.getPrenom()+" "+equipe.getNom()+" a bien ete ajouté à l'équipe "+equipe.getNom());
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


	
