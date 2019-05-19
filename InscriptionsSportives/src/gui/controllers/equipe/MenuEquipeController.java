package gui.controllers.equipe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import inscriptions.Equipe;
import inscriptions.Inscriptions;
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

public class MenuEquipeController implements Initializable {
	
	@FXML
	ListView<Equipe> listview=new ListView<Equipe>();
	
	private String previouslocation="";
	

	
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
	
	@FXML
	public void getVoirEquipes(ActionEvent e) throws IOException {
		  Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/VoirEquipes.fxml"));
	      Scene scene = new Scene(parent);
		  Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		  primaryStage.setScene(scene);
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Inscriptions inscriptions =  Inscriptions.getInscriptions();
		ObservableList<Equipe>equipes = FXCollections.observableArrayList(inscriptions.getEquipes());
		listview.setItems(equipes);
		
		this.setPreviousLocation(arg0.getFile());
	}
	
	private void setPreviousLocation(String file) {

		if(file.endsWith("VoirEquipes.fxml"))
			this.previouslocation="MenuEquipe.fxml";
		else
			this.previouslocation="main.fxml";
	}
	

}
