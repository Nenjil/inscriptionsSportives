package CommandLine.Personne.Menus;

import CommandLine.Personne.Action.ActionAjoutPersonne;
import CommandLine.Personne.Action.*;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import inscriptions.Inscriptions;

public class MenuPersonne {
		
		Inscriptions inscriptions;
		
		public MenuPersonne() {
			inscriptions = Inscriptions.getInscriptions();
		}



		public Menu getMenuPersonne() {
			Menu menuPersonne = new Menu("Menu des personnes","P");

			menuPersonne.add(new Option ("Details des Personnes", "dp", new ActionDetailsPersonne(inscriptions)));
			menuPersonne.add(new Option ("Ajout d'une personne", "ap", new ActionAjoutPersonne(inscriptions)));
			Menu SelectPersonne = new SelectListPersonneOptions().getPersonneList();
			menuPersonne.add(SelectPersonne);
			menuPersonne.setAutoBack(true);
			
			return menuPersonne;	
		}

	}