package CommandLine.Competition.Action;

import java.io.IOException;


import commandLineMenus.Action;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;

public class ActionDeleteCompetition implements Action {

	
	private Competition competition;
	private Inscriptions inscriptions;

	public ActionDeleteCompetition(Competition competition) {
		this.competition = competition ;
		inscriptions = Inscriptions.getInscriptions();
		
	}
	
	@Override
	public void optionSelected() {
	
		String rep =InOut.getString("Etes vous sur de vouloir supprimer la competition (O pour valider , n'importe quel autre caractère pour ne pas valider) (cela supprimera tout les candidats "+competition.getNom()+" ?");
		rep.toUpperCase();
		if(rep.equals("O")) {
		for (Candidat candidat : competition.getCandidats()) {
		competition.remove(candidat)	;
		}
		competition.delete();
		System.out.println("La competition a bien eté supprimée");
		}
		else
		System.out.println("La competition n'est pas supprimée");
		try {
			inscriptions.sauvegarder();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
