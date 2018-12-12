package CommandLine.Personne.Action;

import commandLineMenus.Action;
import inscriptions.Personne;
import inscriptions.Equipe;
import inscriptions.Inscriptions;

public class ActionDetailsPersonne implements Action {

	private Inscriptions inscriptions;

	public ActionDetailsPersonne(Inscriptions inscriptions) {
	this.inscriptions = inscriptions;
	}

		@Override
		public void optionSelected() {
			if (inscriptions.getPersonnes().isEmpty())
				System.out.println("La liste des personnes est vide");
				else {
					for (Personne personne : inscriptions.getPersonnes()) {	
						System.out.println("------------");
						System.out.println("--------------");
						System.out.println("Nom : "+ personne.getNom());
						System.out.println("Nom : "+ personne.getPrenom());
						System.out.println("Mail : "+ personne.getMail());
						if(personne.getEquipes().isEmpty())System.out.println(" Cette personne fait parti d'aucunes équipes");
						for (Equipe equipe : personne.getEquipes() ) {
						System.out.println(personne.getPrenom()+ " fait partie de l'equipe :"+ equipe.getNom());
						}
						System.out.print("--------------");
						System.out.print("--------------");	}
				}
		}
		
		
	}
		
