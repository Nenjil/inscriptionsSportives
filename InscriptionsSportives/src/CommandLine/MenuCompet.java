package CommandLine;
import java.time.LocalDate;
import java.time.Month;

import commandLineMenus.Action;
import inscriptions.*;
//import commandLineMenus.*;
//import CommandLine.Action.DetailCompet;
import commandLineMenus.Menu;
import commandLineMenus.Option;

public class MenuCompet {
	static Inscriptions inscriptions = Inscriptions.getInscriptions();
	

	private MenuCompet() {

	}

	public static Menu createMenuCompet(String longTitle, String shortTitle, String shortcut) {
	
		
		Menu ShortTitle = new Menu(longTitle, shortTitle,shortcut);

		
		
		Option detailsCompet = new Option ("Details Competitions", "dC");
		ShortTitle.add(detailsCompet);
		detailsCompet.setAction(new Action() {
			@Override
			public void optionSelected() {
				if (inscriptions.getCompetitions().isEmpty())
				System.out.println("La liste des competitions est vide");
				else {
					for (Competition compet : inscriptions.getCompetitions()) {	
						System.out.println("Nom : "+ compet.getNom()+ " \nEn equipe : "+ compet.estEnEquipe()+ "\nCandidats : "+ compet.getCandidats());
						System.out.println("--------------------------------------------------------------------");	
						
					}
		       }
			}
		});
		
		Option ajoutCompet = new Option ("Ajout Competitions", "aC");
		ShortTitle.add(ajoutCompet);
		ajoutCompet.setAction(new Action() {
		@Override
		public void optionSelected() {
			/*
			LocalDate datecloture = null;
			String nom = null;
			boolean enEquipe = false;
			
			 inscriptions.createCompetition(nom, datecloture, enEquipe);	
			
			*/
		}});
		
		
		
		
		Menu SelectCompetition = new Menu ("Choix Competitions", "cC");
		ShortTitle.add(SelectCompetition);
		Option CandidateList = new Option ("Voir la liste des candidats", "vc");
		SelectCompetition.add(CandidateList);
		Option AddCandidate = new Option ("Ajout d'un candidat" , "ac");
		SelectCompetition.add(AddCandidate);
		Option DeleteCandidate = new Option ("Suppression d'un candidat" , "sc");
		SelectCompetition.add(DeleteCandidate);
		Option ModifyCompetition = new Option ("Modifier une competition" , "mC");
		SelectCompetition.add(ModifyCompetition);
		Option DeleteCompetition = new Option ("Suppression d'une competition" , "sC");
		SelectCompetition.add(DeleteCompetition);
		SelectCompetition.addBack("b");
		
		return ShortTitle;
		
	
	}
	
}
