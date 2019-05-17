package gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import inscriptions.Competition;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCompetController implements Initializable {

	@FXML
	TextField nomCompet; 
	
	@FXML 
	DatePicker dateCloture; 
	
	private Stage dialogStage;
	private Competition compet;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Initialise la compet a editer avec les param recues
     *
     * @param compet
     */

    
    public void loadCompet(Competition compet) {
    	  this.compet = compet;
          nomCompet.setText(compet.getNom());
          dateCloture.setValue(compet.getDateCloture());
    }
    
    
    /**
     * Appelé lorsque que l'user clique valider
     */
    @FXML
    private void handleEditCompet() {
    	//Si les champs sont valides
        if (isInputValid()) {
        	compet.setNom(nomCompet.getText());
        	compet.setDateCloture(dateCloture.getValue());
            dialogStage.close();
        }
    }
    
    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String error = "";

        if(nomCompet.getText().isEmpty() || dateCloture.getValue() == null)
        	error="veuillez remplir tout les champs";

        if (error.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(error);
            alert.showAndWait();

            return false;
        }
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	

}
