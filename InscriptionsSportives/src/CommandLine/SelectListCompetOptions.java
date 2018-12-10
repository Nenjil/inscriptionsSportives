package CommandLine;
import java.util.ArrayList;

import CommandLine.Action.ActionVoirCandidats;
import commandLineMenus.List;
import commandLineMenus.ListData;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import inscriptions.Competition;
import inscriptions.Inscriptions;


public class SelectListCompetOptions 

{

   private Inscriptions inscriptions ;

	public SelectListCompetOptions()
	{

		inscriptions = Inscriptions.getInscriptions();
	
	}

	protected List<Competition> getCompetList()
	{
		List<Competition> liste = new List<>("Selection competition","sc",
				getCompetitions(),
				getCompetitionListOption()
				);
		
		liste.setAutoBack(false);
		liste.addBack("r");
		liste.addQuit("q");
		return liste;
	}
	
	private ListData<Competition> getCompetitions()
	{
		return () -> new ArrayList<>(inscriptions.getCompetitions());
	}
	
	private ListOption<Competition> getCompetitionListOption()
	{
		return (competition) -> getCompetMenuOptions(competition);
	}
	
	private Option getCompetMenuOptions(final Competition competition)
	{	
		Menu competMenu = new Menu("Edit " + competition.getNom());
		competMenu.add(getVoirCandidatsOption(competition));
		competMenu.add(getAjoutCandidatsOption(competition));
		//competMenu.add(getDeleteCandidatsOption());
		competMenu.setAutoBack(true);
		return competMenu;
	}

	private Option getDeleteCandidatsOption() {
		return new Option("Supprimer un candidats", "rc"/*, new ActionVoirCandidats(competition)*/);
	}

	private Menu getAjoutCandidatsOption(final Competition competition) {
		return new ListAddCandidat(competition).competListCandidat();
	}
	
	
	private Option getVoirCandidatsOption(Competition competition) 
	{
		return new Option("Voir les candidats", "v", new ActionVoirCandidats(competition));
	}
}
