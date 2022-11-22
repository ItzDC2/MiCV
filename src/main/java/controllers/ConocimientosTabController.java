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
import model.Conocimiento;
import model.Nivel;

public class ConocimientosTabController implements Initializable {

	private Conocimiento model = new Conocimiento();
	
	@FXML
	private BorderPane view;
    
	@FXML
    private Button addConocimientoButton;

    @FXML
    private TableView<Conocimiento> conocimientosTableView;
    
    @FXML
    private TableColumn<Conocimiento, String> denominacionColumn;

    @FXML
    private TableColumn<Conocimiento, Nivel> nivelColumn;

    @FXML
    private Button eliminarButton;

    @FXML
    private Button addIdiomaButton;
	
	public ConocimientosTabController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
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
	
	public Conocimiento getModel() {
		return model;
	}
	
}
