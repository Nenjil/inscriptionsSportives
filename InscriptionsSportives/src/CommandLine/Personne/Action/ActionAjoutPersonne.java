package CommandLine.Personne.Action;
import java.io.IOException;

import commandLineMenus.Action;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Inscriptions;

public class ActionAjoutPersonne implements Action {

	private Inscriptions inscriptions;

	public ActionAjoutPersonne(Inscriptions inscriptions) {
	this.inscriptions = inscriptions;
	}

		@Override
		public void optionSelected() {
			
			String prenomPersonne = "",nomPersonne="",mailPersonne="";
			try {
				nomPersonne = InOut.getString("\nSaisir le nom de la personne.\n'a' pour annuler.");
				if(!nomPersonne.isEmpty() && !nomPersonne.equals("a")) {
				prenomPersonne = InOut.getString("\nSaisir le prenom de la personne.\n'a' pour annuler.");}
				if(!prenomPersonne.isEmpty() && !prenomPersonne.equals("a")) {
				mailPersonne = InOut.getString("\nSaisir le mail de la personne.\n'a' pour annuler.");}
				if (!mailPersonne.contains("@")) throw new Exception("");
				if(!nomPersonne.isEmpty() && !prenomPersonne.isEmpty() && !mailPersonne.isEmpty()){
				inscriptions.createPersonne(nomPersonne, prenomPersonne, mailPersonne);
				System.out.println(nomPersonne+" "+prenomPersonne+" avec le mail"+mailPersonne+" a bien été crée");
				try {
					inscriptions.sauvegarder();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			} catch (Exception e ) {
				System.out.println("Votre adresse Mail n'est pas valide.\n");
				System.out.println("Erreur lors de la création");
			}
			
		}	
		
	}

		
