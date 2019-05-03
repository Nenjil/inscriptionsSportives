package CommandLine.Competition.Action;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import commandLineMenus.Action;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Inscriptions;


public class ActionAjoutCompet implements Action{
private Inscriptions inscriptions;

public ActionAjoutCompet(Inscriptions inscriptions) {
this.inscriptions = inscriptions;
}

	@Override
	public void optionSelected() {
		LocalDate datecloture =null ;
		String nom = "",choixenEquipe = "";
		Boolean enEquipe = null;
		LocalDate Dateactuelle = LocalDate.now();
		String quit = "0"; 
		//inscriptions.reinitialiser();
		while(!quit.equals("1") ){
		try {
			    nom=InOut.getString("\n Saisir le nom de la compétition");
			    if(nom.equals("")) 
			    	throw new NullPointerException("");
			    choixenEquipe =InOut.getString("\nLa compétition est-elle pour les équipes "
				+ "ou les personnes seules ?\n(tapez '1' pour équipes ou '0' pour personnes)\n");
				if (!choixenEquipe.equals("0") && !choixenEquipe.equals("1") || choixenEquipe.isEmpty()) 
						throw new NumberFormatException("") ;
				if (choixenEquipe.equals("1"))choixenEquipe = "true";
						enEquipe = Boolean.parseBoolean(choixenEquipe);
				
				datecloture = LocalDate.parse(InOut.getString("\nSaisir la date de clôture de la "
				+ "compétition (au format yyyy-MM-dd)"));
						
				if (datecloture == null || datecloture.compareTo(Dateactuelle) < 0 ) 
				throw new DateTimeException("");
		
				}
		
			catch(NumberFormatException e) {
				System.out.println("Desolé vous n'avez pas respecté le format pour le choix de la competition en equipe");
			}
			catch(DateTimeException e){
				System.out.println("Désolé la date doit ne doit ni être nulle ni depassée");
			}
			catch(NullPointerException e) {
				System.out.println("Desolé vous n'avez pas respecté le format du nom de competition imposé");
			}
			quit =InOut.getString("Taper 0 pour recommencer la saisie "
				+ "ou 1 pour valider \n");
		
		}
	
		if((!nom.isEmpty()) && (datecloture!=null) && (enEquipe!=null)) {
			inscriptions.createCompetition(nom, datecloture, enEquipe);
			System.out.println("Vous venez de creer la competition : "+ nom);
		}
		else
			System.out.println("Erreur lors de la création");
			
		if (!Inscriptions.HIBERNATE) {
			try {
				inscriptions.sauvegarder();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
}
	