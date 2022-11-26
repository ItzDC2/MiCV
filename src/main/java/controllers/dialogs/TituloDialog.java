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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Titulo;

public class TituloDialog extends Dialog<Titulo> implements Initializable {

	//model
	private StringProperty denominacion = new SimpleStringProperty();
	private StringProperty organizador = new SimpleStringProperty();
	private ObjectProperty<LocalDate> desde = new SimpleObjectProperty<>();
	private ObjectProperty<LocalDate> hasta = new SimpleObjectProperty<>();
	
	//view
    @FXML
    private Button cancelarButton;

    @FXML
    private Button crearButton;

    @FXML
    private TextField denominacionText;

    @FXML
    private DatePicker desdePicker;

    @FXML
    private DatePicker hastaPicker;

    @FXML
    private TextField organizadorText;

    @FXML
    private GridPane view;
	
	public TituloDialog() {
		super();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nuevoDialogs/NuevoTitulo.fxml"));
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
		setTitle("Nuevo título");
		getDialogPane().setContent(view);
		getDialogPane().setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/formacion.png"))));
		
		//init buttons
		ButtonType addButtonType = new ButtonType("Crear", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
		
		//getting button
		Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
		addButton.disableProperty().bind(denominacion.isEmpty().or(organizador.isEmpty().or(desde.isNull().or(hasta.isNull()))));
		
		//bindings
		denominacion.bind(denominacionText.textProperty());
		organizador.bind(organizadorText.textProperty());
		desde.bind(desdePicker.valueProperty());
		hasta.bind(hastaPicker.valueProperty());
	
		//onConvertResult
		setResultConverter(this::onConvertResult);
	}
	
	private Titulo onConvertResult(ButtonType b) {
		Titulo t = null;
		if(b.getButtonData() == ButtonData.OK_DONE) {
			t = new Titulo();
			t.setDenominacion(denominacion.get());
			t.setOrganizador(organizador.get());
			t.setDesde(desde.getValue());
			t.setHasta(hasta.getValue());
		}
		return t;
	}
	
	public GridPane getView() {
		return view;
	}

}
