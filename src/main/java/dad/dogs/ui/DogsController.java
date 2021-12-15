package dad.dogs.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dad.dogs.api.DogsService;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class DogsController implements Initializable {
	
	// logic
	
	private DogsService dogsService = new DogsService();
	
	// model
	
	private ListProperty<String> breeds = new SimpleListProperty<>(FXCollections.observableArrayList());
	private StringProperty selectedBreed = new SimpleStringProperty();
	private ObjectProperty<Image> image = new SimpleObjectProperty<>();

	// view

    @FXML
    private VBox view;

    @FXML
    private ComboBox<String> breedsCombo;

    @FXML
    private Button reloadButton;

    @FXML
    private ImageView dogImage;

    public DogsController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DogsView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindings
		
		breedsCombo.itemsProperty().bind(breeds);
		selectedBreed.bind(breedsCombo.getSelectionModel().selectedItemProperty());
		dogImage.imageProperty().bind(image);
		reloadButton.disableProperty().bind(selectedBreed.isNull());
		
		// listeners
		
		selectedBreed.addListener(this::onSelectedBreedChanged);
		
		// load breeds in combo
		
		loadBreeds();

	}
	
	private void onSelectedBreedChanged(ObservableValue<? extends String> o, String ov, String nv) {
		loadBreedImage(nv);
	}

	public VBox getView() {
		return view;
	}

    @FXML
    void onReloadAction(ActionEvent event) {
    	loadBreedImage(selectedBreed.get());
    }

	private void loadBreedImage(String breed) {
		Task<Image> task = new Task<Image>() {
			protected Image call() throws Exception {
				return new Image(dogsService.randomBreedImage(breed).toExternalForm());
			}
		};
		task.setOnSucceeded(event -> this.image.set(task.getValue()));
		task.setOnFailed(event -> event.getSource().getException().printStackTrace());
		reloadButton.disableProperty().bind(task.runningProperty());
		new Thread(task).start();
	}

	private void loadBreeds() {
		Task<List<String>> breedsTask = new Task<List<String>>() {
			protected List<String> call() throws Exception {
				return dogsService.breedsList();
			}
		};
		breedsTask.setOnSucceeded(event -> breeds.setAll(breedsTask.getValue()));
		breedsTask.setOnFailed(event -> event.getSource().getException().printStackTrace());
		new Thread(breedsTask).start();
	}
    
}
