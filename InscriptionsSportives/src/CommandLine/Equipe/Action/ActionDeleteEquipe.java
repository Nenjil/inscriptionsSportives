package CommandLine.Equipe.Action;
import java.io.IOException;

import commandLineMenus.Action;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class ActionDeleteEquipe implements Action{

	private Inscriptions inscriptions;
	
	private Equipe equipe;

	public ActionDeleteEquipe(Equipe equipe) {
	inscriptions= Inscriptions.getInscriptions();
	this.equipe = equipe;
	}

		@Override
		public void optionSelected() {
		
		int choix = InOut.getInt("Etes vous sur de vouloir supprimer l'équipe :  "+equipe.getNom()+"  (les candidats y seront supprimés) taper 1 si Oui");
		if(choix==1) {
		if(!equipe.getMembres().isEmpty())	
			for (Personne membre : equipe.getMembres()) {
			System.out.println(membre.getPrenom()+membre.getNom()+" vient d'être supprimé(e) de l'équipe.");
			equipe.remove(membre);
			}
		equipe.delete();

		System.out.println("Suppression effectuée");
		try {
			inscriptions.sauvegarder();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
		
		
	}
