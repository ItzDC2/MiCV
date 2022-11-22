package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class MainViewController implements Initializable {
	
	//controllers
	private ConocimientosTabController conocimientosTabController = new ConocimientosTabController();
	private ContactoTabController contactoTabController = new ContactoTabController();
	private ExperienciaTabController experienciaTabController = new ExperienciaTabController();
	private FormacionTabController formacionTabController = new FormacionTabController();
	private PersonalTabController personalTabController = new PersonalTabController();
	
	//view
	@FXML
	private BorderPane view;
	
	@FXML
	private Tab personalTab;
	
	@FXML
	private Tab contactoTab;
	
	@FXML
	private Tab formacionTab;
	
	@FXML
	private Tab experienciaTab;
	
	@FXML
	private Tab conocimientosTab;
	
	public MainViewController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		try {
			loader.load();	
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//bindings
		
		
		//actions
		personalTab.setContent(personalTabController.getView());
		contactoTab.setContent(contactoTabController.getView());
		formacionTab.setContent(formacionTabController.getView());
		experienciaTab.setContent(experienciaTabController.getView());
		conocimientosTab.setContent(conocimientosTabController.getView());
		
	}
	
	public BorderPane getView() {
		return view;
	}
	
}
