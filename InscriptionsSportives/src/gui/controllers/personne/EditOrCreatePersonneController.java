package gui.controllers.personne;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import inscriptions.Personne;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditOrCreatePersonneController implements Initializable {

	@FXML
	TextField nomPersonne; 
	@FXML
	TextField prenomPersonne;
	@FXML
	TextField mailPersonne; 
	@FXML
	TextField sucess;
	@FXML
	Button BtnValider;
	
	private Stage dialogStage;
	private Personne personne;
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
    public void loadPersonne(Personne personne) {
    	  this.personne = personne;
          nomPersonne.setText(personne.getNom());
          prenomPersonne.setText(personne.getPrenom());
          mailPersonne.setText(personne.getMail());
    }
    
    
    
    @FXML
    private void handleCreateOrEditPersonne() throws IOException {
        if(isInputValid()) {
        	if(mode.equals("creation")) {
		    	Inscriptions.getInscriptions().createPersonne(nomPersonne.getText(),prenomPersonne.getText(),mailPersonne.getText());
        	}
        	else
        	{
        		personne.setNom(nomPersonne.getText());
        		personne.setPrenom(prenomPersonne.getText());
        		personne.setMail(mailPersonne.getText());
        	
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
        String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mailPersonne.getText());

        if(nomPersonne.getText().isBlank() || prenomPersonne.getText().isBlank() || mailPersonne.getText().isBlank()) {
        	error="veuillez remplir tout les champs";
        }
           
        else if(!matcher.matches())
        	error ="Email non valide";
        
       
        
        if (error.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
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
		    Parent parent = FXMLLoader.load(getClass().getResource("../../fxml/Personne/MenuPersonne.fxml"));
		    //creation d'une nouvelle scene basée sur le fxml 
	        Scene scene = new Scene(parent);
	        //Recuperation de la fenetre principale pour creer une nouvelle scene
			Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow() ; 
			primaryStage.setScene(scene);
		}
	}

	

}
