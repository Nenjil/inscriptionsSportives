package gui.controllers.personne;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import inscriptions.Equipe;
import inscriptions.Personne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class VoirEquipesController implements Initializable {

	@FXML
	ListView<Equipe> LV_equipes=  new ListView<Equipe>() ;; 

	
	private Stage dialogStage;
	private ObservableList<Equipe>equipes ;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setListEquipes (Personne personne) {
    	equipes = FXCollections.observableArrayList(personne.getEquipes());
		LV_equipes.setItems(equipes);
		
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	public void backtoMainMenu(ActionEvent e) throws IOException {
		dialogStage.close();
	}

	

}


