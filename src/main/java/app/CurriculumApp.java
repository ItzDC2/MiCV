package app;

import java.util.Optional;

import controllers.MainViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CurriculumApp extends Application {

	public static Stage primaryStage;
	private MainViewController controller = new MainViewController();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		CurriculumApp.primaryStage = primaryStage;

		primaryStage.setTitle("Mi currículum");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.setOnCloseRequest(this::onCloseAction);
		primaryStage.show();
		
	}

	private void onCloseAction(WindowEvent e) {
		e.consume();
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.initOwner(CurriculumApp.primaryStage);
		a.setTitle("Salir");
		a.setHeaderText("¿Seguro que quieres salir?");
		Optional<ButtonType> res = a.showAndWait();
		if(res.get() == ButtonType.OK)
			CurriculumApp.primaryStage.close(); 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
