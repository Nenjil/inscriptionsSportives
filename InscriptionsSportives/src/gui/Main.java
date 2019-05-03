package gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	
    public void start(Stage primaryStage) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setTitle("Inscriptions Sportives");
        primaryStage.setScene(scene);
     //   scene.getStylesheets().add(getClass().getResource("main.css").toString());
        primaryStage.show();
    }

	
	public static void main(String[] args) {
		launch(args);
		
	}
}