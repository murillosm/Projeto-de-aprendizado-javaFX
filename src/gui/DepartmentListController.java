package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable{
	
	private DepartmentService service;
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	private ObservableList<Department> obsList;
	
	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parantStage = Utils.currentStage(event);
		createDialogForm("/gui/DepartmentForm.fxml", parantStage);
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNode();
		
	}

	private void initializeNode() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("idDepartment")); //iniciar apropriadamente o comportamento das colunas da tabela
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nameDepartment")); 
		
		//referência para o Stage atual
		Stage stage = (Stage) Main.getMainScene().getWindow(); //getWindow pega referencia para janela
		
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());//Faz o tableView acompalhar o tamanho da janela
		
	}
	
	public void updeteTableView(){
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);//instancia o ObservableList pegando os dados originais da lista
		
		tableViewDepartment.setItems(obsList);//carregar os itens na tableView e mostrar na tela
	}
	
	private void createDialogForm(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));  //Abrir janela de formulario
			Pane pane = loader.load(); //carregar view 
			
			/**quando carregar uma janela de diálogo modal na frente 
			da janela existente e necessario que instancia um novo stage*/
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department data"); //configurar o título da janela
			dialogStage.setScene(new Scene(pane));//nova cena
			dialogStage.setResizable(false); //diz se janela pode ou não ser redimencionada
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);//a janela vai ficar travada enquanto você não fechar não pode acessar a janela anterior
			dialogStage.showAndWait();//carregar a janela do formulário
			
		}catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
