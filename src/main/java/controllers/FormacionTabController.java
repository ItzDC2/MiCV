package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.CV;

public class FormacionTabController implements Initializable {

	private CV model = new CV();	
	
	@FXML
	private BorderPane view;
	
	@FXML
    private Button addButton;

	@FXML
	private TableView<CV> formacionTableView;

	@FXML
    private TableColumn<CV, String> denominacionColumn;

    @FXML
    private TableColumn<CV, LocalDate> desdeColumn;

    @FXML
    private TableColumn<CV, LocalDate> hastaColumn;

    @FXML
    private TableColumn<CV, String> organizadorColumn;

    @FXML
    private Button removeButton;
	
	public FormacionTabController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public BorderPane getView() {
		return view;
	}
	
	public CV getModel() {
		return model;
	}
	
}
