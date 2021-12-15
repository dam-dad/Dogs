package dad.dogs.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DogsApplication extends Application {

	private DogsController dogsController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		dogsController = new DogsController();
		
		Scene scene = new Scene(dogsController.getView());
		
		primaryStage.setTitle("Dogs");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
