package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminLoginController implements Initializable{
	@FXML
	private VBox container;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Login.fxml"));
			loader.setController(new ALoginController());
			Parent root = loader.load();
			container.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goBack(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("../views/Starter.fxml"));
		Scene scene = new Scene(root);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

}
