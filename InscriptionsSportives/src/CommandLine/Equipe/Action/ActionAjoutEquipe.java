package CommandLine.Equipe.Action;

import java.io.IOException;

import commandLineMenus.Action;
import commandLineMenus.rendering.examples.util.InOut;

import inscriptions.Inscriptions;


public class ActionAjoutEquipe implements Action {

	private Inscriptions inscriptions;

	public ActionAjoutEquipe(Inscriptions inscriptions) {
	this.inscriptions = inscriptions;
	}

		@Override
		public void optionSelected() {
			String nomEquipe = InOut.getString("\nSaisir le nom de l'équipe.\n'a' pour annuler.");
			if(!nomEquipe.isEmpty() && !nomEquipe.equals("a")){
				try 
				{
					inscriptions.createEquipe(nomEquipe);
					System.out.println("Equipe :"+nomEquipe+" à bien ajoutée.");
				}
				catch (Exception e) 
				{
					System.out.println(e.toString());
				}
				
				if (!Inscriptions.HIBERNATE) {
					try {
						inscriptions.sauvegarder();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
		}
		
		
	}
		