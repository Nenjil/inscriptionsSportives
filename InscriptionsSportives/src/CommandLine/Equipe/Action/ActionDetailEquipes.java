package CommandLine.Equipe.Action;
import commandLineMenus.Action;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class ActionDetailEquipes implements Action {

	private Inscriptions inscriptions;

	public ActionDetailEquipes(Inscriptions inscriptions) {
	this.inscriptions = inscriptions;
	}

		@Override
		public void optionSelected() {
			if (inscriptions.getEquipes().isEmpty())
				System.out.println("La liste des equipes est vide");
				else {
					for (Equipe equipe : inscriptions.getEquipes()) {	
						System.out.println("------------");
						System.out.println("--------------");
						System.out.println("Nom : "+ equipe.getNom());
						for (Personne membre : equipe.getMembres() ) {
						System.out.println(membre.getPrenom()+" "+membre.getNom()+" fait partie de cette équipe");
						}
						if(equipe.getMembres().isEmpty())System.out.println("Il n'y a pas encore de membres dans cette équipe.");
						System.out.print("--------------");
						System.out.print("--------------");	}
				}
		}
		
		
	}
		