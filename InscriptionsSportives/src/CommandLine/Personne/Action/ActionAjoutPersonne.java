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
			
				nomPersonne = InOut.getString("\nSaisir le nom de la personne.\n'a' pour annuler.");
				if(!nomPersonne.isEmpty() && !nomPersonne.equals("a")) {
				prenomPersonne = InOut.getString("\nSaisir le prenom de la personne.\n'a' pour annuler.");}
				if(!prenomPersonne.isEmpty() && !prenomPersonne.equals("a")) {
				mailPersonne = InOut.getString("\nSaisir le mail de la personne.\n'a' pour annuler.");}
				if(!nomPersonne.isEmpty() && !prenomPersonne.isEmpty() && !mailPersonne.isEmpty()) {
				try {
				inscriptions.createPersonne(nomPersonne, prenomPersonne, mailPersonne);
				
				}
				catch(Exception e) {
					// TODO
				}
				
				System.out.println(nomPersonne+" "+prenomPersonne+" avec le mail"+mailPersonne+" a bien été crée");
				try {
					inscriptions.sauvegarder();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				else
				System.out.println("Erreur lors de la création");
			
		}	
		
	}

		
