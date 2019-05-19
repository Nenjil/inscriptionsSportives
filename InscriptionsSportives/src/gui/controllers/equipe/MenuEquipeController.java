package gui.controllers.equipe;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.controllers.MainController;
import gui.controllers.competition.EditOrCreateCompetController;
import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Personne;
import inscriptions.Inscriptions;
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

public class MenuEquipeController implements Initializable {
	
	@FXML 
	ListView<Equipe> listview= new ListView<Equipe>() ;
	
	@FXML
	private TableView<Equipe> equipesTable;
	@FXML
	private TableColumn<Equipe, String> nomequipe;
	
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
		ObservableList<Equipe> equipes = FXCollections.observableArrayList(inscriptions.getEquipes());
		listview.setItems(equipes);
		if(arg0.getFile().endsWith("GestionEquipes.fxml")){
			initializeEquipeTable(equipes);
		}
		
	
	}

	private void initializeEquipeTable(ObservableList<Equipe> equipes) {
		// Initialise chaque ligne de la table view
		// on devrait utiliser les java properties mais je ne veux pas modifier le modele dans le package inscription 
		nomequipe.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
		//association de la table view aux equipes
		equipesTable.setItems(equipes);
	}
	
	@FXML
	public void getVoirEquipes(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/equipe/VoirEquipes.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);
		
	}
	
	@FXML
	public void getAjoutEquipe(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/equipe/AjoutOrEditEquipe.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);	
	}
	
	@FXML
	public void getGestionEquipes(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/equipe/GestionEquipes.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);
		
	}
	
	public void handleDeleteEquipe (ActionEvent e) throws IOException {
	Equipe equipeselected= equipesTable.getSelectionModel().getSelectedItem() ;
		
		if(equipeselected !=null) {
			for (Personne membre : equipeselected.getMembres()) {
				equipeselected.remove(membre);
			}
			equipeselected.delete();
			equipesTable.getItems().remove(equipeselected);
		}
		//rien n'est selectionné en envoie une alert
		else {
	       MainController.triggerNoSelectionAlert();
		}
		//refresh la table
		this.getGestionEquipes(e);
		
	}
	
	public void handleEditEquipe (ActionEvent e) throws IOException {
		Equipe equipeselected= equipesTable.getSelectionModel().getSelectedItem() ;
		if(equipeselected !=null) {
			showEquipeEditDialog(equipeselected);
			this.previouslocation="equipe/GestionEquipes.fxml";
			this.backtoMainMenu(e);
		}
		else {
		       MainController.triggerNoSelectionAlert();
			}
	}
	
	private void showEquipeEditDialog(Equipe equipe) throws IOException {
	    // chargement du fxml de la boite de dialogue
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../../fxml/equipe/AjoutOrEditEquipe.fxml"));
        BorderPane dialogPage = (BorderPane) loader.load();
        // Cree la fenetre de dialogue
        Stage dialogStage = new Stage();  
        dialogStage.setTitle("Modifier une equipe");
        Scene scene = new Scene(dialogPage);
        dialogStage.setScene(scene);
        // initialisation de la compet en recuperant le controller
        EditOrCreateEquipeController controller = loader.getController();
        //cela servira pour close la fenetre
        controller.setDialogStage(dialogStage,"edition"); 
        controller.loadEquipe(equipe);
        // affiche la boite de dialogue
        dialogStage.showAndWait();
		
		
	}

	public void handleAddMembreEquipe (ActionEvent e) throws IOException {
			
		}
	
	public void handleDeleteMembreEquipe (ActionEvent e) throws IOException {
		
	}
	
	public void handleVoirMembreEquipe (ActionEvent e) throws IOException {
		
	}
	
	
	private void setPreviousLocation(String file) {

		if(file.endsWith("VoirEquipes.fxml") || file.endsWith("GestionEquipes.fxml"))
			this.previouslocation="equipe/MenuEquipe.fxml";
		else
			this.previouslocation="main.fxml";
	}
	
	@FXML
	public void backtoMainMenu(ActionEvent e) throws IOException {
		//chargement du xml lié
	    Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/"+previouslocation));
	    //creation d'une nouvelle scene basée sur le fxml 
        Scene scene = new Scene(parent);
        //Recuperation de la fenetre principale pour creer une nouvelle scene
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}
	

}
