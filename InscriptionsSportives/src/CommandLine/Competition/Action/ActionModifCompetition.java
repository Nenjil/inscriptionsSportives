package CommandLine.Competition.Action;

import java.io.IOException;
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
	//Il faudrait gerer les exceptions 
		choix = InOut.getString("\nVoulez-vous changer le nom de la comp�tition ?\n" +
		"Le nom actuel est " + competition.getNom() + ".\n" +
		"Taper 0 pour le changer 1 pour continuer ");
		if(choix.equals("0")) {
			nom = InOut.getString("Taper le nouveau nom de competition : ");
			if(!nom.isEmpty())
			competition.setNom(nom);
			choix="1";}
		if(choix.equals("1")) {
			choix = InOut.getString("\nVoulez-vous changer la date de cloture de la comp�tition ?\n" +
			"La date actuelle est " + competition.getDateCloture() + ".\n" +
			"Taper 0 pour le changer 1 pour continuer ");
			if(choix.equals("0")) {
				dateCloture = LocalDate.parse(InOut.getString("Taper la nouvelle date au format YYYY-MM-DD "));
			if(!dateCloture.toString().isEmpty())
				competition.setDateCloture(dateCloture);
				choix="1";}
			if (choix.equals("1")) {
				System.out.println("la competition "+competition.getNom()+" a bien �t� modifi�e selon vos choix.");
				try {
					inscriptions.sauvegarder();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(!nom.isEmpty() || dateCloture==null)
			{System.out.println("Erreur durant la saisie");}
		
	}
}
