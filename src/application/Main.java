package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

	//guardar referência nesse atributo mainScene
	private static Scene mainScene;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			//instaciar o objeto correto 
			ScrollPane scrollPane = loader.load();
			
			//fazer o scrollPane se ajustar a janela
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);

			mainScene = new Scene(scrollPane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sample JavaFX application");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//método aqui para pegar referência Scene
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
	
		launch(args);
	}
}
