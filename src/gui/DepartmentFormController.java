package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable{
	
	private Department entiny;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancel;
	
	public void setDepartment(Department entity) {
		this.entiny = entity;
	}
	
	
	@FXML
	protected void onBtSaveAction() {
		
	}
	
	@FXML
	protected void onBtCancelAction() {
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 40);
	}
	
	public void updateFormData() { //responsável por pegar os dados do departamento é popular de texto do formulário
		if (entiny == null) {
			throw new IllegalStateException("Entiny was null");
		}
		txtId.setText(String.valueOf(entiny.getIdDepartment()));
		txtName.setText(String.valueOf(entiny.getNameDepartment()));
	}

}
