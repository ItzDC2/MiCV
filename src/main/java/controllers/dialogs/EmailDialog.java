package controllers.dialogs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Email;

public class EmailDialog extends Dialog<Email> implements Initializable {

	//model
	private StringProperty email = new SimpleStringProperty();
	
	//view
	@FXML
	private GridPane view;
	
	@FXML
	private TextField emailText;
	
	public EmailDialog() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nuevoDialogs/NuevoEmail.fxml"));
			loader.setController(this);
			loader.load();
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//init dialog
		setTitle("Nuevo email");
		setHeaderText("Introduzca el nuevo email");
		getDialogPane().setContent(view);
		getDialogPane().setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/email.png"))));
		
		//init buttons
		ButtonType addButtonType = new ButtonType("Añadir", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
		
		//getting button
		Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
		addButton.disableProperty().bind(email.isEmpty());
		
		//result converter
		setResultConverter(this::onConvertResult);
		
		//bindings
		email.bind(emailText.textProperty());
		
	}
	
	private Email onConvertResult(ButtonType b) {
		Email e = null;
		if(b.getButtonData() == ButtonData.OK_DONE) {
			e = new Email();
			e.setDireccion(email.get());
		}
		return e;
	}
	
}
