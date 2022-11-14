package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.DepartmentService;
import model.services.SellerService;

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
		loadView("/gui/SellerList.fxml", (SellerListController controller) -> {
			controller.setSellerService(new SellerService());  //ação de inicialização do controller departamento
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());  //ação de inicialização do controller departamento
			controller.updeteTableView();
		});
	}

	@FXML
	public void onMenuItemAboutAction() {
		//System.out.println("onMenuItemSellerAction");
		loadView("/gui/About.fxml", x -> {});
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
	private synchronized <T>void loadView(String absoluteName, Consumer<T> initializengAction) {
		try {
			//carregar uma tela
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			
			//para que eu tenha condição de trabalhar com a janela principal eu vou 
			//ter que pegar uma referência da cena a cena em que a janela principal está ela foi declarada na classe main
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); //referencia do VBOX da janela principal
			
			Node mainMenu = mainVBox.getChildren().get(0);//guardar uma referência para o menu
			mainVBox.getChildren().clear(); //limpar todos os filhos limpar mainVBox
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializengAction.accept(controller);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("IO Exeception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
