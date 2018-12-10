package CommandLine;
import CommandLine.Action.ActionAjoutCompet;
import CommandLine.Action.ActionDetailCompet;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import inscriptions.Inscriptions;


public class MenuCompet  {

		static Inscriptions inscriptions = Inscriptions.getInscriptions();
	
		public MenuCompet() {
		
		}

		protected Menu getMenuCompet() {
			
			Menu menuCompet = new Menu("Competition","C");

			menuCompet.add(new Option ("Details Competitions", "dC", new ActionDetailCompet(inscriptions)));
			menuCompet.add(new Option ("Ajout Competitions", "aC", new ActionAjoutCompet(inscriptions)));
			Menu SelectCompet = new SelectListCompetOptions().getCompetList();
			menuCompet.add(SelectCompet);
			menuCompet.setAutoBack(true);
			return menuCompet;	
			
		}


		
		/*Option CandidateList = new Option ("Voir la liste des candidats", "vc");
		SelectCompetition.add(CandidateList);
		Option AddCandidate = new Option ("Ajout d'un candidat" , "ac");
		SelectCompetition.add(AddCandidate);
		Option DeleteCandidate = new Option ("Suppression d'un candidat" , "sc");
		SelectCompetition.add(DeleteCandidate);
		Option ModifyCompetition = new Option ("Modifier une competition" , "mC");
		SelectCompetition.add(ModifyCompetition);
		Option DeleteCompetition = new Option ("Suppression d'une competition" , "sC");
		SelectCompetition.add(DeleteCompetition);
		SelectCompetition.addBack("b");
		
		return ShortTitle;
		
	
	}*/
	
}
