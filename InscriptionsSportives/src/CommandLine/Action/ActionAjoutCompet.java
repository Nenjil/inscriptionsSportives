package CommandLine.Action;

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
		Boolean enEquipe = false;
		
		inscriptions.reinitialiser();
		try {
	    nom=InOut.getString("\nSaisir le nom de la comp�tition");
	    if(nom.equals("")) throw new NullPointerException("");
		 choixenEquipe =InOut.getString("\nLa comp�tition est-elle pour les �quipes "
				+ "ou les personnes seules ?\n(tapez '1' pour �quipes ou '0' pour personnes)\n");
		if (choixenEquipe.equals("0") && choixenEquipe.equals("1")) throw new NumberFormatException("") ;
		if(choixenEquipe.equals("1"))choixenEquipe = "true";
		enEquipe = Boolean.valueOf(choixenEquipe);
		datecloture = LocalDate.parse(InOut.getString("\nSaisir la date de cl�ture de la "
		+ "comp�tition (au format yyyy-MM-dd)"));
		if (datecloture == null ) throw new DateTimeException("");
		}
		catch(NumberFormatException e) {
			System.out.println("Desol� vous n'avez pas respect� le format pour le choix de la competition en equipe");
		}
		catch(DateTimeException e){
			System.out.println("Desol� vous n'avez pas respect� le format de la date impos�");
		}
		catch(NullPointerException e) {
			System.out.println("Desol� vous n'avez pas respect� le format du nom de competition impos�");
		}
		System.out.println("Avant boucle");
		if(!nom.isEmpty() && (datecloture!=null) && choixenEquipe.equals("0") || choixenEquipe.equals("1")) {
		inscriptions.createCompetition(nom, datecloture, enEquipe);
		System.out.println("Vous venez de creer la competition : "+ nom);}
		else
		System.out.println("Erreur de saisie");
	
	}
	
	
}
	