package gui.controllers;

import java.io.IOException;
import inscriptions.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;

public class MainController implements Initializable {

	
	@FXML
	public void getCompetMenu(ActionEvent e) throws IOException {
		//chargement du xml li�
	    Parent parent = FXMLLoader.load(getClass().getResource("../fxml/MenuCompet.fxml"));
	    //creation d'une nouvelle scene bas�e sur le xml 
        Scene scene = new Scene(parent);
        //scene.getStylesheets().add(getClass().getResource("css/main.css").toString());
        //Recuperation de la fenetre principale pour creer une nouvelle scene
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}
	
	@FXML
	public void getEquipeMenu(ActionEvent e) throws IOException {
	    Parent parent = FXMLLoader.load(getClass().getResource("../fxml/MenuEquipe.fxml"));
        Scene scene = new Scene(parent);
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}
	
	@FXML
	public void getPersonneMenu(ActionEvent e) throws IOException {
	    Parent parent = FXMLLoader.load(getClass().getResource("../fxml/MenuPersonne.fxml"));
        Scene scene = new Scene(parent);
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Inscriptions inscriptions = Inscriptions.getInscriptions();

		
	}
	
	

}