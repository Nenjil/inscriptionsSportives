package CommandLine;

import commandLineMenus.Menu;
import commandLineMenus.Option;

public class MenuPerson {

	public MenuPerson() {
	
		create();
	}

	private void create() {
		Menu Person = new Menu("Person Sub-Menu", "Personne", "P");
		//Menu Equipe = new Menu("Equipe Sub-Menu", "Equipe", "e");
		//Menu Personne = new Menu("Personne Sub-Menu", "Personne", "p");
		Option detailsPerson = new Option ("Details Person", "dP");
		Person.add(detailsPerson);
		Option addPerson = new Option ("Ajout Person", "aP");
		Person.add(addPerson);
		Menu SelectPerson = new Menu ("Choix Person", "cP");
		Person.add(SelectPerson);
		Option TeamPerson = new Option ("Voir la liste des Personnes", "vP");
		SelectPerson.add(TeamPerson);
		Option AddPerson = new Option ("Ajout d'une Personne" , "aP");
		SelectPerson.add(AddPerson);
		Option DeletePersonFromTeam = new Option ("Suppression d'une Personne depuis l'équipe" , "sPT");
		SelectPerson.add(DeletePersonFromTeam);
		Option CompetList = new Option ("Liste des Competitions", "lC");
		SelectPerson.add(CompetList);
		Option ModifyPerson = new Option ("Modifier une Person" , "mP");
		SelectPerson.add(ModifyPerson);
		Option DeletePerson = new Option ("Suppression d'une Person" , "sP");
		SelectPerson.add(DeletePerson);
		SelectPerson.addBack("b");
		Person.addBack("b");
	}
}
