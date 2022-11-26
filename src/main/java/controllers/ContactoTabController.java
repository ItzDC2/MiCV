package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import app.CurriculumApp;
import controllers.dialogs.EmailDialog;
import controllers.dialogs.TelefonoDialog;
import controllers.dialogs.WebDialog;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Contacto;
import model.Email;
import model.Telefono;
import model.TipoTelefono;
import model.Web;

public class ContactoTabController implements Initializable {

	// model
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<>(new Contacto());
	private ObjectProperty<Telefono> telefonoSeleccionado = new SimpleObjectProperty<>();
	private ObjectProperty<Email> emailSeleccionado = new SimpleObjectProperty<>();
	private ObjectProperty<Web> webSeleccionada = new SimpleObjectProperty<>();

	// view
	@FXML
	private SplitPane view;

	@FXML
	private TableView<Telefono> telefonoTableView;

	@FXML
	private TableColumn<Telefono, String> numTelefonoColumn;

	@FXML
	private TableColumn<Telefono, TipoTelefono> tipoTelefonoColumn;

	@FXML
	private TableView<Email> emailTableView;

	@FXML
	private TableColumn<Email, String> emailColumn;

	@FXML
	private TableView<Web> websTableView;

	@FXML
	private TableColumn<Web, String> urlColumn;

	@FXML
	private Button addTelefonoButton;

	@FXML
	private Button removeTelefonoButton;

	@FXML
	private Button addEmailButton;

	@FXML
	private Button removeEmailButton;

	@FXML
	private Button addWebButton;

	@FXML
	private Button removeWebButton;

	public ContactoTabController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// init table columns
		numTelefonoColumn.setCellValueFactory(v -> v.getValue().numeroProperty());
		tipoTelefonoColumn.setCellValueFactory(v -> v.getValue().tipoProperty());
		tipoTelefonoColumn.setCellFactory(ComboBoxTableCell.forTableColumn(TipoTelefono.values()));
		numTelefonoColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		emailColumn.setCellValueFactory(v -> v.getValue().direccionProperty());
		emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		urlColumn.setCellValueFactory(v -> v.getValue().urlProperty());
		urlColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		// bindings
		telefonoSeleccionado.bind(telefonoTableView.getSelectionModel().selectedItemProperty());
		emailSeleccionado.bind(emailTableView.getSelectionModel().selectedItemProperty());
		webSeleccionada.bind(websTableView.getSelectionModel().selectedItemProperty());

		removeTelefonoButton.disableProperty().bind(telefonoSeleccionado.isNull());
		removeEmailButton.disableProperty().bind(emailSeleccionado.isNull());
		removeWebButton.disableProperty().bind(webSeleccionada.isNull());

		// listeners
		contacto.addListener(this::onChangeModel);

	}

	private void onChangeModel(ObservableValue<? extends Contacto> o, Contacto ov, Contacto nv) {
		if (ov != null) {
			telefonoTableView.itemsProperty().unbindBidirectional(ov.telefonosProperty());
			emailTableView.itemsProperty().unbindBidirectional(ov.emailsProperty());
			websTableView.itemsProperty().unbindBidirectional(ov.websProperty());
		}

		if (nv != null) {
			telefonoTableView.itemsProperty().bindBidirectional(nv.telefonosProperty());
			emailTableView.itemsProperty().bindBidirectional(nv.emailsProperty());
			websTableView.itemsProperty().bindBidirectional(nv.websProperty());
		}

	}

	@FXML
	private void onAddTelefonoAction(ActionEvent e) {
		TelefonoDialog dialog = new TelefonoDialog();
		dialog.initOwner(CurriculumApp.primaryStage);
		Optional<Telefono> res = dialog.showAndWait();
		if (res.isPresent() && res != null)
			contacto.get().telefonosProperty().add(res.get());
	}

	@FXML
	private void onAddEmailAction(ActionEvent e) {
		EmailDialog dialog = new EmailDialog();
		dialog.initOwner(CurriculumApp.primaryStage);
		Optional<Email> res = dialog.showAndWait();
		if (res.isPresent() && res != null)
			contacto.get().emailsProperty().add(res.get());
	}

	@FXML
	private void onAddWebAction(ActionEvent e) {
		WebDialog dialog = new WebDialog();
		dialog.initOwner(CurriculumApp.primaryStage);
		Optional<Web> res = dialog.showAndWait();
		if (res.isPresent() && res != null)
			contacto.get().websProperty().add(res.get());
	}

	@FXML
	private void onRemoveTelefonoAction(ActionEvent e) {
		contacto.get().telefonosProperty()
				.remove(contacto.get().telefonosProperty().indexOf(telefonoSeleccionado.get()));
	}

	@FXML	
	private void onRemoveEmailAction(ActionEvent e) {
		contacto.get().emailsProperty().remove(contacto.get().emailsProperty().indexOf(emailSeleccionado.get()));
	}

	@FXML
	private void onRemoveWebAction(ActionEvent e) {
		contacto.get().websProperty().remove(contacto.get().websProperty().indexOf(webSeleccionada.get()));
	}

	public SplitPane getView() {
		return view;
	}

	public ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}

	public Contacto getContacto() {
		return this.contactoProperty().get();
	}

	public void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}

	public ObjectProperty<Telefono> telefonoSeleccionadoProperty() {
		return this.telefonoSeleccionado;
	}

	public Telefono getTelefonoSeleccionado() {
		return this.telefonoSeleccionadoProperty().get();
	}

	public void setTelefonoSeleccionado(final Telefono telefonoSeleccionado) {
		this.telefonoSeleccionadoProperty().set(telefonoSeleccionado);
	}

	public ObjectProperty<Email> emailSeleccionadoProperty() {
		return this.emailSeleccionado;
	}

	public Email getEmailSeleccionado() {
		return this.emailSeleccionadoProperty().get();
	}

	public void setEmailSeleccionado(final Email emailSeleccionado) {
		this.emailSeleccionadoProperty().set(emailSeleccionado);
	}

	public ObjectProperty<Web> webSeleccionadaProperty() {
		return this.webSeleccionada;
	}

	public Web getWebSeleccionada() {
		return this.webSeleccionadaProperty().get();
	}

	public void setWebSeleccionada(final Web webSeleccionada) {
		this.webSeleccionadaProperty().set(webSeleccionada);
	}

}
