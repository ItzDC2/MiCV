package controllers.dialogs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Idioma;
import model.Nivel;

public class IdiomaDialog extends Dialog<Idioma> implements  Initializable  {

	//model
	private StringProperty denominacion = new SimpleStringProperty();
	private StringProperty certificacion = new SimpleStringProperty();
	private ObjectProperty<Nivel> nivelSeleccionado = new SimpleObjectProperty<>();
	
	//view
    @FXML
    private TextField certificacionText;

    @FXML
    private TextField denominacionText;

    @FXML
    private ComboBox<Nivel> nivelesCombo;

    @FXML
    private Button resetComboButton;

    @FXML
    private GridPane view;
	
	public IdiomaDialog() {
		super();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nuevoDialogs/NuevoIdioma.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//init dialog
		setTitle("Nuevo idioma");
		getDialogPane().setContent(view);
		
		//init combo
		nivelesCombo.getItems().setAll(Nivel.values());
		
		//buttontype
		ButtonType addButtonType = new ButtonType("Crear", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
		
		//getting button
		Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
		
		//result converter
		setResultConverter(this::onResultConverter);
		
		//bindings
		denominacion.bind(denominacionText.textProperty());
		certificacion.bind(certificacionText.textProperty());
		nivelSeleccionado.bind(nivelesCombo.getSelectionModel().selectedItemProperty());
		addButton.disableProperty().bind(denominacion.isEmpty().or(nivelSeleccionado.isNull().or(certificacion.isEmpty())));
	
		//actions
		resetComboButton.setOnAction(this::onResetAction);
		
	}

	private void onResetAction(ActionEvent e) {
		nivelesCombo.setItems(null);
	}
	
	private Idioma onResultConverter(ButtonType b) {
		Idioma i = null;
		if(b.getButtonData() == ButtonData.OK_DONE) {
			i = new Idioma();
			i.setDenominacion(denominacion.get());
			i.setNivel(nivelSeleccionado.get());
			i.setCertificacion(certificacion.get());
		}
		return i;
	}
	
}
