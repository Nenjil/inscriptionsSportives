package CommandLine;

import commandLineMenus.Menu;
import commandLineMenus.Option;

public class MenuEquipe {

	public MenuEquipe() {
	
		create();
	}

	private void create() {
		//Gestion Menu
				Menu Team = new Menu("Equipe Sub-Menu", "Equipe", "E");
				//Menu Equipe = new Menu("Equipe Sub-Menu", "Equipe", "e");
				//Menu Personne = new Menu("Personne Sub-Menu", "Personne", "p");
				Option detailsTeam = new Option ("Details Equipes", "dE");
				Team.add(detailsTeam);
				Option addTeam = new Option ("Ajout Equipe", "aE");
				Team.add(addTeam);
				Menu SelectTeam = new Menu ("Choix Equipes", "cE");
				Team.add(SelectTeam);
				Option TeamList = new Option ("Voir la liste des membres", "vlm");
				SelectTeam.add(TeamList);
				Option AddMember = new Option ("Ajout d'un Membre" , "am");
				SelectTeam.add(AddMember);
				Option DeleteMember = new Option ("Suppression d'un Membre" , "sm");
				SelectTeam.add(DeleteMember);
				Option CompetList = new Option ("Liste des Competitions", "lC");
				SelectTeam.add(CompetList);
				Option ModifyTeam = new Option ("Modifier une Equipe" , "mE");
				SelectTeam.add(ModifyTeam);
				Option DeleteTeam = new Option ("Suppression d'une Equipe" , "sE");
				SelectTeam.add(DeleteTeam);
				SelectTeam.addBack("b");
				Team.addBack("b");
	}
}
