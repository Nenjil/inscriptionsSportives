package CommandLine.Equipe.Menus;
import java.io.IOException;
import java.util.ArrayList;

import commandLineMenus.*;
import inscriptions.*;

public class ListDeleteMembre  {
	final ArrayList<Personne> membres = new ArrayList<>();
	private Inscriptions inscriptions;
	private Equipe equipe;
	
	public ListDeleteMembre(Equipe equipe){
		inscriptions = Inscriptions.getInscriptions();
		this.equipe = equipe;
	}
	
	protected Menu equipeListMembres() {
		
		ajoutMembres();
		List<Personne> menu = new List<Personne>("Suppression d'un membre","dm",
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
					try {
									
									equipe.remove(personne);
									System.out.println("\n'" + personne.getNom()+ "' est bien supprimé de l'equipe.");
								} catch (Exception e) {
									// TODO Auto-generated catch block
									System.out.println(e.toString());
								}							
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

	private void ajoutMembres() {
		for(Personne p : equipe.getMembres()){
			membres.add(p);
			}
	}
}

	