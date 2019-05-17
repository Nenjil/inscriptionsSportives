package gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sun.javafx.collections.ObservableSequentialListWrapper;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuCompetController implements Initializable {

	@FXML 
	ListView<Competition> listview= new ListView<Competition>() ;
	
	@FXML
	private TableView<Competition> competsTable;
	@FXML
	private TableColumn<Competition, String> nomcompet;
	@FXML
	private TableColumn<Competition,String> enequipe;
	@FXML
	private TableColumn<Competition, String> datecloture;
	
	//Pour desactive le bouton quand rien n'est selectionné
	@FXML 
	Button DeleteButton = new Button() ; 
	
	private String previouslocation="";
	
	@FXML
	public void getVoirCompets(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../fxml/VoirCompets.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);	
	}
	
	@FXML
	public void getAjoutCompet(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../fxml/AjoutCompet.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);	
	}
	
	@FXML
	public void getGestionCompets(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../fxml/GestionCompets.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);
		
	}
	
	//Supprime une compet de l'application
	@FXML
	public void handleDeleteCompet(ActionEvent e) throws IOException {
		
		Competition competselected= competsTable.getSelectionModel().getSelectedItem() ;
		
		if(competselected !=null) {
			for (Candidat candidat : competselected.getCandidats()) {
				competselected.remove(candidat)	;
			}
			competselected.delete();
			competsTable.getItems().remove(competselected);
		}
		//rien n'est selectionné en envoie une alert
		else {
	       MainController.triggerNoSelectionAlert();
		}
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.setPreviousLocation(arg0.getFile());
	// boutton de suppresion desactivé tant qu'un objet n'est pas selectionné
	//	DeleteButton.setVisible(false);
		Inscriptions inscriptions =  Inscriptions.getInscriptions();
		ObservableList<Competition>compets = FXCollections.observableArrayList(inscriptions.getCompetitions());
		listview.setItems(compets);
		if(arg0.getFile().endsWith("GestionCompets.fxml")){
		initializeCompetTable(compets);
		}
		
	
	}

	private void initializeCompetTable(ObservableList<Competition> compets) {
		// Initialise chaque ligne de la table view
		// on devrait utiliser les java properties mais je ne veux pas modifier le modele dans le package inscription 
			nomcompet.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom())); 
			enequipe.setCellValueFactory(cellData -> {
				String estEnEquipe="";
				if(cellData.getValue().estEnEquipe())
					estEnEquipe = "Oui";
				else
					estEnEquipe = "Non";
				return new ReadOnlyStringWrapper(estEnEquipe);
			});
  
			datecloture.setCellValueFactory(cellData -> 
			new ReadOnlyStringWrapper(cellData.getValue().getDateCloture().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
			          .withLocale(Locale.FRENCH))));
			
			//association de la table view aux compets
			competsTable.setItems(compets);
			
			// ecoute les changements sur la competTable et affiche les bouttons de suppressions si une compet est selectionnée
		//    competsTable.getSelectionModel().selectedItemProperty().addListener(
		  //  (observable, oldValue, newValue) -> DeleteButton.setVisible(true));
	}
	
	@FXML
	public void backtoMainMenu(ActionEvent e) throws IOException {
		//chargement du xml lié
	    Parent parent = FXMLLoader.load(getClass().getResource("../fxml/"+previouslocation));
	    //creation d'une nouvelle scene basée sur le fxml 
        Scene scene = new Scene(parent);
        //Recuperation de la fenetre principale pour creer une nouvelle scene
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}

	private void setPreviousLocation(String file) {
		if(file.endsWith("VoirCompets.fxml") || file.endsWith("AjoutCompet.fxml") || file.endsWith("GestionCompets.fxml"))
			this.previouslocation="MenuCompet.fxml";
		else
			this.previouslocation="main.fxml";
		
	}
	
	//Modifie une compet de l'application
		@FXML
		public void handleEditCompet(ActionEvent e) throws IOException {
			
			Competition competselected= competsTable.getSelectionModel().getSelectedItem() ;
			if(competselected !=null) {
				showCompetEditDialog(competselected);
				this.previouslocation="GestionCompets.fxml";
				this.backtoMainMenu(e);
			}
			else {
			       MainController.triggerNoSelectionAlert();
				}
		}
		
		//Ajout un candidat dans la compet
				@FXML
				public void handleAddCandidatCompet(ActionEvent e) throws IOException {
					
					Competition competselected= competsTable.getSelectionModel().getSelectedItem() ;
					if(competselected !=null) {
						// TODO 
					}
					else {
					       MainController.triggerNoSelectionAlert();
						}
				}
	
	public void showCompetEditDialog(Competition compet) {
	    try {
	  
	        // chargement du fxml de la boite de dialogue
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("../fxml/EditCompet.fxml"));
	        BorderPane dialogPage = (BorderPane) loader.load();
	        // Cree la fenetre de dialogue
	        Stage dialogStage = new Stage();  
	        dialogStage.setTitle("Modifier une competition");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(null);
	        Scene scene = new Scene(dialogPage);
	        dialogStage.setScene(scene);
	        // initialisation de la compet en recuperant le controller
	        EditCompetController controller = loader.getController();
	        //cela servira pour close la fenetre
	        controller.setDialogStage(dialogStage); 
	        controller.loadCompet(compet);

	        // affiche la boite de dialogue
	        dialogStage.showAndWait();
	        

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	

}
