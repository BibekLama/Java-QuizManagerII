package controllers.admin;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DAOs.QuizDAO;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Quiz;

public class AdminMainController implements Initializable{

	@FXML
	private Accordion quizAccordion;
	
	@FXML
	private AnchorPane container;
	
	@FXML 
	private Label listViewTitle;
	
	@FXML
	private Label quizCount;
	
	@FXML
	private ListView<Quiz> listView;
	
	private ObservableList<Quiz> quizList;
	
	@FXML
	private MenuBar menuBar;
	
	private Quiz selectedQuiz;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		showQuizData();	
		selectedQuiz = listView.getSelectionModel().getSelectedItem();
		showQuestionTable();
	}
	
	@FXML
	public void openNewQuizForm(ActionEvent event){
		Dialog<Quiz> dialog = new Dialog<>();
		dialog.setTitle("New Quiz");
		dialog.setHeaderText("Add new quiz \n" +
		    "press Okay (or click title bar 'X' for cancel).");
		dialog.setResizable(false);
		 
		Label title = new Label("Title: ");
		TextField titleInput = new TextField();
		         
		GridPane grid = new GridPane();
		grid.add(title, 1, 1);
		grid.add(titleInput, 2, 1);
		dialog.getDialogPane().setContent(grid);
		         
		ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		 
		dialog.setResultConverter(new Callback<ButtonType, Quiz>() {
		    @Override
		    public Quiz call(ButtonType b) {
		        if (b == buttonTypeOk) {
		        	Quiz quiz = new Quiz();
		        	quiz.setTitle(titleInput.getText());
		            return quiz;
		        }
		        return null;
		    }
		});
		
		Optional<Quiz> result = dialog.showAndWait();
        
		if (result.isPresent()) {
			
			Quiz quiz = new Quiz();
			quiz.setTitle(result.get().getTitle());
			QuizDAO quizDAO = new QuizDAO();
		    
		    if(quizDAO.saveQuiz(quiz)){
		    	showQuizData();
		    	Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Message");
			    alert.setHeaderText("Success!");
			    String s =result.get().getTitle()+ " added successfully.";
			    alert.setContentText(s);
			    alert.show();
		    }else{
		    	Alert alert = new Alert(AlertType.ERROR);
			    alert.setTitle("Message");
			    alert.setHeaderText("Error!");
			    String s ="Unable to add "+result.get().getTitle();
			    alert.setContentText(s);
			    alert.show();
		    }
		}
	}
	
	@FXML
	public void openEditQuizForm(ActionEvent event){
		int selectedID = listView.getSelectionModel().getSelectedItem().getID();
//		System.out.println(selectedID);
		QuizDAO quizDAO = new QuizDAO();
		Quiz quiz = quizDAO.getQuizByID(selectedID);
		if(quiz != null){
			Dialog<Quiz> dialog = new Dialog<>();
			dialog.setTitle("Edit Quiz");
			dialog.setHeaderText("Edit quiz \n" +
			    "press Okay (or click title bar 'X' for cancel).");
			dialog.setResizable(false);
			 
			Label title = new Label("Title: ");
			TextField titleInput = new TextField();
			titleInput.setText(quiz.getTitle());
			         
			GridPane grid = new GridPane();
			grid.add(title, 1, 1);
			grid.add(titleInput, 2, 1);
			dialog.getDialogPane().setContent(grid);
			         
			ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
			 
			dialog.setResultConverter(new Callback<ButtonType, Quiz>() {
			    @Override
			    public Quiz call(ButtonType b) {
			        if (b == buttonTypeOk) {
			        	Quiz quiz = new Quiz();
			        	quiz.setTitle(titleInput.getText());
			            return quiz;
			        }
			        return null;
			    }
			});
			
			Optional<Quiz> result = dialog.showAndWait();
	        
			if (result.isPresent()) {
				
				Quiz quiz1 = new Quiz();
				quiz1.setID(quiz.getID());
				quiz1.setTitle(result.get().getTitle());
				QuizDAO quizDAO1 = new QuizDAO();
			    
			    if(quizDAO1.editQuiz(quiz1)){
			    	showQuizData();
			    	Alert alert = new Alert(AlertType.INFORMATION);
				    alert.setTitle("Message");
				    alert.setHeaderText("Success!");
				    String s =result.get().getTitle()+ " edited successfully.";
				    alert.setContentText(s);
				    alert.show();
			    }else{
			    	Alert alert = new Alert(AlertType.ERROR);
				    alert.setTitle("Message");
				    alert.setHeaderText("Error!");
				    String s ="Unable to edit "+result.get().getTitle();
				    alert.setContentText(s);
				    alert.show();
			    }
			}
		}
	}
	
	@FXML
	public void openAbout(ActionEvent event){
		Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("About");
	    alert.setHeaderText("QuizManager");
	    String s = "Java project quiz application.\nBibek Lama (Group2)";
	    alert.setContentText(s);
	    alert.show();
	}
	
	@FXML
	public void deleteQuizConfirm(ActionEvent event){
		int selectedID = listView.getSelectionModel().getSelectedItem().getID();
		QuizDAO quizDAO = new QuizDAO();
		Quiz quiz = quizDAO.getQuizByID(selectedID);
		if(quiz != null){
			Dialog<Quiz> dialog = new Dialog<>();
			dialog.setTitle("Delete Quiz");
			dialog.setHeaderText("Delete "+ quiz.getTitle() +" quiz \n" +
			    "press Okay (or click title bar 'X' for cancel).");
			dialog.setResizable(false);
		
			ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
			 
			dialog.setResultConverter(new Callback<ButtonType, Quiz>() {
			    @Override
			    public Quiz call(ButtonType b) {
			        if (b == buttonTypeOk) {
			            return quiz;
			        }
			        return null;
			    }
			});
		
			Optional<Quiz> result = dialog.showAndWait();
	        
			if (result.isPresent()) {		
			    if(quizDAO.deleteQuiz(result.get())){
			    	showQuizData();
			    	Alert alert = new Alert(AlertType.INFORMATION);
				    alert.setTitle("Message");
				    alert.setHeaderText("Success!");
				    String s = result.get().getTitle()+ " deleted successfully.";
				    alert.setContentText(s);
				    alert.show();
			    }else{
			    	Alert alert = new Alert(AlertType.ERROR);
				    alert.setTitle("Message");
				    alert.setHeaderText("Error!");
				    String s ="Unable to delete "+result.get().getTitle();
				    alert.setContentText(s);
				    alert.show();
			    }
			}
		}
	}
	
	@FXML
	public void signOut(ActionEvent event) throws IOException{
		Stage window = (Stage)  menuBar.getScene().getWindow();
		window.close();
		Parent root = FXMLLoader.load(getClass().getResource("../../views/Starter.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setTitle("QuizManager");
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void exitProgram(ActionEvent event){
		Platform.exit();
        System.exit(0);
	}
	
	private  void getQuizData(){
		QuizDAO quizDAO = new QuizDAO();
		quizList = quizDAO.getAllQuizes();
	}
	
	// Get quiz list from database
	private void showQuizData(){
		if(listView.getItems().size() > 0){
			listView.getItems().clear();
		}
		getQuizData();
		quizCount.setText("("+quizList.size()+")");
		listView.getItems().addAll(quizList);
		listView.setFocusTraversable(false);
		listView.getSelectionModel().select(0);;
		
		listView.setCellFactory(new Callback<ListView<Quiz>, ListCell<Quiz>>() {
            @Override 
            public ListCell<Quiz> call(ListView<Quiz> list) {
                ListCell<Quiz> cell = new ListCell<Quiz>() {
                    @Override
                    public void updateItem(Quiz item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getTitle());
                        }
                    }
                };
                return cell;
            }
        });
		
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Quiz>() {

		    @Override
		    public void changed(ObservableValue<? extends Quiz> observable, Quiz oldValue, Quiz newValue) {
		        selectedQuiz = newValue;
		        showQuestionTable();
		    }
		});
	}
	
	private void showQuestionTable(){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/admin/QuizMain.fxml"));
			Parent root = loader.load();
			QuizMainController quizMainController = loader.getController();
			quizMainController.setQuiz(selectedQuiz);
			if(container.getChildren().size() > 0){
				container.getChildren().clear();
			}
			container.getChildren().add(root);
			AnchorPane.setTopAnchor(root, 0.0);
			AnchorPane.setRightAnchor(root, 0.0);
			AnchorPane.setLeftAnchor(root, 0.0);
			AnchorPane.setBottomAnchor(root, 0.0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
