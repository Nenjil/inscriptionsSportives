package gui.controllers.competition;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import gui.controllers.MainController;
import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Personne;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AjoutCandidatsController implements Initializable {

	@FXML
	ListView<Candidat> LV_candidats=  new ListView<Candidat>() ;; 

	@FXML
	TextField sucess;
	
	private Stage dialogStage;
	private ObservableList<Candidat>candidats ;
	private Competition compet; 
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setListCandidats (Competition compet) {
    	setCompet(compet); 
    	if(compet.estEnEquipe()) {
    		candidats = FXCollections.observableArrayList(compet.getEquipesAInscrire());
    	}
    	else {
    		candidats = FXCollections.observableArrayList(compet.getPersonnesAInscrire());
    	}
    	//autorise le choix de plusieurs candidats simultanément
    	LV_candidats.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		LV_candidats.setItems(candidats);
    }

	private void setCompet(Competition compet) {
		this.compet =compet ;
	}
    
    //quand on clique sur valider
    
    @FXML 
    public void handleAddCandidats(ActionEvent e) throws IOException {
    	
    	List<Candidat> candis =LV_candidats.getSelectionModel().getSelectedItems();
    	
    	if(candis.isEmpty()) {
    		MainController.triggerNoSelectionAlert();
    	}
    	else
    	{
    		for (Candidat candidat : candis) {
				if(compet.estEnEquipe()) {
					compet.add((Equipe)candidat);
				}
				else
					compet.add((Personne)candidat);
				
				sucess.setVisible(true);
				sucess.setText(sucess.getText()+ "("+candidat.getNom()+")");
				
			}
    		// on reinitialise la liste
    		setListCandidats(compet);
    	}
    	
    	
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	public void backtoMainMenu(ActionEvent e) throws IOException {
		dialogStage.close();
	}

	

}
