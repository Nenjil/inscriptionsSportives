package gui.controllers;

import java.io.IOException;
import inscriptions.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainController implements Initializable {

	
	@FXML
	public void getCompetMenu(ActionEvent e) throws IOException {
		//chargement du xml li�
	    Parent parent = FXMLLoader.load(getClass().getResource("../fxml/competition/MenuCompet.fxml"));
	    //creation d'une nouvelle scene bas�e sur le xml 
        Scene scene = new Scene(parent);
        //scene.getStylesheets().add(getClass().getResource("css/main.css").toString());
        //Recuperation de la fenetre principale pour creer une nouvelle scene
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}
	
	@FXML
	public void getEquipeMenu(ActionEvent e) throws IOException {
	    Parent parent = FXMLLoader.load(getClass().getResource("../fxml/equipe/MenuEquipe.fxml"));
        Scene scene = new Scene(parent);
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}
	
	@FXML
	public void getPersonneMenu(ActionEvent e) throws IOException {
	    Parent parent = FXMLLoader.load(getClass().getResource("../fxml/personne/MenuPersonne.fxml"));
        Scene scene = new Scene(parent);
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		@SuppressWarnings("unused")
		Inscriptions inscriptions = Inscriptions.getInscriptions();
	}
	
	public static void triggerNoSelectionAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(null);
		alert.setTitle("Pas de selection");
		alert.setHeaderText("Aucun �l�ment selectionn�");
		alert.setContentText("Veuillez dabord selectionner un �l�ment.");
		alert.showAndWait();
	}
	

}
