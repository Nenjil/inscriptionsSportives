package CommandLine;

import CommandLine.Action.DetailCompet;
import commandLineMenus.Menu;
import commandLineMenus.Option;

public class MenuCompet {

	public MenuCompet() {

	}

	public Option createMenuCompet() {
		Menu Competition = new Menu("Competition Sub-Menu", "Competion", "c");
		//Menu Equipe = new Menu("Equipe Sub-Menu", "Equipe", "e");
		//Menu Personne = new Menu("Personne Sub-Menu", "Personne", "p");
		Option detailsCompet = new Option ("Details Competitions", "dc");
		Competition.add(detailsCompet);
		detailsCompet.setAction(new DetailCompet());
		Option ajoutCompet = new Option ("Ajout Competitions", "aC");
		Competition.add(ajoutCompet);
		Menu SelectCompetition = new Menu ("Choix Competitions", "cC");
		Competition.add(SelectCompetition);
		Option CandidateList = new Option ("Voir la liste des candidats", "vc");
		SelectCompetition.add(CandidateList);
		Option AddCandidate = new Option ("Ajout d'un candidat" , "ac");
		SelectCompetition.add(AddCandidate);
		Option DeleteCandidate = new Option ("Suppression d'un candidat" , "sc");
		SelectCompetition.add(DeleteCandidate);
		Option ModifyCompetition = new Option ("Modifier une competition" , "mC");
		SelectCompetition.add(ModifyCompetition);
		Option DeleteCompetition = new Option ("Suppression d'une competition" , "dC");
		SelectCompetition.add(DeleteCompetition);
		SelectCompetition.addBack("b");
		
		return Competition;
		
	
	}
	
}
