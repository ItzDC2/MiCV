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
import model.Web;

public class WebDialog extends Dialog<Web> implements Initializable {
	
	//model
	private StringProperty web = new SimpleStringProperty();	
	
	//view
	@FXML
	private TextField webText;
	
	@FXML
	private GridPane view;
	
	public WebDialog() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nuevoDialogs/NuevaWeb.fxml"));
			loader.setController(this);
			loader.load();
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//init dialog
		setTitle("Nueva web:");
		setHeaderText("Introduzca la nueva url:");
		getDialogPane().setContent(view);
		getDialogPane().setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/web.png"))));
		
		//init buttons
		ButtonType addButtonType = new ButtonType("Añadir", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
		
		//getting button
		Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
		addButton.disableProperty().bind(web.isEmpty());
		
		//result converter
		setResultConverter(this::onConvertResult);
		
		//bindings
		web.bind(webText.textProperty());
		
	}	
	
	private Web onConvertResult(ButtonType b) {
		Web w = null;
		if(b.getButtonData() == ButtonData.OK_DONE) {
			w = new Web();
			w.setUrl(web.get());
		}
		return w;
	}
	

}
