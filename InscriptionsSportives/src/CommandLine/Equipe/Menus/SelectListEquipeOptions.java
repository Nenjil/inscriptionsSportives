package CommandLine.Equipe.Menus;

import java.util.ArrayList;
import CommandLine.Equipe.Action.ActionDeleteEquipe;
import CommandLine.Equipe.Action.ActionModifEquipe;
import CommandLine.Equipe.Action.ActionVoirCompet;
import CommandLine.Equipe.Action.ActionVoirMembres;
import commandLineMenus.List;
import commandLineMenus.ListData;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import inscriptions.Equipe;
import inscriptions.Inscriptions;

public class SelectListEquipeOptions {

	    private Inscriptions inscriptions ;

		public SelectListEquipeOptions()
		{
			inscriptions = Inscriptions.getInscriptions();
		}

		protected List<Equipe> getEquipeList()
		{
			List<Equipe> selectCompetListe = new List<>("Selection pour une equipe","se",
					getEquipes(),
					getEquipeListOption()
					);
			
			selectCompetListe.setAutoBack(false);
			selectCompetListe.addBack("r");
			return selectCompetListe;
		}
		
		private ListData<Equipe> getEquipes()
		{
			return () -> new ArrayList<>(inscriptions.getEquipes());
		}
		
		private ListOption<Equipe> getEquipeListOption()
		{
			return (equipes) -> getEquipeMenuOptions(equipes);
		}
		
		
		private Option getEquipeMenuOptions(final Equipe equipe)
		{	
			Menu equipeMenu = new Menu("Edit " + equipe.getNom());
			equipeMenu.add(getVoirMembresOption(equipe));
			equipeMenu.add(getAjoutMembreOption(equipe));
			equipeMenu.add(getDeleteMembreOption(equipe));
			equipeMenu.add(getModifEquipeOption(equipe));
			equipeMenu.add(getDeletEquipeOption(equipe));
		    equipeMenu.add(getVoirCompetEquipeOption(equipe));
		    
			equipeMenu.setAutoBack(true);		
			return equipeMenu;
			
		}

		private Option getVoirCompetEquipeOption(Equipe equipe) {
			return new Option("Voir les competitions", "ve", new ActionVoirCompet(equipe));	
		}

		private Option getDeletEquipeOption(Equipe equipe) {
			return new Option("Supprimer l'equipe", "de", new ActionDeleteEquipe(equipe));
		}

		private Option getModifEquipeOption(Equipe equipe) {
			return new Option("Modifier l'equipe", "me", new ActionModifEquipe(equipe));
		}
			
		private Option getDeleteMembreOption(final Equipe equipe) {
			return new ListDeleteMembre(equipe).equipeListMembres();
		}

		private Menu getAjoutMembreOption(final Equipe equipe) {
			
			return new ListAddMembre(equipe).equipeListMembres();
			
		}

		private Option getVoirMembresOption(Equipe equipe) 
		{
			return new Option("Voir les membres", "v", new ActionVoirMembres(equipe));
		}
	}
