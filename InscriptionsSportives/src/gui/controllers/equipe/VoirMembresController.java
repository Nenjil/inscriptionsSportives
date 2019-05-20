package gui.controllers.equipe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import inscriptions.Personne;
import inscriptions.Equipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
public class VoirMembresController implements Initializable {

	@FXML
	ListView<Personne> LV_membres=  new ListView<Personne>() ;; 

	
	private Stage dialogStage;
	private ObservableList<Personne>membres ;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setListMembres (Equipe equipe) {
    	membres = FXCollections.observableArrayList(equipe.getMembres());
		LV_membres.setItems(membres);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	public void backtoMainMenu(ActionEvent e) throws IOException {
		dialogStage.close();
	}

	

}
