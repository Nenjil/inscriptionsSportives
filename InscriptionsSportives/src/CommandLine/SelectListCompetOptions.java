package CommandLine;
import CommandLine.Action.ActionVoirCandidats;
import commandLineMenus.List;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import inscriptions.Competition;
import inscriptions.Inscriptions;


public class SelectListCompetOptions extends Menu

{
	private Competition competition=null;
	
   private Inscriptions inscriptions ;

	private java.util.List<String> compets;
	
	SelectListCompetOptions(java.util.List<String> compets)
	{
		super("Select Compet","sc");
		this.compets = compets;
		inscriptions = Inscriptions.getInscriptions();
		//List<String> list = getPeopleList();
		//list.start();
	}

	
	
	protected List<String> getPeopleList()
	{
		List<String> liste = new List<>("Select compet","sc",
				() -> compets,
				(compets) -> getCompetMenu(compets)
				);
		
		liste.setAutoBack(false);
		liste.addBack("r");
		liste.addQuit("q");
		return liste;
	}
	
	private Option getCompetMenu(final String compet)
	{	
		for ( Competition comp : inscriptions.getCompetitions()) {
			if(comp.getNom().equals(compet))
			competition= comp;}
		
		Menu competMenu = new Menu("Edit " + compet, compet, null);
		competMenu.add(new Option("Voir les candidats", "v", new ActionVoirCandidats(competition)));
		Option ajoutcandidat  = new ListAddCandidat("Ajouter un candidat", "ac", competition).listCandidat(); 
		competMenu.add(ajoutcandidat);
		// a continuer
		competMenu.add(new Option("Supprimer un candidats", "rc"/*, new ActionVoirCandidats(competition)*/));
		competMenu.setAutoBack(true);
		return competMenu;
	}
}
