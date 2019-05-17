package gui.controllers.competition;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import inscriptions.Competition;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class EditOrCreateCompetController implements Initializable {

	@FXML
	TextField nomCompet; 
	@FXML
	ToggleGroup enEquipe ;
	@FXML 
	DatePicker dateCloture; 
	@FXML
	TextField sucess;
	
	private Stage dialogStage;
	private Competition compet;
	private boolean estEnEquipe;
	private RadioButton radioChoice;
    
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
     * @throws InterruptedException 
     */
    @FXML
    private void handleEditCompet() throws InterruptedException {
    	//Si les champs sont valides
        if (isInputValid("")) {
        	compet.setNom(nomCompet.getText());
        	compet.setDateCloture(dateCloture.getValue());
        	sucess.setVisible(true);
        	Thread.sleep(2500); 
            dialogStage.close();
        }
    }
    
    
    /**
     * Appelé lorsque que l'user clique valider
     * @throws IOException 
     */
    @FXML
    private void handleCreateCompet() throws IOException {
    	//recupere le choix oui ou non de en equipe
    	radioChoice= (RadioButton) enEquipe.selectedToggleProperty().getValue() ;
    	//Si les champs sont valides
        if (isInputValid("create")) {
       
        if(radioChoice.getText().equals("Oui")) 
        	estEnEquipe = true; 
        else 
        	estEnEquipe= false;
        Inscriptions.getInscriptions().createCompetition(nomCompet.getText(), dateCloture.getValue(), estEnEquipe);
        sucess.setVisible(true);
        
        }
    }
    
    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid(String editOrCreate) {
        String error = "";

        if(nomCompet.getText().isEmpty() || dateCloture.getValue() == null)
        	error="veuillez remplir tout les champs";
        else if(dateCloture.getValue().isBefore(LocalDate.now()))
        	error= "Vous ne pouvez pas créer une competition avec une date de cloture antérieure à la date actuelle" ;
        
        else if(editOrCreate.equals("create")) {
        	if(radioChoice==null)
        		error="Veuillez selectionner si la competition est en equipe ou non";
        }
        
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
	
	//quasiment toute les classes implemente cette fonction il serait pertinent de peut etre mettre ca dans une superclass ou une interface
	@FXML
	public void backtoMainMenu(ActionEvent e) throws IOException {
		//chargement du xml lié
	    Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/MenuCompet.fxml"));
	    //creation d'une nouvelle scene basée sur le fxml 
        Scene scene = new Scene(parent);
        //Recuperation de la fenetre principale pour creer une nouvelle scene
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
		primaryStage.setScene(scene);
	}

	

}
