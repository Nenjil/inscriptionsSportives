package CommandLine.Equipe.Menus;
import java.io.IOException;
import java.util.ArrayList;

import commandLineMenus.*;
import inscriptions.*;

public class ListAddMembre  {
	final ArrayList<Personne> membres = new ArrayList<>();
	private Inscriptions inscriptions;
	private Equipe equipe;
	
	public ListAddMembre(Equipe equipe){
		inscriptions = Inscriptions.getInscriptions();
		this.equipe = equipe;
	}
	
	protected Menu equipeListMembres() {
		
		
	
		getpersonneAAjouter();	
		List<Personne> menu = new List<Personne>("ajout d'un membre","am",
				new ListData<Personne>()		
				{
					// Returns the data needed to refresh the list 
					// each time it is displayed. 
					public java.util.List<Personne> getList()
					{
						return membres;
					}	
				},
				new ListAction<Personne>()
				{				
					// Triggered each time an item is selected
					public void itemSelected(int index, Personne personne)
					{
							equipe.add(personne);
							System.out.println(personne.getPrenom()+" "+personne.getNom()+" a bien ete ajouté à l'équipe");
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

	private void getpersonneAAjouter() {
		for (Personne personne : equipe.getPersonnesAAjouter()) {
			membres.add(personne);
		}
	}

	
}


	
