package CommandLine.Competition.Action;

import java.util.ArrayList;
import commandLineMenus.*;
import inscriptions.Competition;
import inscriptions.Inscriptions;


public class ActionSelectCompet implements Action{

 private Inscriptions inscriptions;


public ActionSelectCompet(Inscriptions inscriptions) {
	this.inscriptions = inscriptions;
}


	@Override
	public void optionSelected() {
		// Creates a list containing "Ginette", "Marcel" et "Gisèle"
				final ArrayList<String> compets = new ArrayList<>();
				for (Competition c : inscriptions.getCompetitions()) {
					compets.add(c.getNom());
				}
				// Creates a menu with an option for each people in the list
				List<String> menu = new List<String>("Liste Selection Competition ", 
					new ListData<String>()	
					{
						// Returns the data needed to refresh the list 
						// each time it is displayed. 
						public java.util.List<String> getList()
						{
							return compets;
						}	
					},
					new ListAction<String>()
					{				
						// Triggered each time an item is selected
						public void itemSelected(int index, String someone)
						{
							System.out.println("You have selected " + someone 
									+ ", who has the index " + index);
						}
					});

				menu.addQuit("q");
				menu.start();
				
			
	}
	
	
}
	