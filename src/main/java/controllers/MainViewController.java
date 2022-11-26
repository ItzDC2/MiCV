package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

import adapters.GsonAdapter;
import app.CurriculumApp;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.CV;

public class MainViewController implements Initializable {
	
	private static Gson gson = FxGson.fullBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new GsonAdapter()).create();
	
	private File f;
	private boolean guardado = false;
	private boolean guardando = false;
	
	//model
	private ObjectProperty<CV> cv = new SimpleObjectProperty<>();
	
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
	
	@FXML
	private MenuItem nuevo;
	
	@FXML
	private MenuItem abrir;

	@FXML
	private MenuItem guardar;
	
	@FXML
	private MenuItem guardarComo;

	@FXML
	private MenuItem salir;
	
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
		
		//listener
		cv.addListener(this::onChangeCV);

		//actions
		cv.set(new CV());
		personalTab.setContent(personalTabController.getView());
		contactoTab.setContent(contactoTabController.getView());
		formacionTab.setContent(formacionTabController.getView());
		experienciaTab.setContent(experienciaTabController.getView());
		conocimientosTab.setContent(conocimientosTabController.getView());
		
	}
	
	@FXML
	private void onCloseAction(ActionEvent e) {
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.initOwner(CurriculumApp.primaryStage);
		a.setTitle("Salir");
		a.setHeaderText("¿Seguro que quieres salir?");
		Optional<ButtonType> res = a.showAndWait();
		if(res.get() == ButtonType.OK)
			CurriculumApp.primaryStage.close();
	}
	
	private void onChangeCV(ObservableValue<? extends CV> o, CV ov, CV nv) {
		if(ov != null) {
			personalTabController.personalProperty().unbind();
			contactoTabController.contactoProperty().unbind();
			formacionTabController.formacionProperty().unbind();
			experienciaTabController.experienciasProperty().unbind();
			conocimientosTabController.habilidadesProperty().unbind();
		}
		if(nv != null) {
			personalTabController.personalProperty().bind(nv.personalProperty());
			contactoTabController.contactoProperty().bind(nv.contactoProperty());
			formacionTabController.formacionProperty().bind(nv.formacionProperty());
			experienciaTabController.experienciasProperty().bind(nv.experienciasProperty());
			conocimientosTabController.habilidadesProperty().bind(nv.habilidadesProperty());
		}
	}
	
	@FXML
	private void onGuardarAction(ActionEvent e) {
		if(!guardado) {
			guardando = true;
			onGuardarComoAction(e);
			if(f != null)
				guardado = true;
		} else {
			if(f != null) {
				String json = gson.toJson(cv.get(), CV.class);
				try {
					Files.writeString(f.toPath(), json, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
				} catch(IOException ex) {
					ex.printStackTrace();
				}
			} 
		}
		guardando = false;
	}
	
	@FXML
	private void onGuardarComoAction(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle((!guardando) ? "Guardar como" : "Guardar");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*"));
		f = chooser.showSaveDialog(CurriculumApp.primaryStage);
		
		if(f != null) {
			String json = gson.toJson(cv.get(), CV.class);
			try {
				Files.writeString(f.toPath(), json, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@FXML
	private void onAbrirAction(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Abrir");
		f = chooser.showOpenDialog(CurriculumApp.primaryStage);
		if(f != null) {
			try {
				String json = Files.readString(f.toPath(), StandardCharsets.UTF_8);
				CV newCV = gson.fromJson(json, CV.class);
				cv.set(newCV);
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@FXML
	private void onNuevoAction(ActionEvent e) {
		cv.set(new CV());
	}
	
	public BorderPane getView() {
		return view;
	}
	
}
