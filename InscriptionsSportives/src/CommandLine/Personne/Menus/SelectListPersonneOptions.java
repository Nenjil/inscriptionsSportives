package CommandLine.Personne.Menus;

import java.util.ArrayList;
import CommandLine.Personne.Action.*;
import commandLineMenus.List;
import commandLineMenus.ListData;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import inscriptions.Personne;
import inscriptions.Inscriptions;

public class SelectListPersonneOptions {

	    private Inscriptions inscriptions ;

		public SelectListPersonneOptions()
		{
			inscriptions = Inscriptions.getInscriptions();
		}

		protected List<Personne> getPersonneList()
		{
			List<Personne> selectPersonList = new List<>("Selection pour une personne","se",
					getPersonnes(),
					getPersonnesListOptions()
					);
			
			selectPersonList.setAutoBack(false);
			selectPersonList.addBack("r");
			return selectPersonList;
		}
		
		private ListData<Personne> getPersonnes()
		{
			return () -> new ArrayList<>(inscriptions.getPersonnes());
		}
		
		private ListOption<Personne> getPersonnesListOptions()
		{
			return (personne) -> getPersonneMenuOptions(personne);
		}
		
		
		private Option getPersonneMenuOptions(final Personne personne)
		{	
			Menu personneMenu = new Menu("Edit " + personne.getNom());
			personneMenu.add(getVoirPersonneEquipeOption(personne));
			personneMenu.add(getAjoutPersonneToEquipeOption(personne));
			personneMenu.add(getDeletePersonneToEquipeOption(personne));
			personneMenu.add(getModifPersonneOption(personne));
			personneMenu.add(getDeletePersonneOption(personne));
		 
		  
			personneMenu.setAutoBack(true);		
			return personneMenu;
			
		}

		private Option getDeletePersonneOption(Personne personne) {
			return new Option("Supprimer la personne", "de", new ActionDeletePersonne(personne));
		}

		private Option getModifPersonneOption(Personne personne) {
			return new Option("Modifier la personne", "me", new ActionModifPersonne(personne));
		}
			
		private Option getDeletePersonneToEquipeOption(final Personne personne) {
			return new ListPersonneDeleteEquipe(personne).equipesListPersonne();
		}

		private Menu getAjoutPersonneToEquipeOption(final Personne personne) {
			
			return new ListAddEquipesPersonne(personne).equipesListPersonne();
			
		}

		private Option getVoirPersonneEquipeOption(Personne personne) 
		{
			return new Option("Voir les equipes", "v", new ActionVoirEquipe(personne));
		}
	}
