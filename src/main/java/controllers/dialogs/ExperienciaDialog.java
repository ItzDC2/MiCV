package controllers.dialogs;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Experiencia;

public class ExperienciaDialog extends Dialog<Experiencia> implements Initializable {

	//model
	private StringProperty denominacion = new SimpleStringProperty();
	private StringProperty empleador = new SimpleStringProperty();
	private ObjectProperty<LocalDate> desde = new SimpleObjectProperty<>();
	private ObjectProperty<LocalDate> hasta = new SimpleObjectProperty<>();
	
	@FXML
	private GridPane view;
	
	@FXML
	private TextField denominacionText;
	
	@FXML
	private DatePicker desdeDatePicker;
	
	@FXML
	private TextField empleadorText;
	
	@FXML
	private DatePicker hastaDatePicker;
	
	public ExperienciaDialog() {
		super();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nuevoDialogs/NuevaExperiencia.fxml"));
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
		setTitle("Nueva experiencia");
		getDialogPane().setContent(view);
		
		//buttons
		ButtonType addButtonType = new ButtonType("Crear", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
		
		//getting button
		Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
		addButton.disableProperty().bind(denominacion.isEmpty().or(empleador.isEmpty().or(desde.isNull().or(hasta.isNull()))));
		
		//result converter
		setResultConverter(this::onConvertResult);
		
		//bindings
		denominacion.bind(denominacionText.textProperty());
		empleador.bind(empleadorText.textProperty());
		desde.bind(desdeDatePicker.valueProperty());
		hasta.bind(hastaDatePicker.valueProperty());
		
	}

	private Experiencia onConvertResult(ButtonType b) {
		Experiencia e = null;
		if(b.getButtonData() == ButtonData.OK_DONE) {
			e = new Experiencia();
			e.setDenominacion(denominacion.get());
			e.setEmpleador(empleador.get());
			e.setDesde(desde.get());
			e.setHasta(hasta.get());
		}	
		return e;
	}
	
}
