package CommandLine.Action;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

import commandLineMenus.Action;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Competition;
import inscriptions.Inscriptions;

public class ActionModifCompetition implements Action {

	
	private Competition competition;
	private Inscriptions inscriptions;

	public ActionModifCompetition(Competition competition) {
		this.competition = competition ;
		inscriptions = Inscriptions.getInscriptions();
		
	}
	
	@Override
	public void optionSelected() {
	
		
		String choix,nom =  null;
		LocalDate dateCloture=null;
		Boolean valider = false;
	
		do {
			choix = InOut.getString("\nVoulez-vous changer le nom de la compétition ?\n" +
			"Le nom actuel est " + competition.getNom() + ".\n" +
			"Taper 0 pour le changer 1 pour continuer ");
			if(choix.equals("0")) {
				nom = InOut.getString("Taper le nouveau nom de competition : ");
				competition.setNom(nom);
				choix="1";}
			 if(choix.equals("1")) {
				choix = InOut.getString("\nVoulez-vous changer la date de cloture de la compétition ?\n" +
				"La date actuelle est " + competition.getDateCloture() + ".\n" +
				"Taper 0 pour le changer 1 pour continuer ");
			if(choix.equals("0")) {
				dateCloture = LocalDate.parse(InOut.getString("Taper la nouvelle date au format YYYY-MM-DD "));
				competition.setDateCloture(dateCloture);
				choix="1";}
		    if (choix.equals("1")) {
				System.out.println("la competition"+competition.getNom()+" a bien été modidifiée");
			}
			
			}
			else
			{System.out.println("Erreur durant la saisie veuillez recommencer");
			valider=false;};
					
					}while(valider);
		try {
			inscriptions.sauvegarder();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
		
		//System.out.println("Avant boucle");
		
	
	}
}
