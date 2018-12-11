package CommandLine.Equipe.Action;

import java.io.IOException;

import commandLineMenus.Action;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Equipe;
import inscriptions.Inscriptions;


public class ActionModifEquipe implements Action{


	private Inscriptions inscriptions;
	
	private Equipe equipe;

	public ActionModifEquipe(Equipe equipe) {
	inscriptions= Inscriptions.getInscriptions();
	this.equipe = equipe;
	}

		@Override
		public void optionSelected() {
			
			String newName = null;
			newName = InOut.getString("Veuillez saisir le nouveau nom de l'équipe ou 'a' pour annuler"
					+ ", ancien nom : " + equipe.getNom() + " nouveau nom : ");
			if(newName != "a" && !newName.isEmpty()){
			try {
			equipe.setNom(newName);
			System.out.println("\nLe nom de l'équipe a bien été changé en : " + equipe.getNom());
			} catch (Exception e) {System.out.println(e.getMessage());}
			
			}
			else if(newName.isEmpty()){
			System.out.println("Votre entrée est vide.");
			}
			
			try {
				inscriptions.sauvegarder();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
		}
		
		
	

