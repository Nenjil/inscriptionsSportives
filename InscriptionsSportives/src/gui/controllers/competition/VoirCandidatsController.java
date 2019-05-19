package gui.controllers.competition;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import inscriptions.Candidat;
import inscriptions.Competition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
public class VoirCandidatsController implements Initializable {

	@FXML
	ListView<Candidat> LV_candidats=  new ListView<Candidat>() ;; 

	
	private Stage dialogStage;
	private ObservableList<Candidat>candidats ;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setListCandidats (Competition compet) {
    	candidats = FXCollections.observableArrayList(compet.getCandidats());
		LV_candidats.setItems(candidats);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	public void backtoMainMenu(ActionEvent e) throws IOException {
		dialogStage.close();
	}

	

}
