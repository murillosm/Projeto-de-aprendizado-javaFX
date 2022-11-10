package gui;


import java.io.IOException;
import java.lang.ref.Cleaner;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable{
	
	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
	/**
	 * métodos pra tratar cada uma das ações nesses itens de menu
	 * */
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		//System.out.println("onMenuItemSellerAction");
		loadView("/gui/DepartmentList.fxml");
	}

	@FXML
	public void onMenuItemAboutAction() {
		//System.out.println("onMenuItemSellerAction");
		loadView("/gui/About.fxml");
	}


	/**
	 * Método da interface Initializable
	 */
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}
	
	/**
	 * Porque o nome é absoluto? 
	 * ->porque eu vou passar o caminho completo
	 * @param absoluteName
	 */
	private void loadView(String absoluteName) {
		try {
			//carregar uma tela
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			
			Scene mainScene = Main.getMainScene(); //para que eu tenha condição de trabalhar com a janela principal eu vou ter que pegar uma referência da cena a cena em que a janela principal está ela foi declarada na classe main
			
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); //referencia do VBOX da janela principal
			
			Node mainMenu = mainVBox.getChildren().get(0);//guardar uma referência para o menu
			
			mainVBox.getChildren().clear(); //limpar todos os filhos limpar mainVBox
			
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("IO Exeception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
}
