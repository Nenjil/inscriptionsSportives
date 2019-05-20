package gui.controllers.personne;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import gui.controllers.MainController;
import inscriptions.Personne;
import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Equipe;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuPersonneController implements Initializable {
	@FXML 
	ListView<Personne> listview= new ListView<Personne>() ;
	
	@FXML
	private TableView<Personne> personnesTable;
	@FXML
	private TableColumn<Personne, String> nompersonne;
	@FXML
	private TableColumn<Personne, String> prenompersonne;
	
	@FXML
	private TableColumn<Personne, String> mailpersonne;
	
	//Pour desactive le bouton quand rien n'est selectionné
	@FXML 
	Button DeleteButton = new Button() ; 
	private String previouslocation="";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.setPreviousLocation(arg0.getFile());
	// boutton de suppresion desactivé tant qu'un objet n'est pas selectionné
	//	DeleteButton.setVisible(false);
		Inscriptions inscriptions =  Inscriptions.getInscriptions();
		ObservableList<Personne> personnes = FXCollections.observableArrayList(inscriptions.getPersonnes());
		listview.setItems(personnes);
		if(arg0.getFile().endsWith("GestionPersonnes.fxml")){
			initializePersonneTable(personnes);
		}
	}

	private void initializePersonneTable(ObservableList<Personne> personnes) {
		// Initialise chaque ligne de la table view
		// on devrait utiliser les java properties mais je ne veux pas modifier le modele dans le package inscription 
		nompersonne.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
		prenompersonne.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPrenom()));
		mailpersonne.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getMail()));
		//association de la table view aux equipes
		personnesTable.setItems(personnes);
	}
		
	@FXML
	public void getVoirPersonnes(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/personne/VoirPersonnes.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);
		
	}
	
	@FXML
	public void getAjoutPersonne(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/personne/AjoutOrEditPersonne.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);	
	}
	
	@FXML
	public void getGestionPersonnes(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/personne/GestionPersonnes.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);
	}
	
	@FXML
	public void handleDeletePersonne (ActionEvent e) throws IOException {
	Personne personneselected= personnesTable.getSelectionModel().getSelectedItem() ;
		if(personneselected !=null) {
			deletehimFromCompets(personneselected);
			deletehimFromEquipes(personneselected);
			personneselected.delete();
			personnesTable.getItems().remove(personneselected);
		}
		//rien n'est selectionné en envoie une alert
		else {
	       MainController.triggerNoSelectionAlert();
		}
		//refresh la table
		this.getGestionPersonnes(e);
	}

	protected void deletehimFromCompets(Personne personneselected) {
		//Pour supprimer tous les membres d'une liste unmodifiable il faut passer par un iterateur et/ou cloner la liste
		List<Competition> modifiable = new ArrayList<Competition>(personneselected.getCompetitions());
		for (Iterator<Competition> iterator = modifiable.iterator(); iterator.hasNext();) {
		    Competition compet = iterator.next();
		    compet.remove(personneselected);
		    iterator.remove();
		}
	}

	protected void deletehimFromEquipes(Personne personneselected) {
		//Pour supprimer tous les membres d'une liste unmodifiable il faut passer par un iterateur et/ou cloner la liste
		List<Equipe> modifiable = new ArrayList<Equipe>(personneselected.getEquipes());
		for (Iterator<Equipe> iterator = modifiable.iterator(); iterator.hasNext();) {
			Equipe equipe = iterator.next();
		    equipe.remove(personneselected);
		    iterator.remove();
		}
	}
	
	
	
	@FXML
	public void handleEditPersonne (ActionEvent e) throws IOException {
		Personne personneselected= personnesTable.getSelectionModel().getSelectedItem() ;
		if(personneselected !=null) {
			showPersonneEditDialog(personneselected);
			this.previouslocation="personne/GestionPersonnes.fxml";
			this.backtoMainMenu(e);
		}
		else {
		       MainController.triggerNoSelectionAlert();
			}
	}
	
	private void showPersonneEditDialog(Personne personne) throws IOException {
	    // chargement du fxml de la boite de dialogue
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../../fxml/personne/AjoutOrEditPersonne.fxml"));
        BorderPane dialogPage = (BorderPane) loader.load();
        // Cree la fenetre de dialogue
        Stage dialogStage = new Stage();  
        dialogStage.setTitle("Modifier une personne");
        Scene scene = new Scene(dialogPage);
        dialogStage.setScene(scene);
        // initialisation de la compet en recuperant le controller
        EditOrCreatePersonneController controller = loader.getController();
        //cela servira pour close la fenetre
        controller.setDialogStage(dialogStage,"edition"); 
        controller.loadPersonne(personne);
        // affiche la boite de dialogue
        dialogStage.showAndWait();
		
		
	}
	
	public void handleAddPersonneToEquipe (ActionEvent e) throws IOException {
	
		Personne personneselected= personnesTable.getSelectionModel().getSelectedItem() ;
		if(personneselected !=null) {
			// methode qui ouvre une fenetre avec la liste des candidats a ajouter
			showEquipePersonneDialog(personneselected,"AjoutPersToEquipes");
		}
		else {
		       MainController.triggerNoSelectionAlert();
			}
			
		}
	
	private void showEquipePersonneDialog(Personne personne,String mode) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../fxml/personne/"+mode+".fxml"));
		BorderPane dialogPage = (BorderPane) loader.load();
		//creation dune fenetre avec une scene 
		Stage dialogStage = new Stage();
		Scene scene = new Scene(dialogPage);
		dialogStage.setScene(scene);
		// initialisation de la compet en recuperant le controller

		if(mode.equals("AjoutPersToEquipes")) {
		AjoutPersToEquipeController controller = loader.getController();
	    controller.setDialogStage(dialogStage);
	    controller.setListEquipes(personne);
		}
		else {
			DeletePersToEquipeController controller = loader.getController();
		    controller.setDialogStage(dialogStage);
		    controller.setListEquipes(personne);
		}
	    
	    
        // affiche la boite de dialogue
        dialogStage.showAndWait();

	}

	public void handleDeletePersonneToEquipe (ActionEvent e) throws IOException {
		
		Personne personneselected= personnesTable.getSelectionModel().getSelectedItem() ;
		if(personneselected !=null) {
			// methode qui ouvre une fenetre avec la liste des candidats a ajouter
			showEquipePersonneDialog(personneselected,"DeletePersToEquipes");
		}
		else {
		       MainController.triggerNoSelectionAlert();
			}
	}
	
	public void handleVoirEquipesPersonne (ActionEvent e) throws IOException {
		Personne personneselected= personnesTable.getSelectionModel().getSelectedItem() ;
		if(personneselected !=null) {
			// methode qui ouvre une fenetre avec la liste des candidats a ajouter
			showVoirEquipeDialog(personneselected);
		}
		else {
		       MainController.triggerNoSelectionAlert();
			}
	}
	
	
	private void showVoirEquipeDialog(Personne personne) throws IOException {
		  FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("../../fxml/equipe/VoirEquipes.fxml"));
	        BorderPane dialogPage = (BorderPane) loader.load();
	        Stage dialogStage = new Stage();  
	        Scene scene = new Scene(dialogPage);
	        dialogStage.setScene(scene);
	        VoirEquipesController controller = loader.getController();
	        controller.setDialogStage(dialogStage); 
	        controller.setListEquipes(personne);
	        dialogStage.showAndWait();
	}
	
	
	private void setPreviousLocation(String file) {
		if(file.endsWith("VoirPersonnes.fxml") || file.endsWith("GestionPersonnes.fxml") )
		{
			this.previouslocation="personne/MenuPersonne.fxml"; 
		}
		else 
			this.previouslocation="main.fxml";
		
	}
	
	@FXML
	public void backtoMainMenu(ActionEvent e) throws IOException {
		//chargement de la scene precedente
	    Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/"+previouslocation));
	    //creation d'une nouvelle scene basée sur le fxml 
	  //  System.out.println(getClass().getResource());
        Scene scene = new Scene(parent);
       // System.out.println(this.location.getFile());
        //Recuperation de la fenetre principale pour creer une nouvelle scene
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}
	


}
