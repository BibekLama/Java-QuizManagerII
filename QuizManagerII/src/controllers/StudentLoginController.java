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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentLoginController implements Initializable{
	
	@FXML
	private VBox container;
	
	@FXML
	private Button loginBtn;
	
	@FXML
	private Button registerBtn;
	
	@FXML
	private ScrollPane scrollPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Login.fxml"));
			loader.setController(new SLoginController());
			Parent root = loader.load();
			container.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
	}
	
	@FXML
	public void goBack(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("../views/Starter.fxml"));
		Scene scene = new Scene(root);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	@FXML
	public void showRegisterForm(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/StudentRegisterForm.fxml"));
		Parent root = loader.load();
		container.getChildren().clear();
		container.getChildren().add(root);
		loginBtn.setDisable(false);
		registerBtn.setDisable(true);
	}
	
	@FXML
	public void showLoginForm(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Login.fxml"));
		loader.setController(new SLoginController());
		Parent root = loader.load();
		container.getChildren().clear();
		container.getChildren().add(root);
		loginBtn.setDisable(true);
		registerBtn.setDisable(false);
	}

}
