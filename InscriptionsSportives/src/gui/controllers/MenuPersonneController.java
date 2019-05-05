package gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MenuPersonneController implements Initializable {
	 
	//cette variable va charger la dans la méthode initialize() la scene précédente
	private String previouslocation="";
	  
	 
	@FXML
	ListView <Personne> listview= new ListView<Personne>();
	
	@FXML
	public void backtoMainMenu(ActionEvent e) throws IOException {
		//chargement de la scene precedente
	    Parent parent = FXMLLoader.load(getClass().getResource("../fxml/"+previouslocation));
	    //creation d'une nouvelle scene basée sur le fxml 
	  //  System.out.println(getClass().getResource());
        Scene scene = new Scene(parent);
       // System.out.println(this.location.getFile());
        //Recuperation de la fenetre principale pour creer une nouvelle scene
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}
	
	@FXML
	public void getVoirPersonnes(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../fxml/VoirPersonnes.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	/*	Inscriptions inscriptions =  Inscriptions.getInscriptions();
		ObservableList<Personne>personnes = FXCollections.observableArrayList(inscriptions.getPersonnes());
		listview.setItems(personnes);*/
		this.setPreviousLocation(arg0.getFile());

	}
	
	private void setPreviousLocation(String file) {
		if(file.endsWith("VoirPersonnes.fxml"))
		{
			this.previouslocation="MenuPersonne.fxml"; 
		}
		else 
			this.previouslocation="main.fxml";
		
	}

}
