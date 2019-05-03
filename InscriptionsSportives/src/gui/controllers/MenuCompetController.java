package gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.collections.ObservableSequentialListWrapper;

import inscriptions.Competition;
import inscriptions.Inscriptions;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MenuCompetController implements Initializable {

	@FXML 
	ListView<Competition> listview= new ListView<Competition>() ;
	
	@FXML
	public void backtoMainMenu(ActionEvent e) throws IOException {
		//chargement du xml li�
	    Parent parent = FXMLLoader.load(getClass().getResource("../fxml/MenuCompet.fxml"));
	    //creation d'une nouvelle scene bas�e sur le fxml 
        Scene scene = new Scene(parent);
        //Recuperation de la fenetre principale pour creer une nouvelle scene
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}
	
	@FXML
	public void getVoirCompets(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../fxml/VoirCompets.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Inscriptions inscriptions =  Inscriptions.getInscriptions();
		ObservableList<Competition>compets = FXCollections.observableArrayList(inscriptions.getCompetitions());
		listview.setItems(compets);
		
	
	}
	
	

}
