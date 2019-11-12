package dad.javafx.dogs.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.dogs.client.DogsService;
import dad.javafx.dogs.client.DogsServiceException;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
	
	// business logic
	
	private DogsService service = new DogsService();
	
	// model
	
	private StringProperty selectedBreed = new SimpleStringProperty();
	private ListProperty<String> breeds = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private ObjectProperty<Image> dogImage = new SimpleObjectProperty<Image>();
	
	// view
	
    @FXML
    private VBox view;

    @FXML
    private ComboBox<String> breedsCombo;

    @FXML
    private Button reloadButton;

    @FXML
    private ImageView dogImageView;

	public DogsController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DogsView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		selectedBreed.bind(breedsCombo.getSelectionModel().selectedItemProperty());
		
		breedsCombo.itemsProperty().bind(breeds);
		
		selectedBreed.addListener((o, ov, nv) -> onSelectedBreedChanged(o, ov, nv));
		
		dogImageView.imageProperty().bind(dogImage);
		
    	try {
			breeds.setAll(service.listBreeds());
		} catch (DogsServiceException e1) {
			DogsApp.error("No se pudo cargar la lista de razas de perro", e1);
		}
    	
	}

	public VBox getView() {
		return view;
	}
	
	private void onSelectedBreedChanged(ObservableValue<? extends String> o, String ov, String nv) {
		loadBreedImage(nv); 
	}
	
    @FXML
    private void onReloadAction(ActionEvent e) {
		loadBreedImage(selectedBreed.get()); 
    }


	private void loadBreedImage(String breed) {
		try {
			URL url = service.randomImageByBreed(breed);
			dogImage.set(new Image(url.toString()));
		} catch (DogsServiceException e) {
			DogsApp.error("No se pudo obtener imagen aleatoria de la raza " + breed, e);
		}
	}

}
