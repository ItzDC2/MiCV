package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import app.CurriculumApp;
import controllers.dialogs.TituloDialog;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Titulo;

public class FormacionTabController implements Initializable {

	// model
	private ListProperty<Titulo> formacion = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Titulo> tituloSeleccionado = new SimpleObjectProperty<>();

	// view
	@FXML
	private BorderPane view;

	@FXML
	private Button addButton;

	@FXML
	private TableView<Titulo> formacionTableView;

	@FXML
	private TableColumn<Titulo, String> denominacionColumn;

	@FXML
	private TableColumn<Titulo, LocalDate> desdeColumn;

	@FXML
	private TableColumn<Titulo, LocalDate> hastaColumn;

	@FXML
	private TableColumn<Titulo, String> organizadorColumn;

	@FXML
	private Button removeButton;

	public FormacionTabController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
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
		formacionTableView.itemsProperty().bind(formacionProperty());
		tituloSeleccionado.bind(formacionTableView.getSelectionModel().selectedItemProperty());
		removeButton.disableProperty().bind(tituloSeleccionado.isNull());

		// init table columns
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		organizadorColumn.setCellValueFactory(v -> v.getValue().organizadorProperty());
		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());

		// TODO: Pasarlo a FXML
		addButton.setOnAction(this::onAgregarAction);
		removeButton.setOnAction(this::onRemoveAction);
	}

	@FXML
	private void onAgregarAction(ActionEvent e) {
		TituloDialog dialog = new TituloDialog();
		dialog.initOwner(CurriculumApp.primaryStage);
		Optional<Titulo> res = dialog.showAndWait();
		if (res.isPresent() && res != null)
			formacionProperty().add(res.get());
	}

	@FXML
	private void onRemoveAction(ActionEvent e) {
		formacionProperty().remove(formacionProperty().indexOf(tituloSeleccionado.get()));
	}

	public BorderPane getView() {
		return view;
	}

	public ListProperty<Titulo> formacionProperty() {
		return this.formacion;
	}

	public ObservableList<Titulo> getFormacion() {
		return this.formacionProperty().get();
	}

	public void setFormacion(final ObservableList<Titulo> formacion) {
		this.formacionProperty().set(formacion);
	}

}
