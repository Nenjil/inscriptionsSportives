package CommandLine.Competition.Menus;
import CommandLine.Competition.Action.*;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import inscriptions.Inscriptions;


public class MenuCompet  {

		Inscriptions inscriptions;
	
		public MenuCompet() {
		inscriptions = Inscriptions.getInscriptions();
	
		}

		public Menu getMenuCompet() {
			
			Menu menuCompet = new Menu("Menu des comp�titions","C");

			menuCompet.add(new Option ("Details des comp�titions", "dc", new ActionDetailCompet(inscriptions)));
			menuCompet.add(new Option ("Ajout d'une competition", "ac", new ActionAjoutCompet(inscriptions)));
			Menu SelectCompet = new SelectListCompetOptions().getCompetList();
			menuCompet.add(SelectCompet);
			menuCompet.addBack("b");
			menuCompet.setAutoBack(true);
			
			return menuCompet;		
		}	
}
