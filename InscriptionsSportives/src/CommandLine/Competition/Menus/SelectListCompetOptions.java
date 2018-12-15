package CommandLine.Competition.Menus;
import java.util.ArrayList;

import CommandLine.Competition.Action.*;
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
		List<Competition> selectCompetListe = new List<>("Selection pour une competition","sc",
				getCompetitions(),
				getCompetitionListOption()
				);
		
		selectCompetListe.setAutoBack(false);
		selectCompetListe.addBack("r");
		return selectCompetListe;
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
		competMenu.add(getDeleteCandidatsOption(competition));
		competMenu.add(getModifCompetOption(competition));
		competMenu.add(getDeletCompetOption(competition));
		competMenu.addBack("r");
		competMenu.setAutoBack(true);
		return competMenu;
		
	}

	private Option getDeletCompetOption(Competition competition) {
		return new Option("Supprimer la competition", "dc", new ActionDeleteCompetition(competition));
	}

	private Option getModifCompetOption(Competition competition) {
		return new Option("Modifier la competition", "mc", new ActionModifCompetition(competition));
	}
		
	

	private Option getDeleteCandidatsOption(final Competition competition) {
		return new ListDeleteCandidat(competition).competListCandidat();
	}

	private Menu getAjoutCandidatsOption(final Competition competition) {
		
		return new ListAddCandidat(competition).competListCandidat();
	}
	
	
	private Option getVoirCandidatsOption(Competition competition) 
	{
		return new Option("Voir les candidats", "v", new ActionVoirCandidats(competition));
	}	
}
