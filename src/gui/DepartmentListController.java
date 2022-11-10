package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable{
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNode();
		
	}

	private void initializeNode() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id")); //iniciar apropriadamente o comportamento das colunas da tabela
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("Name")); 
		
		//referência para o Stage atual
		Stage stage = (Stage) Main.getMainScene().getWindow(); //getWindow pega referencia para janela
		
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());//Faz o tableView acompalhar o tamanho da janela
		
	}

}
