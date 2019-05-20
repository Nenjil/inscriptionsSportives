package gui.controllers.equipe;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gui.controllers.MainController;
import inscriptions.Equipe;
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

public class AjoutMembresController implements Initializable {

	@FXML
	ListView<Personne> LV_membres=  new ListView<Personne>() ;; 

	@FXML
	TextField sucess;
	
	private Stage dialogStage;
	private ObservableList<Personne> membres ;
	private Equipe equipe; 
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setListMembres (Equipe equipe) {
    	setEquipe(equipe);
    	membres = FXCollections.observableArrayList(equipe.getPersonnesAAjouter());
    	//autorise le choix de plusieurs candidats simultanément
    	LV_membres.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		LV_membres.setItems(membres);
    }

    private void setEquipe(Equipe equipe) {
		this.equipe=equipe;
	}
    
    //quand on clique sur valider
    
    @FXML 
    public void handleAddMembres(ActionEvent e) throws IOException {
    	
    	// liste les personnes selections
    	List<Personne> membersSelected =LV_membres.getSelectionModel().getSelectedItems();
    	if(membersSelected.isEmpty()) {
    		MainController.triggerNoSelectionAlert();
    	}
    	else
    	{
    		for (Personne personne : membersSelected) {
				equipe.add(personne);
				sucess.setVisible(true);
				sucess.setText(sucess.getText()+ " ("+personne.getNom()+")");	
			}
    		// on reinitialise la liste
    		setListMembres(equipe);
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
