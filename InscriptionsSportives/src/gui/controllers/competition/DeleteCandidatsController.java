package gui.controllers.competition;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import gui.controllers.MainController;
import inscriptions.Candidat;
import inscriptions.Competition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteCandidatsController implements Initializable {

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
    		candidats = FXCollections.observableArrayList(compet.getCandidats());
    
    	//autorise le choix de plusieurs candidats simultanément
    	LV_candidats.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		LV_candidats.setItems(candidats);
    }

	private void setCompet(Competition compet) {
		this.compet =compet ;
	}
    
    //quand on clique sur valider
    
    @FXML 
    public void handleDeleteCandidats(ActionEvent e) throws IOException {
    	List<Candidat> candis =LV_candidats.getSelectionModel().getSelectedItems();
    	
    	if(candis.isEmpty()) {
    		MainController.triggerNoSelectionAlert();
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Attention action irréversible");
			alert.setContentText("Etes vous sure de vouloir supprimer les candidats selectionnés ? ");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				for (Candidat candidat : candis) {
	    			compet.remove(candidat);
					sucess.setVisible(true);
					sucess.setText(sucess.getText()+ "("+candidat.getNom()+")");
				}
			} 
			else {
			    alert.close();
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
