package CommandLine.Personne.Action;
import java.io.IOException;

import commandLineMenus.Action;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Personne;
import inscriptions.Inscriptions;


public class ActionModifPersonne implements Action{


	private Inscriptions inscriptions;
	
	private Personne personne;

	public ActionModifPersonne(Personne personne) {
	inscriptions= Inscriptions.getInscriptions();
	this.personne = personne;
	}

		@Override
		public void optionSelected() {
			
			String newFirstName = null,newLastName = null,newMail = null,choix=null;
			// ici un switch aurait ete pas mal ... 
			choix = InOut.getString("\nVoulez-vous changer le nom de la personne ?\n" +
			"Le nom actuel est " + personne.getNom() + ".\n" +
			"Taper 0 pour le changer 1 pour continuer ");
			if(choix.equals("0")) {
				newLastName = InOut.getString("Taper le nouveau nom de la personne : ");
				if(!newLastName.isEmpty())
					personne.setNom(newLastName);
					choix="1";}
			if(choix.equals("1")) {
				choix = InOut.getString("\nVoulez-vous changer le prenom de la personne ?\n" +
				"La prenom actuel est " + personne.getPrenom()+ ".\n" +
				"Taper 0 pour le changer 1 pour continuer ");}
			if(choix.equals("0")) {
				newFirstName = InOut.getString("Taper le nouveau nom de la personne : ");
				if(!newFirstName.isEmpty())
					personne.setPrenom(newFirstName);
					choix="1";}
			if(choix.equals("1")) {
				choix = InOut.getString("\nVoulez-vous changer le mail  de la personne ?\n" +
				"La mail actuel est " + personne.getMail()+ ".\n" +
				"Taper 0 pour le changer 1 pour continuer ");}
			if(choix.equals("0")) {
				newMail = InOut.getString("Taper le nouveau nom de la personne : ");
				if(!newMail.isEmpty())
					personne.setMail(newMail);
					choix="1";}				
				if (choix.equals("1")) {
					System.out.println(personne.getNom()+" a bien été modifiée selon vos choix.");
					try {
					inscriptions.sauvegarder();
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
			if(!newLastName.isEmpty() || newFirstName.isEmpty() || newMail.isEmpty()  )
				{System.out.println("Erreur durant la saisie");}
					
		}		
	}
		
		
	

