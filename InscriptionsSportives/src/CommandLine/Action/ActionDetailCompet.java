package CommandLine.Action;

import commandLineMenus.Action;
import inscriptions.Competition;
import inscriptions.Inscriptions;


public class ActionDetailCompet implements Action{
private Inscriptions inscriptions;

public ActionDetailCompet(Inscriptions inscriptions) {
this.inscriptions = inscriptions;
}

	@Override
	public void optionSelected() {
		if (inscriptions.getCompetitions().isEmpty())
			System.out.println("La liste des competitions est vide");
			else {
				for (Competition compet : inscriptions.getCompetitions()) {	
					System.out.println("Nom : "+ compet.getNom()+ " \nEn equipe : "+ compet.estEnEquipe()+ "\nCandidats : "+ compet.getCandidats());
					System.out.println("--------------------------------------------------------------------");	}
			}
	}
	
	
}
	