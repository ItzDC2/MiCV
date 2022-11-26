package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import app.CurriculumApp;
import controllers.dialogs.ExperienciaDialog;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Experiencia;

public class ExperienciaTabController implements Initializable {

	// model
//	private ObjectProperty<CV> model = new SimpleObjectProperty<>(new CV());	
	private ListProperty<Experiencia> experiencias = new SimpleListProperty<>();
	private ObjectProperty<Experiencia> experienciaSeleccionada = new SimpleObjectProperty<>();

	// view
	@FXML
	private BorderPane view;

	@FXML
	private TableColumn<Experiencia, String> denominacionColumn;

	@FXML
	private TableColumn<Experiencia, String> empleadorColumn;

	@FXML
	private TableColumn<Experiencia, LocalDate> desdeColumn;

	@FXML
	private TableColumn<Experiencia, LocalDate> hastaColumn;

	@FXML
	private TableView<Experiencia> experienciaTableView;

	@FXML
	private Button removeButton;

	@FXML
	private Button addButton;

	public ExperienciaTabController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
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
		experienciaTableView.itemsProperty().bind(experienciasProperty());
		experienciaSeleccionada.bind(experienciaTableView.getSelectionModel().selectedItemProperty());
		removeButton.disableProperty().bind(experienciaSeleccionada.isNull());

		// init table
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		empleadorColumn.setCellValueFactory(v -> v.getValue().empleadorProperty());
		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());

	}

	@FXML
	private void onAddAction(ActionEvent e) {
		ExperienciaDialog dialog = new ExperienciaDialog();
		dialog.initOwner(CurriculumApp.primaryStage);
		Optional<Experiencia> res = dialog.showAndWait();
		if (res.isPresent() && res != null)
			experienciasProperty().add(res.get());
	}

	@FXML
	private void onRemoveAction(ActionEvent e) {
		experienciasProperty().remove(experienciasProperty().indexOf(experienciaSeleccionada.get()));
	}

	public BorderPane getView() {
		return view;
	}

	public ListProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}

	public ObservableList<Experiencia> getExperiencias() {
		return this.experienciasProperty().get();
	}

	public void setExperiencias(final ObservableList<Experiencia> experiencias) {
		this.experienciasProperty().set(experiencias);
	}

}
