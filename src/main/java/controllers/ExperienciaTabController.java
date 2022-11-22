package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Experiencia;

public class ExperienciaTabController implements Initializable {

	private Experiencia model = new Experiencia();	
	
	@FXML
	private BorderPane view;
	
    @FXML
    private TableColumn<?, ?> denominacionColumn;

    @FXML
    private TableColumn<?, ?> desdeColumn;

    @FXML
    private TableColumn<?, ?> empleadorColumn;

    @FXML
    private TableView<?> formacionTableView;

    @FXML
    private TableColumn<?, ?> hastaColumn;

    @FXML
    private Button removeButton;

	public ExperienciaTabController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
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

	public Experiencia getModel() {
		return model;
	}
	
}
