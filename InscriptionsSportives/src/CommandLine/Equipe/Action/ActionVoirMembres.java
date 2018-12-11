package CommandLine.Equipe.Action;

import commandLineMenus.Action;
import inscriptions.Equipe;
import inscriptions.Personne;


public class ActionVoirMembres implements Action{
	
	private Equipe equipe;

	public ActionVoirMembres(Equipe equipe) {
	this.equipe = equipe;
	}

		@Override
		public void optionSelected() {
			
			if(equipe.getMembres().isEmpty())
			System.out.println("Il n'y a aucun membres dans cette equipe;");
			else
			System.out.println("Les membres sont : ");
			for (Personne membre : equipe.getMembres()) {
			System.out.println("- "+membre.getNom()+" "+membre.getPrenom());	
			}
			}
		
	}
		
		
	

