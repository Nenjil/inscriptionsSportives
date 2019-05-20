package gui.controllers.personne;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.controllers.MainController;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjoutPersToEquipeController implements Initializable {

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
    	//On recupere toute les equipes sauf celles dans lesquelles il est deja
    	List<Equipe> team = new ArrayList<>(Inscriptions.getInscriptions().getEquipes());
    	team.removeAll(personne.getEquipes());
    	
    	equipes = FXCollections.observableArrayList(team);
    	//autorise le choix de plusieurs candidats simultanément
    	LV_equipes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		LV_equipes.setItems(equipes);
    }

    private void setPersonne(Personne personne) {
    	this.personne=personne ;
    }
    
    //quand on clique sur valider
    
    @FXML 
    public void handleAddPersToEquipes(ActionEvent e) throws IOException {
    	
    	// liste les personnes selections
    	List<Equipe> equipesSelected =LV_equipes.getSelectionModel().getSelectedItems();
    	if(equipesSelected.isEmpty()) {
    		MainController.triggerNoSelectionAlert();
    	}
    	else
    	{
    		for (Equipe team : equipesSelected) {
				team.add(personne);
				sucess.setVisible(true);
				sucess.setText(sucess.getText()+ " ("+team.getNom()+")");	
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
