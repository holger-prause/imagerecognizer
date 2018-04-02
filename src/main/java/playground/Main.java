package playground;

import java.io.IOException;
import java.io.InputStream;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {

		try {			
			Parent root = FXMLLoader.load(getClass().getResource("/UI.fxml"));
	        Scene scene = new Scene(root);
		    
	        stage.setTitle("Face Recognizer");
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}