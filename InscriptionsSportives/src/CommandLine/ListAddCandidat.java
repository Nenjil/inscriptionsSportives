package CommandLine;

import java.io.IOException;
import java.util.ArrayList;

import commandLineMenus.*;
import inscriptions.*;

public class ListAddCandidat extends Menu  {
	final ArrayList<Candidat> candidats = new ArrayList<>();
	private Inscriptions inscriptions;
	private Competition competition;
	
	public ListAddCandidat(String shortTitle, String shortcut,Competition competition){
		super(shortTitle, shortcut);
		inscriptions = Inscriptions.getInscriptions();
		this.competition = competition;
	}
	
	protected Menu listCandidat() {
		
		for(Candidat c : inscriptions.getCandidats()){
			if((c instanceof Equipe && competition.estEnEquipe()) && !competition.getCandidats().contains(c)||
					(c instanceof Personne && !competition.estEnEquipe() && !competition.getCandidats().contains(c))){	
				candidats.add(c);
				}
			}
				
		List<Candidat> menu = new List<Candidat>(getTitle(),getShortcut(),
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
						if(!competition.inscriptionsOuvertes()){
							System.out.println("Les inscriptions ne sont pas ouvertes.");
						}
						else{
							
							if(competition.estEnEquipe()){
								
									try 
									{
										
										competition.add((Equipe)candidat);
										System.out.println("\n'" + candidat.toString() + "' est bien ajoutée à la compétition.");
									} 
									catch (Exception e) 
									{
										System.out.println(e.toString());
									}
									
								
							}
							else{
								try {
									competition.add((Personne) candidat);
									System.out.println("\n'" + (candidat).toString() + "' est bien ajouté à la compétition.");
								} catch (Exception e) {
									// TODO Auto-generated catch block
									System.out.println(e.toString());
								}
								
							}
							try {
								inscriptions.sauvegarder();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
			menu.addQuit("q");
	        menu.addBack("b");
			return menu; }
}

	