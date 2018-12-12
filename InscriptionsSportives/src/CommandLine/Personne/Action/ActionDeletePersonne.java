package CommandLine.Personne.Action;
import java.io.IOException;
import commandLineMenus.Action;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Personne;
import inscriptions.Inscriptions;

public class ActionDeletePersonne implements Action{

	private Inscriptions inscriptions;
	
	private Personne personne;

	public ActionDeletePersonne(Personne personne) {
	inscriptions= Inscriptions.getInscriptions();
	this.personne = personne;
	}

		@Override
		public void optionSelected() {
		
		int choix = InOut.getInt("Etes vous sur de vouloir supprimer :  "+personne.getNom()+" taper 1 si Oui");
		if(choix==1) {
			personne.delete();
			System.out.println("Suppression effectuée");
			try {
				inscriptions.sauvegarder();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
		
		
}