package CommandLine.Personne.Action;

import commandLineMenus.Action;
import inscriptions.Equipe;
import inscriptions.Personne;


public class ActionVoirEquipe implements Action{
	
	private Personne personne;

	public ActionVoirEquipe(Personne personne) {
	this.personne = personne;
	}

		@Override
		public void optionSelected() {
			
			System.out.print(personne.getPrenom());
			if(personne.getEquipes().isEmpty())
			System.out.println(" ne fait parti d'aucunes équipes");
			else
			System.out.println(" fait partie de : ");
			for (Equipe equipe : personne.getEquipes()) {
			System.out.println("- "+equipe.getNom());	
			}
			}
		
	}
		
		
