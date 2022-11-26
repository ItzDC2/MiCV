package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import app.CurriculumApp;
import controllers.dialogs.ConocimientoDialog;
import controllers.dialogs.IdiomaDialog;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import model.Conocimiento;
import model.Idioma;
import model.Nivel;

public class ConocimientosTabController implements Initializable {

	// model
	private ListProperty<Conocimiento> habilidades = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Conocimiento> conocimientoSeleccionado = new SimpleObjectProperty<>();

	// view
	@FXML
	private BorderPane view;

	@FXML
	private TableView<Conocimiento> conocimientosTableView;

	@FXML
	private TableColumn<Conocimiento, String> denominacionColumn;

	@FXML
	private TableColumn<Conocimiento, Nivel> nivelColumn;

	@FXML
	private Button addConocimientoButton;

	@FXML
	private Button addIdiomaButton;

	@FXML
	private Button eliminarButton;

	public ConocimientosTabController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// init table
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		nivelColumn.setCellValueFactory(v -> v.getValue().nivelProperty());

		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Nivel.values()));
		
		// bindingspens
		conocimientosTableView.itemsProperty().bind(habilidadesProperty());
		conocimientoSeleccionado.bind(conocimientosTableView.getSelectionModel().selectedItemProperty());
		eliminarButton.disableProperty().bind(conocimientoSeleccionado.isNull());

	}
	
	@FXML	
	private void onAddConocimientoAction(ActionEvent e) {
		ConocimientoDialog dialog = new ConocimientoDialog();
		dialog.initOwner(CurriculumApp.primaryStage);
		Optional<Conocimiento> res = dialog.showAndWait();
		if (res.isPresent() && res != null)
			habilidadesProperty().get().add(res.get());
	}
	
	@FXML
	private void onAddIdiomaAction(ActionEvent e) {
		IdiomaDialog dialog = new IdiomaDialog();
		dialog.initOwner(CurriculumApp.primaryStage);
		Optional<Idioma> res = dialog.showAndWait();
		if (res.isPresent() && res != null)
			habilidadesProperty().add(res.get());
	}
	
	@FXML
	private void onEliminarAction(ActionEvent e) {
		habilidadesProperty().remove(habilidadesProperty().indexOf(conocimientoSeleccionado.get()));
	}

	public BorderPane getView() {
		return view;
	}

	public ListProperty<Conocimiento> habilidadesProperty() {
		return this.habilidades;
	}

	public ObservableList<Conocimiento> getHabilidades() {
		return this.habilidadesProperty().get();
	}

	public void setHabilidades(final ObservableList<Conocimiento> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}

}
