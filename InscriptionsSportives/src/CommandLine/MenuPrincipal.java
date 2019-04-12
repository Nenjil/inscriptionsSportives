package CommandLine;
import CommandLine.Competition.Menus.MenuCompet;
import CommandLine.Equipe.Menus.MenuEquipe;
import CommandLine.Personne.Menus.MenuPersonne;
import commandLineMenus.*;
public class MenuPrincipal {

	// Returns the root menu
	private static Menu getMainMenu()
	{
		
		Menu mainMenu = new Menu("Inscriptions sportives : Menu principal");
		// Adds a submenu or an option is then done 
		// with a method call
		mainMenu.add(getPersonneMenu());
		mainMenu.add(getEquipeMenu());
		mainMenu.add(getCompetMenu());
		mainMenu.addQuit("Q");
		return mainMenu;
	}

	// It is also advised to create each menu in a separated method
	private static Menu getPersonneMenu()
	{
		Menu menuPersonne = new MenuPersonne().getMenuPersonne();
		return menuPersonne;

	}

	private static Menu getCompetMenu()
	{
		Menu menuCompetition = new MenuCompet().getMenuCompet();
		return menuCompetition;

	}
	private	static Menu getEquipeMenu()
	{
		Menu menuEquipe = new MenuEquipe().getMenuEquipe();
		return menuEquipe;

	}

	public static void main(String[] args)
	{
		Menu menu = getMainMenu();
		menu.start();
		Passerelle.Passerelle.close();
		
	}
}
