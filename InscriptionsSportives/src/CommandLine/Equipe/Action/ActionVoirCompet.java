package CommandLine.Equipe.Action;
import commandLineMenus.Action;
import inscriptions.Competition;
import inscriptions.Equipe;

public class ActionVoirCompet implements Action {

	private Equipe equipe;

	public ActionVoirCompet(Equipe equipe) {
	this.equipe = equipe;
	}

		@Override
		public void optionSelected() {
			
		
			if(equipe.getCompetitions().isEmpty()){
				System.out.println(equipe.getNom()+" n'est inscrite à aucune compétition.");
			}
			else{
				int i=1;
				System.out.println("\nLes compétitions auxquelles est inscrite l'équipe sont : \n");
				for(Competition compet : equipe.getCompetitions()){
					System.out.println(i + "- : " + compet.toString());
					i++;	
				}
			}
	}
}
		