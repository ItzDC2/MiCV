package controllers.dialogs;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Telefono;
import model.TipoTelefono;

public class TelefonoDialog extends Dialog<Telefono> implements Initializable {
	
	//model
	private StringProperty numero = new SimpleStringProperty();
	private ObjectProperty<TipoTelefono> tipo = new SimpleObjectProperty<>();
	
	//view
	@FXML
	private TextField numeroText;
	
	@FXML
	private ComboBox<TipoTelefono> tipoCombo;
	
	@FXML
	private GridPane view;
	
	public TelefonoDialog() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nuevoDialogs/nuevoTelefono.fxml"));
			loader.setController(this);
			loader.load();
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//init dialog
		setTitle("Nuevo teléfono");
		setHeaderText("Introduzca el nuevo número de teléfono");
		getDialogPane().setContent(view);

		//init buttons
		ButtonType addButtonType = new ButtonType("Añadir", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
		
		//Getting button
		Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
		addButton.disableProperty().bind(numero.isEmpty().or(tipo.isNull()));
		
		// result converter
		setResultConverter(this::onConvertResult);
		
		//load combo
		tipoCombo.getItems().setAll(TipoTelefono.values());		
		
		//bindings
		numero.bind(numeroText.textProperty());
		tipo.bind(tipoCombo.getSelectionModel().selectedItemProperty());
		
		//actions
		tipoCombo.getSelectionModel().select(0);
		getDialogPane().setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/telefono.png"))));
		
	}
	
	private Telefono onConvertResult(ButtonType b) {
		Telefono t = null;
		if(b.getButtonData() == ButtonData.OK_DONE) {
			t = new Telefono();
			t.setNumero(numero.get());
			t.setTipo(tipo.get());
		}
		return t;
	}
	
}
