package controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import app.CurriculumApp;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Nacionalidad;
import model.Personal;

public class PersonalTabController implements Initializable {

	// model
	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>(new Personal());
	private ObjectProperty<Nacionalidad> nacionalidadSeleccionada = new SimpleObjectProperty<>();
	private List<Nacionalidad> nacionalidades = new ArrayList<>();

	// view
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
	private TextArea inputDireccion;

	@FXML
	private TextField inputCodigoPostal;

	@FXML
	private TextField inputLocalidad;

	@FXML
	private ComboBox<String> paisesCombo;

	@FXML
	private ListView<Nacionalidad> nacionalidadesView;

	@FXML
	private Button addNacionalidadButton;

	@FXML
	private Button removeNacionalidadButton;

	public PersonalTabController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindings
		nacionalidadSeleccionada.bind(nacionalidadesView.getSelectionModel().selectedItemProperty());
		removeNacionalidadButton.disableProperty().bind(nacionalidadSeleccionada.isNull());

		// listener
		personal.addListener(this::onChangeModel);

		// actions
		try {
			loadData();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

	}

	private void onChangeModel(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {

		if (ov != null) {
			inputDNI.textProperty().unbindBidirectional(ov.identificacionProperty());
			inputNombre.textProperty().unbindBidirectional(ov.nombreProperty());
			inputApellidos.textProperty().unbindBidirectional(ov.apellidoProperty());
			inputFechaNacimiento.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
			inputDireccion.textProperty().unbindBidirectional(ov.direccionProperty());
			inputCodigoPostal.textProperty().unbindBidirectional(ov.codigoPostalProperty());
			inputLocalidad.textProperty().unbindBidirectional(ov.localidadProperty());
			nacionalidadesView.itemsProperty().unbind();
		}

		if (nv != null) {
			inputDNI.textProperty().bindBidirectional(personal.get().identificacionProperty());
			inputNombre.textProperty().bindBidirectional(personal.get().nombreProperty());
			inputApellidos.textProperty().bindBidirectional(personal.get().apellidoProperty());
			inputFechaNacimiento.valueProperty().bindBidirectional(personal.get().fechaNacimientoProperty());
			inputDireccion.textProperty().bindBidirectional(personal.get().direccionProperty());
			inputCodigoPostal.textProperty().bindBidirectional(personal.get().codigoPostalProperty());
			inputLocalidad.textProperty().bindBidirectional(personal.get().localidadProperty());
			paisesCombo.getSelectionModel().select(personal.get().getPais());
			personal.get().paisProperty().bind(paisesCombo.getSelectionModel().selectedItemProperty());
			nacionalidadesView.itemsProperty().bind(personal.get().nacionalidadesProperty());
		}
	}

	private void loadData() throws IOException, URISyntaxException {
		List<String> nacionalidades = Files
				.readAllLines(Paths.get(getClass().getResource("/csv/nacionalidades.csv").toURI()));
		List<String> paises = Files.readAllLines(Paths.get(getClass().getResource("/csv/paises.csv").toURI()));

		nacionalidades.stream().map(i -> new Nacionalidad(i)).forEach(this.nacionalidades::add);
		paisesCombo.getItems().addAll(paises);
	}

	@FXML
	private void onAddNacionalidadAction(ActionEvent e) {
		ChoiceDialog<Nacionalidad> dialog = new ChoiceDialog<>(this.nacionalidades.get(0), this.nacionalidades);
		dialog.initOwner(CurriculumApp.primaryStage);
		dialog.setTitle("Nueva nacionalidad");
		dialog.setHeaderText("Añadir nacionalidad");
		dialog.setContentText("Seleccione una nacionalidad");

		Optional<Nacionalidad> res = dialog.showAndWait();
		if (res.isPresent())
			personal.get().nacionalidadesProperty().add(res.get());
	}

	@FXML
	private void onRemoveNacionalidadAction(ActionEvent e) {
		personal.get().nacionalidadesProperty()
				.remove(personal.get().nacionalidadesProperty().indexOf(nacionalidadSeleccionada.get()));
	}

	public BorderPane getView() {
		return view;
	}

	public ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public Personal getPersonal() {
		return this.personalProperty().get();
	}

	public void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

}