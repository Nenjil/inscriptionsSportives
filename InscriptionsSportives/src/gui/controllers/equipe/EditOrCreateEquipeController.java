package gui.controllers.equipe;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class EditOrCreateEquipeController implements Initializable {

	@FXML
	TextField nomEquipe; 
	@FXML
	TextField sucess;
	@FXML
	Button BtnValider;
	
	private Stage dialogStage;
	private Equipe equipe;
	private String mode ="creation" ;
	
    public void setDialogStage(Stage dialogStage, String mode) {
        this.dialogStage = dialogStage;
        this.mode=mode;
    }
    
    /**
     * Initialise lequipe a editer avec les param recues
     *
     * @param compet
     */
    public void loadEquipe(Equipe equipe) {
    	  this.equipe = equipe;
          nomEquipe.setText(equipe.getNom());
    }
    
    
    
    @FXML
    private void handleCreateOrEditEquipe() throws IOException {
        if(isInputValid()) {
        	if(mode.equals("creation")) {
		    	Inscriptions.getInscriptions().createEquipe(nomEquipe.getText());
        	}
        	else
        	{
        		equipe.setNom(nomEquipe.getText());
        		sucess.setText("L'equipe a bien ete modifiee");
        	}
        	sucess.setVisible(true);
		    BtnValider.setVisible(false); 
        }
    }
    
    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String error = "";

        if(nomEquipe.getText().isEmpty())
        	error="veuillez remplir tout les champs";
       
        
        if (error.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setContentText(error);
            alert.showAndWait();

            return false;
        }
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	//quasiment toute les classes implemente cette fonction il serait pertinent de peut etre mettre ca dans une superclass ou une interface
	@FXML
	public void backtoMainMenu(ActionEvent e) throws IOException {
		
		if(mode.contentEquals("edition")) {
			dialogStage.close();
		}
		else {
			//chargement du xml lié
		    Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/equipe/MenuEquipe.fxml"));
		    //creation d'une nouvelle scene basée sur le fxml 
	        Scene scene = new Scene(parent);
	        //Recuperation de la fenetre principale pour creer une nouvelle scene
			Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
			primaryStage.setScene(scene);
		}
	}

	

}
