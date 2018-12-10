package CommandLine;
import java.util.ArrayList;

import CommandLine.Action.ActionAjoutCompet;
import CommandLine.Action.ActionDetailCompet;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import inscriptions.Competition;
import inscriptions.Inscriptions;


public class MenuCompet extends Menu {

		static Inscriptions inscriptions = Inscriptions.getInscriptions();
	
		Competition competition=null;
		public MenuCompet(String longTitle, String shortTitle, String shortcut) {
		super(longTitle, shortTitle, shortcut);	
		this.add(new Option ("Details Competitions", "dC", new ActionDetailCompet(inscriptions)));
		this.add(new Option ("Ajout Competitions", "aC", new ActionAjoutCompet(inscriptions)));
		final ArrayList<String> compets = fetchCompet();
		Menu SelectCompet = new SelectListCompetOptions(compets).getPeopleList();
		this.add(SelectCompet);
		this.setAutoBack(true);	
	}


	private ArrayList<String> fetchCompet() {
		final ArrayList<String> compets = new ArrayList<>();
		for (Competition c : inscriptions.getCompetitions()) {
			compets.add(c.getNom());
		}
		return compets;
	}

		
		/*Option CandidateList = new Option ("Voir la liste des candidats", "vc");
		SelectCompetition.add(CandidateList);
		Option AddCandidate = new Option ("Ajout d'un candidat" , "ac");
		SelectCompetition.add(AddCandidate);
		Option DeleteCandidate = new Option ("Suppression d'un candidat" , "sc");
		SelectCompetition.add(DeleteCandidate);
		Option ModifyCompetition = new Option ("Modifier une competition" , "mC");
		SelectCompetition.add(ModifyCompetition);
		Option DeleteCompetition = new Option ("Suppression d'une competition" , "sC");
		SelectCompetition.add(DeleteCompetition);
		SelectCompetition.addBack("b");
		
		return ShortTitle;
		
	
	}*/
	
}
