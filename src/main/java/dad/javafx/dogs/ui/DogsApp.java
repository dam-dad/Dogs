package dad.javafx.dogs.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DogsApp extends Application {

	private static Stage primaryStage;
	
	private DogsController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		DogsApp.primaryStage = primaryStage;
		
		controller = new DogsController();
		
		Scene scene = new Scene(controller.getView());
		
		primaryStage.setTitle("Dogs");
		primaryStage.getIcons().add(new Image("/images/Dog-icon-16px.png"));
		primaryStage.getIcons().add(new Image("/images/Dog-icon-32px.png"));
		primaryStage.getIcons().add(new Image("/images/Dog-icon-64px.png"));
		primaryStage.getIcons().add(new Image("/images/Dog-icon-128px.png"));
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void error(String message, Throwable e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(primaryStage);
		alert.setTitle("Error");
		alert.setHeaderText(message);
		alert.setContentText(e.getMessage());
		alert.showAndWait();
	}

}
