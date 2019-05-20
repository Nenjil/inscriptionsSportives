package gui.controllers.personne;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import gui.controllers.MainController;
import inscriptions.Equipe;
import inscriptions.Personne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeletePersToEquipeController implements Initializable {

	@FXML
	ListView<Equipe> LV_equipes=  new ListView<Equipe>() ;; 

	@FXML
	TextField sucess;
	
	private Stage dialogStage;
	private ObservableList<Equipe> equipes ;
	private Personne personne; 
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setListEquipes (Personne personne) {
    	setPersonne(personne);

    	equipes = FXCollections.observableArrayList(personne.getEquipes());
    	//autorise le choix de plusieurs candidats simultanément
    	LV_equipes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		LV_equipes.setItems(equipes);
    }

    private void setPersonne(Personne personne) {
    	this.personne=personne ;
    }
    
    //quand on clique sur valider
    
    @FXML 
    public void handleDeletePersToEquipes(ActionEvent e) throws IOException {
        	List<Equipe> equipeSelected =LV_equipes.getSelectionModel().getSelectedItems();
        	
        	if(equipeSelected.isEmpty()) {
        		MainController.triggerNoSelectionAlert();
        	}
        	else
        	{
        		Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Attention action irréversible");
    			alert.setContentText("Etes vous sure de vouloir detacher les equipes selectionnées à cette personne ? ");
    			Optional<ButtonType> result = alert.showAndWait();
    			if (result.get() == ButtonType.OK){
    				for (Equipe equipe : equipeSelected) {
    	    			equipe.remove(personne);
    					sucess.setVisible(true);
    					sucess.setText(sucess.getText()+ "("+equipe.getNom()+")");
    				}
    			} 
    			else {
    			    alert.close();
    			}
        	
        		// on reinitialise la liste
        		setListEquipes(personne);
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

    




