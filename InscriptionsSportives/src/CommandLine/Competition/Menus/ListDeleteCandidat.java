package CommandLine.Competition.Menus;

import java.io.IOException;
import java.util.ArrayList;

import commandLineMenus.*;
import inscriptions.*;

public class ListDeleteCandidat   {
	final ArrayList<Candidat> candidats = new ArrayList<>();
	private Inscriptions inscriptions;
	private Competition competition;
	
	public ListDeleteCandidat(Competition competition){
		inscriptions = Inscriptions.getInscriptions();
		this.competition = competition;
	}
	
	protected Menu competListCandidat() {
		
		for(Candidat c : inscriptions.getCandidats()){
			if((competition.getCandidats().contains(c)))
			candidats.add(c);
			}
				
			
				
		List<Candidat> menu = new List<Candidat>("Suppression d'un candidat","sc",
				new ListData<Candidat>()		
				{
					// Returns the data needed to refresh the list 
					// each time it is displayed. 
					public java.util.List<Candidat> getList()
					{
						return candidats;
					}	
				},
				new ListAction<Candidat>()
				{				
					// Triggered each time an item is selected
					public void itemSelected(int index, Candidat candidat)
					{
					try {
									competition.remove(candidat);
									System.out.println("\n'" + candidat.getNom()+ "' est bien supprimé à la compétition.");
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
}

	