package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import app.CurriculumApp;
import controllers.dialogs.EmailDialog;
import controllers.dialogs.TelefonoDialog;
import controllers.dialogs.WebDialog;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Contacto;
import model.Email;
import model.Telefono;
import model.TipoTelefono;
import model.Web;

public class ContactoTabController implements Initializable {

	//model
	private Contacto model = new Contacto();

	//view
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
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//init table columns
		telefonoTableView.itemsProperty().bind(model.telefonosProperty());
		numTelefonoColumn.setCellValueFactory(v -> v.getValue().numeroProperty());
		tipoTelefonoColumn.setCellValueFactory(v -> v.getValue().tipoProperty());
		
		emailTableView.itemsProperty().bind(model.emailsProperty());
		emailColumn.setCellValueFactory(v -> v.getValue().direccionProperty());

		websTableView.itemsProperty().bind(model.websProperty());
		urlColumn.setCellValueFactory(v -> v.getValue().urlProperty());
		
		//bindings
		
		//actions
		addTelefonoButton.setOnAction(this::onAddTelefonoAction);
		addEmailButton.setOnAction(this::onAddEmailAction);
		addWebButton.setOnAction(this::onAddWebAction);
	}

	private void onAddTelefonoAction(ActionEvent e) {
		TelefonoDialog dialog = new TelefonoDialog();
		dialog.initOwner(CurriculumApp.primaryStage);
		Optional<Telefono> res = dialog.showAndWait();
		if(res.isPresent() && res != null) 
			model.telefonosProperty().add(res.get());
	}	
	
	private void onAddEmailAction(ActionEvent e) {
		EmailDialog dialog = new EmailDialog();
		dialog.initOwner(CurriculumApp.primaryStage);
		Optional<Email> res = dialog.showAndWait();
		if(res.isPresent() && res != null) 
			model.emailsProperty().add(res.get());
	}
	
	private void onAddWebAction(ActionEvent e) {
		WebDialog dialog = new WebDialog();
		dialog.initOwner(CurriculumApp.primaryStage);
		Optional<Web> res = dialog.showAndWait();
		if(res.isPresent() && res != null)
			model.websProperty().add(res.get());
		
	}
	
	public SplitPane getView() {
		return view;
	}
	
	public Contacto getModel() {
		return model;
	}
	
}
