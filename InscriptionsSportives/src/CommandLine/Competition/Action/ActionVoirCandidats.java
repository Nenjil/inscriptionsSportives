package CommandLine.Competition.Action;

import commandLineMenus.*;
import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Personne;

public class ActionVoirCandidats implements Action{

	private Competition compet;
	
	public ActionVoirCandidats(Competition compet){
		this.compet = compet;
	}

	@Override
	public void optionSelected() {
		if(compet.getCandidats().isEmpty()){
			System.out.println("Pas de candidats � cette comp�tition");
		}
		else{
			System.out.println("\nCandidats � la comp�tition " + compet.getNom() + ":\n");
			int i = 1;
			for(Candidat c : compet.getCandidats()){
				if(c instanceof Equipe)
					System.out.println(i + " : " + c.toString());
				else
					System.out.println(i + " : " + ((Personne)c).toString());
				i++;
			}
		}
		
	}
}