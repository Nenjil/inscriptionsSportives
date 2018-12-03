package CommandLine.Action;

import commandLineMenus.Action;
import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class DetailCompet implements Action{
	
	public DetailCompet() {
		
		ActionVoirCompetitions();
	}

	private Inscriptions inscriptions;

	public void ActionVoirCompetitions(){
		this.inscriptions = Inscriptions.getInscriptions();
	}
	@Override
	public void optionSelected(){
		if(inscriptions.getCompetitions().isEmpty()){
			System.out.println("\nIl n'y a pas de compétition enregistrée.");
		}
		else{
			for(Competition c : inscriptions.getCompetitions()){
				String membres = "";
				for(Candidat ca : c.getCandidats()){
					if(ca instanceof Equipe)
						membres += ca.getNom() + " | ";
					else if(ca instanceof Personne)
						membres += ((Personne)ca).toString() + " | ";
				}
				System.out.println("\nNom : " + c.toString() + "\n" +
								   "Date de cloture : " + c.getDateCloture() + "\n" +
								   "En équipe : " + c.estEnEquipe()+ "\n" +
								   "Candidats : " + membres);
			}
		}
	}
}
