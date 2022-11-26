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
import model.Conocimiento;
import model.Nivel;

public class ConocimientoDialog extends Dialog<Conocimiento> implements Initializable {

	//model
	private StringProperty denominacion = new SimpleStringProperty();
	private ObjectProperty<Nivel> nivelSeleccionado = new SimpleObjectProperty<>();
	
	//view
    @FXML
    private TextField denominacionText;

    @FXML
    private ComboBox<Nivel> nivelCombo;

    @FXML
    private Button resetComboButton;

    @FXML
    private GridPane view;
	
	public ConocimientoDialog() {
		super();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nuevoDialogs/NuevoConocimiento.fxml"));
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
		setTitle("Nuevo conocimiento");
		getDialogPane().setContent(view);
		
		//init combo
		nivelCombo.getItems().setAll(Nivel.values());
		
		//buttons
		ButtonType addButtonType = new ButtonType("Crear", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
	
		//getting button
		Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
		
		//result convert
		setResultConverter(this::onResultConvert);
		
		//bindings
		denominacion.bind(denominacionText.textProperty());
		nivelSeleccionado.bind(nivelCombo.getSelectionModel().selectedItemProperty());
		addButton.disableProperty().bind(denominacion.isEmpty().or(nivelSeleccionado.isNull()));
		
		//actions
		resetComboButton.setOnAction(this::onResetAction);
		
	}
	
	private void onResetAction(ActionEvent e) {
		nivelCombo.setItems(null);
	}
	
	private Conocimiento onResultConvert(ButtonType b) {
		Conocimiento c = null;
		if(b.getButtonData() == ButtonData.OK_DONE) {
			c = new Conocimiento();
			c.setDenominacion(denominacion.get());
			c.setNivel(nivelSeleccionado.get());
		}
		return c;
	}

}
