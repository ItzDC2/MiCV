package app;

import controllers.MainViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CurriculumApp extends Application {

	public static Stage primaryStage;
	private MainViewController controller = new MainViewController();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		CurriculumApp.primaryStage = primaryStage;

		primaryStage.setTitle("Mi currículum");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
