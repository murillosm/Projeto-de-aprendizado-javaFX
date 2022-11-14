package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Seller;
import model.services.SellerService;

public class SellerListController implements Initializable, DataChangeListener {

	private SellerService service;

	@FXML
	private TableView<Seller> tableViewSeller;

	@FXML
	private TableColumn<Seller, Integer> tableColumnId;

	@FXML
	private TableColumn<Seller, String> tableColumnName;

	@FXML
	private TableColumn<Seller, Seller> tableColumnEDIT;

	@FXML
	private TableColumn<Seller, Seller> tableColumnREMOVE;

	@FXML
	private Button btNew;

	private ObservableList<Seller> obsList;

	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parantStage = Utils.currentStage(event);
		Seller obj = new Seller();
		createDialogForm(obj, "/gui/SellerForm.fxml", parantStage);
	}

	public void setSellerService(SellerService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNode();

	}

	private void initializeNode() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("idSeller")); // iniciar apropriadamente o
																						// comportamento das colunas da
																						// tabela
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nameSeller"));

		// referência para o Stage atual
		Stage stage = (Stage) Main.getMainScene().getWindow(); // getWindow pega referencia para janela

		tableViewSeller.prefHeightProperty().bind(stage.heightProperty());// Faz o tableView acompalhar o tamanho da
																				// janela

	}

	public void updeteTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Seller> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);// instancia o ObservableList pegando os dados originais da
															// lista

		tableViewSeller.setItems(obsList);// carregar os itens na tableView e mostrar na tela

		initEditButtons();
		initRemoveButtons();
	}

	private void createDialogForm(Seller obj, String absoluteName, Stage parentStage) {
//		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); // Abrir janela de formulario
//			Pane pane = loader.load(); // carregar view
//
//			SellerFormController controller = loader.getController();
//			controller.setSeller(obj);
//			controller.setSellerService(new SellerService());
//			controller.subscribeDataChangeListener(this);
//			controller.updateFormData();
//
//			/**
//			 * quando carregar uma janela de diálogo modal na frente da janela existente e
//			 * necessario que instancia um novo stage
//			 */
//			Stage dialogStage = new Stage();
//			dialogStage.setTitle("Enter Seller data"); // configurar o título da janela
//			dialogStage.setScene(new Scene(pane));// nova cena
//			dialogStage.setResizable(false); // diz se janela pode ou não ser redimencionada
//			dialogStage.initOwner(parentStage);
//			dialogStage.initModality(Modality.WINDOW_MODAL);// a janela vai ficar travada enquanto você não fechar não
//															// pode acessar a janela anterior
//			dialogStage.showAndWait();// carregar a janela do formulário
//
//		} catch (IOException e) {
//			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
//		}
	}

	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Seller, Seller>() {
			private final Button button = new Button("edit");

			@Override
			protected void updateItem(Seller obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/SellerForm.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Seller, Seller>() {
			private final Button button = new Button("remove");

			@Override
			protected void updateItem(Seller obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}

	private void removeEntity(Seller obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you sure to delete? ");
		
		if (result.get() == ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Service was null");
			}
			try {
				service.remove(obj);
				updeteTableView();
			} catch (DbIntegrityException e) {
				Alerts.showAlert("Error removing object",  null, e.getMessage(), AlertType.ERROR);
			}
			
		}
	}

	@Override
	public void onDataChanged() {
		updeteTableView();
	}

}
