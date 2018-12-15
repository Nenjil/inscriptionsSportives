package CommandLine.Competition.Action;

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
	
	try {
		String choix,nom =  null;
		LocalDate dateCloture=null;
		choix = InOut.getString("\nVoulez-vous changer le nom de la compétition ?\n" +
		"Le nom actuel est " + competition.getNom() + ".\n" +
		"Taper 0 pour le changer 1 pour continuer ");
		if (!choix.equals("0") && !choix.equals("1")) throw new NumberFormatException("");
		if(choix.equals("0")) {
			nom = InOut.getString("Taper le nouveau nom de competition : ");
			if(nom.isEmpty()) throw new NullPointerException("");
			if(!nom.isEmpty())
			competition.setNom(nom);
			choix="1";}
		if(choix.equals("1")) {
			choix = InOut.getString("\nVoulez-vous changer la date de cloture de la compétition ?\n" +
			"La date actuelle est " + competition.getDateCloture() + ".\n" +
			"Taper 0 pour le changer 1 pour continuer ");
			if (!choix.equals("0") && !choix.equals("1")) throw new NumberFormatException("");
			if(choix.equals("0")){
				dateCloture = LocalDate.parse(InOut.getString("Taper la nouvelle date au format YYYY-MM-DD "));
			if (dateCloture == null)  throw new DateTimeException("");
			if(!dateCloture.toString().isEmpty())
				competition.setDateCloture(dateCloture);
				choix="1";}
			if (choix.equals("1")) {
				System.out.println("la competition "+competition.getNom()+" a bien été modifiée selon vos choix.");
				try {
					inscriptions.sauvegarder();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		} catch (NumberFormatException e) {
			System.out.println("Erreur lors de la selection.");
		} catch(DateTimeException e){
			System.out.println("Desolé vous n'avez pas respecté le format de la date imposé");
		} catch (NullPointerException e ) {
			System.out.println("Vous n'avez pas entré de nom de competition, l'ancien sera gardé");
	}
	}
}
