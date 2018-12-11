package CommandLine.Equipe.Menus;

import CommandLine.Equipe.Action.ActionAjoutEquipe;
import CommandLine.Equipe.Action.ActionDetailEquipes;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import inscriptions.Inscriptions;

public class MenuEquipe {
	
	Inscriptions inscriptions;
	
	public MenuEquipe() {
		inscriptions = Inscriptions.getInscriptions();
	}



	public Menu getMenuEquipe() {
		Menu menuEquipe = new Menu("Menu des équipes","E");

		menuEquipe.add(new Option ("Details des équipes", "de", new ActionDetailEquipes(inscriptions)));
		menuEquipe.add(new Option ("Ajout d'une equipe", "ae", new ActionAjoutEquipe(inscriptions)));
		Menu SelectEquipe = new SelectListEquipeOptions().getEquipeList();
		menuEquipe.add(SelectEquipe);
		menuEquipe.setAutoBack(true);
		
		return menuEquipe;	
	}

}
