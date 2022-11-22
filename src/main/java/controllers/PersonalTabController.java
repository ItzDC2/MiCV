package controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import app.CurriculumApp;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Nacionalidad;
import model.Personal;

public class PersonalTabController implements Initializable {

	//model
	private Personal model = new Personal();
	private ListProperty<String> nacionalidadesListProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
	private StringProperty nacionalidadSeleccionadaProperty = new SimpleStringProperty();
	
	//view
	@FXML
	private BorderPane view;	
	
	@FXML
	private TextField inputDNI;
	
	@FXML
	private TextField inputNombre;
	
	@FXML
	private TextField inputApellidos;

	@FXML
	private DatePicker inputFechaNacimiento;
	
	@FXML
	private TextField inputCodigoPostal;
	
	@FXML
	private TextField inputLocalidad;
	
	@FXML
	private ComboBox<String> paisesCombo;
	
	@FXML
	private ListView<String> nacionalidadesView;
	
	@FXML
	private Button addNacionalidadButton;
	
	@FXML
	private Button removeNacionalidadButton;
	
	public PersonalTabController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//bindings
		paisesCombo.itemsProperty().bind(model.paisesProperty());
		nacionalidadesView.itemsProperty().bind(nacionalidadesListProperty);
		removeNacionalidadButton.disableProperty().bind(nacionalidadesView.getSelectionModel().selectedItemProperty().isNull());
		nacionalidadSeleccionadaProperty.bind(nacionalidadesView.getSelectionModel().selectedItemProperty());
		
		nacionalidadSeleccionadaProperty.addListener((v, ov, nv) -> {
			System.out.println(ov + " -> " + nv);
			System.out.println(nacionalidadSeleccionadaProperty.get());
			System.out.println(nacionalidadesListProperty.indexOf("americano"));
			System.out.println(nacionalidadesListProperty);
		});
		
		//actions
		try {
			loadData();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		
		addNacionalidadButton.setOnAction(this::onAddNacionalidadAction);
		removeNacionalidadButton.setOnAction(this::onRemoveNacionalidadAction);
	}
	
	private void loadData() throws IOException, URISyntaxException {
		List<String> nacionalidades = Files.readAllLines(Paths.get(getClass().getResource("/csv/nacionalidades.csv").toURI()));
		List<String> paises = Files.readAllLines(Paths.get(getClass().getResource("/csv/paises.csv").toURI()));
		
		nacionalidades.stream().map(i -> new Nacionalidad(new SimpleStringProperty(i))).forEach(model.nacionalidadesProperty()::add); 
		paises.stream().forEach(model.paisesProperty()::add);
	
	}
	
	private void onAddNacionalidadAction(ActionEvent e) {
		ChoiceDialog<Nacionalidad> dialog = new ChoiceDialog<>(model.getNacionalidades().get(0), model.nacionalidadesProperty());
		dialog.initOwner(CurriculumApp.primaryStage);
		dialog.setTitle("Nueva nacionalidad");
		dialog.setHeaderText("Añadir nacionalidad");
		dialog.setContentText("Seleccione una nacionalidad");
		
		Optional<Nacionalidad> res = dialog.showAndWait();
		if(res.isPresent())
			nacionalidadesListProperty.add(res.get().getDenominacion());
		else 
			dialog.close();
	}
	
	private void onRemoveNacionalidadAction(ActionEvent e) {
		System.out.println(nacionalidadSeleccionadaProperty);
		System.out.println(nacionalidadesListProperty.indexOf(nacionalidadSeleccionadaProperty));
//		nacionalidadesListProperty.remove(nacionalidadesListProperty.indexOf(nacionalidadSeleccionada));
	}
	
	public BorderPane getView() {
		return view;
	}
	
	public Personal getModel() {
		return model;
	}
	
}
