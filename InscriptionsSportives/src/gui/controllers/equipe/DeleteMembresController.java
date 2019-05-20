package gui.controllers.equipe;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import gui.controllers.MainController;
import inscriptions.Personne;
import inscriptions.Equipe;
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

public class DeleteMembresController implements Initializable {

	@FXML
	ListView<Personne> LV_membres=  new ListView<Personne>() ;

	@FXML
	TextField sucess;
	
	private Stage dialogStage;
	private ObservableList<Personne>membres ;
	private Equipe equipe; 
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setListMembres (Equipe equipe) {
    	setCompet(equipe); 
    		membres = FXCollections.observableArrayList(equipe.getMembres());
    
    	//autorise le choix de plusieurs candidats simultanément
    	LV_membres.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		LV_membres.setItems(membres);
    }

	private void setCompet(Equipe equipe) {
		this.equipe =equipe ;
	}
    
    //quand on clique sur valider
    
    @FXML 
    public void handleDeleteMembres(ActionEvent e) throws IOException {
    	List<Personne> membersSelected =LV_membres.getSelectionModel().getSelectedItems();
    	
    	if(membersSelected.isEmpty()) {
    		MainController.triggerNoSelectionAlert();
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Attention action irréversible");
			alert.setContentText("Etes vous sure de vouloir supprimer les candidats selectionnés ? ");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				for (Personne membre : membersSelected) {
	    			equipe.remove(membre);
					sucess.setVisible(true);
					sucess.setText(sucess.getText()+ "("+membre.getNom()+")");
				}
			} 
			else {
			    alert.close();
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
