package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	//ActionEvent por exemplo o botão recebeu um click
	public static Stage currentStage(ActionEvent event) { 
		//acessar o Stage onde o meu controle que recebeu o evento está por exemplo o seu clique no botão
		return (Stage) ((Node) event.getSource()).getScene().getWindow(); 	}
	
	public static Integer tryParseToInt(String str){
		try {
			return Integer.parseInt(str);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

}
