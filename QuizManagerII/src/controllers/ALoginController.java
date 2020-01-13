package controllers;

import java.io.IOException;

import DAOs.AdminDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Admin;

public class ALoginController {
	@FXML
	private TextField unameInput;
	
	@FXML
	private PasswordField passInput;
	
	@FXML
	private Label msgLabel;
	
	@FXML
	public void doLogin(ActionEvent event) throws IOException{
		String username = unameInput.getText();
		String pass = passInput.getText();
		AdminDAO adminDAO= new AdminDAO();
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(pass);
		if(adminDAO.isAuthenticate(admin)){
			msgLabel.setText("Successfully login.");
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.close();
			Parent root = FXMLLoader.load(getClass().getResource("../views/admin/AdminMain.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}else{
			msgLabel.setText("Username or password incorrect.");
		}
	}
}
