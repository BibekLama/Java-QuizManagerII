package controllers.admin;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DAOs.MCQAnswerDAO;
import DAOs.QuestionDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import models.MCQAnswer;
import models.Question;
import models.Quiz;

public class QuizMainController implements Initializable{
	
	private Quiz quiz;
	
	@FXML
	private TableView<Quest> questionTable;
	
	private String selectedAnswer;
	
	private Question selectedQuestion;
	

	private ObservableList<MCQAnswer> answers;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		questionTable.setFocusTraversable(false);
	}
	
	public void setQuiz(Quiz quiz){
		this.quiz = quiz;
		showDataTable();
	}
	
	@FXML
	public void openNewQuestForm(ActionEvent event){
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("New Question");
		dialog.setHeaderText("Add new question \n" +
		    "press Okay (or click title bar 'X' for cancel).");
		dialog.setResizable(false);
		
		Label question = new Label("Question: ");
		TextArea questInput = new TextArea();
		
		Label opt1 = new Label("Option A: ");
		TextField opt1Input = new TextField();
		opt1Input.setPrefWidth(250);
		RadioButton optRadioBtn1 = new RadioButton("Correct Option A? ");
				
		Label opt2 = new Label("Option B: ");
		TextField opt2Input = new TextField();
		opt2Input.setPrefWidth(250);
		RadioButton optRadioBtn2 = new RadioButton("Correct Option B? ");
		
		Label opt3 = new Label("Option C: ");
		TextField opt3Input = new TextField();
		opt3Input.setPrefWidth(250);
		RadioButton optRadioBtn3 = new RadioButton("Correct Option C? ");
		
		Label opt4 = new Label("Option D: ");
		TextField opt4Input = new TextField();
		opt4Input.setPrefWidth(250);
		RadioButton optRadioBtn4 = new RadioButton("Correct Option D? ");
		
		Label msg = new Label("");
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(15);
		grid.add(question, 1, 1, 1, 1);
		grid.add(questInput, 2, 1, 2, 1);
		grid.setPadding(new Insets(20));
		
		grid.add(opt1, 1, 2, 1, 1);
		grid.add(opt1Input, 2, 2, 1, 1);
		grid.add(optRadioBtn1, 3, 2, 1, 1);
		
		grid.add(opt2, 1, 3, 1, 1);
		grid.add(opt2Input, 2, 3, 1, 1);
		grid.add(optRadioBtn2, 3, 3, 1, 1);
		
		grid.add(opt3, 1, 4, 1, 1);
		grid.add(opt3Input, 2, 4, 1, 1);
		grid.add(optRadioBtn3, 3, 4, 1, 1);
		
		grid.add(opt4, 1, 5, 1, 1);
		grid.add(opt4Input, 2, 5, 1, 1);
		grid.add(optRadioBtn4, 3, 5, 1, 1);
		
		grid.add(msg, 1, 6, 3, 1);
		
		ToggleGroup radioGroup = new ToggleGroup();

		optRadioBtn1.setToggleGroup(radioGroup);
		optRadioBtn2.setToggleGroup(radioGroup);
		optRadioBtn3.setToggleGroup(radioGroup);
		optRadioBtn4.setToggleGroup(radioGroup);
		
		radioGroup.selectToggle(optRadioBtn1);
		radioGroup.equals(optRadioBtn4);
		selectedAnswer = "Option A";
		
		radioGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
			System.out.println(newVal + " was selected");
			if(optRadioBtn1 == newVal){
				selectedAnswer = "Option A";
			}
			if(optRadioBtn2 == newVal){
				selectedAnswer = "Option B";
			}
			if(optRadioBtn3 == newVal){
				selectedAnswer = "Option C";
			}
			if(optRadioBtn4 == newVal){
				selectedAnswer = "Option D";
			}
		});
		
		dialog.getDialogPane().setContent(grid);
		
		ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		
		dialog.setResultConverter(new Callback<ButtonType, String>() {
		    @Override
		    public String call(ButtonType b) {
		        if (b == buttonTypeOk) {
		        	if(questInput.getText().isEmpty() ||
		        			opt1Input.getText().isEmpty() ||
		        			opt2Input.getText().isEmpty() ||
		        			opt3Input.getText().isEmpty() ||
		        			opt4Input.getText().isEmpty()){
		        			return "empty";
		        	}else{
			        	Question quest = new Question(quiz.getID(), questInput.getText());
			        	QuestionDAO questionDAO = new QuestionDAO();
			        	int insertedId = questionDAO.saveQuestion(quest);
			        	if(insertedId > 0){
			        		System.out.println("Inserted: "+ insertedId);
			        		System.out.println("Selected: "+ selectedAnswer);
			        		MCQAnswerDAO mcqAnswerDAO = new MCQAnswerDAO();
			        		MCQAnswer answer1 = new MCQAnswer(insertedId, opt1Input.getText(), (selectedAnswer.equals("Option A")) ? true : false);
			        		mcqAnswerDAO.saveMCQAnswer(answer1);
			        		MCQAnswer answer2 = new MCQAnswer(insertedId, opt2Input.getText(), (selectedAnswer.equals("Option B")) ? true : false);
			        		mcqAnswerDAO.saveMCQAnswer(answer2);
			        		MCQAnswer answer3 = new MCQAnswer(insertedId, opt3Input.getText(), (selectedAnswer.equals("Option C")) ? true : false);
			        		mcqAnswerDAO.saveMCQAnswer(answer3);
			        		MCQAnswer answer4 = new MCQAnswer(insertedId, opt4Input.getText(), (selectedAnswer.equals("Option D")) ? true : false);
			        		mcqAnswerDAO.saveMCQAnswer(answer4);
			        		showDataTable();
							return "saved";
			        	}
		        	}
		        }
		        return null;
		    }
		});
		
		Optional<String> result = dialog.showAndWait();
        
		if (result.isPresent()) {
			if(result.get().equals("saved")){
				Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Message");
			    alert.setHeaderText("Success!");
			    String s = "New Question edited successfully.";
			    alert.setContentText(s);
			    alert.show();
			}
			
			if(result.get().equals("empty")){
				Alert alert = new Alert(AlertType.ERROR);
			    alert.setTitle("Message");
			    alert.setHeaderText("Error!");
			    String s = "Please fill all fields in the form";
			    alert.setContentText(s);
			    alert.show();
			}
		}
		
	}
	
	@FXML
	public void openEditQuestForm(ActionEvent event){
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Edit Question");
		dialog.setHeaderText("Edit question \n" +
		    "press Okay (or click title bar 'X' for cancel).");
		dialog.setResizable(false);
		
		Label question = new Label("Question: ");
		TextArea questInput = new TextArea(this.selectedQuestion.getQuestion());
		
		Label opt1 = new Label("Option A: ");
		TextField opt1Input = new TextField();
		opt1Input.setPrefWidth(250);
		RadioButton optRadioBtn1 = new RadioButton("Correct Option A? ");
				
		Label opt2 = new Label("Option B: ");
		TextField opt2Input = new TextField();
		opt2Input.setPrefWidth(250);
		RadioButton optRadioBtn2 = new RadioButton("Correct Option B? ");
		
		Label opt3 = new Label("Option C: ");
		TextField opt3Input = new TextField();
		opt3Input.setPrefWidth(250);
		RadioButton optRadioBtn3 = new RadioButton("Correct Option C? ");
		
		Label opt4 = new Label("Option D: ");
		TextField opt4Input = new TextField();
		opt4Input.setPrefWidth(250);
		RadioButton optRadioBtn4 = new RadioButton("Correct Option D? ");
		
		Optional<String> result = dialog.showAndWait();
		
		
	}
	
	@FXML
	public void deleteQuestConfirm(ActionEvent event){
		
	}
	
	private void showDataTable(){
		questionTable.getColumns().clear();
		QuestionDAO questionDAO = new QuestionDAO();
		System.out.println("Quiz ID: "+this.quiz.getID());
		ObservableList<Question> data = questionDAO.getQuestionByQuiz(this.quiz.getID());
		ObservableList<Quest> questList = FXCollections.<Quest>observableArrayList();
		if(data != null){
			for(Question que : data){
				Quest q = new Quest(que.getID(), que.getQuiz_id(), que.getQuestion());
				questList.add(q);
			}
		}
		
//		TableColumn<Quest, String> questNo = new TableColumn("No");
		TableColumn<Quest, String> questCol = new TableColumn<>("Question");
		TableColumn<Quest, Integer> questOpt1 = new TableColumn<>("Oprion A");	
		TableColumn<Quest, Integer> questOpt2 = new TableColumn<>("Option B");
		TableColumn<Quest, Integer> questOpt3 = new TableColumn<>("Option C");
		TableColumn<Quest, Integer> questOpt4 = new TableColumn<>("Option D");	
		
		questCol.setCellValueFactory(
                new PropertyValueFactory<Quest, String>("questn"));
		
		questOpt1.setCellValueFactory(
                new PropertyValueFactory<Quest, Integer>("id"));
		
		questOpt1.setCellFactory(col -> new TableCell<Quest, Integer>() {
		    @Override
		    protected void updateItem(Integer item, boolean empty) {
		         super.updateItem(item, empty);
		         if (item != null) {
		        	 MCQAnswerDAO mcqDAO = new MCQAnswerDAO();
		        	 answers = mcqDAO.getMCQAnswersByID(item);
		        	 if(answers != null){
		        		 if(answers.get(0) != null){
		        			 setText(answers.get(0).getAnswer()+""+(answers.get(0).isCorrect() ? " *":""));
		        		 }else{
		        			 setText("Add Answer");
		        		 }
		        	 }
		         }
		    }
		});
		
		questOpt2.setCellValueFactory(
                new PropertyValueFactory<Quest, Integer>("id"));
		
		questOpt2.setCellFactory(col -> new TableCell<Quest, Integer>() {
		    @Override
		    protected void updateItem(Integer item, boolean empty) {
		         super.updateItem(item, empty);
		         if (item != null) {
		        	 if(answers != null){
		        		 if(answers.get(1) != null){
		        			 setText(answers.get(1).getAnswer()+""+(answers.get(1).isCorrect() ? " *":""));
		        		 }else{
		        			 setText("Add Answer");
		        		 }
		        	 }
		         }
		    }
		});
		
		questOpt3.setCellValueFactory(
                new PropertyValueFactory<Quest, Integer>("id"));
		
		questOpt3.setCellFactory(col -> new TableCell<Quest, Integer>() {
		    @Override
		    protected void updateItem(Integer item, boolean empty) {
		         super.updateItem(item, empty);
		         if (item != null) {
		        	 if(answers != null){
		        		 if(answers.get(0) != null){
		        			 setText(answers.get(2).getAnswer()+""+(answers.get(2).isCorrect() ? " *":""));
		        		 }else{
		        			 setText("Add Answer");
		        		 }
		        	 }
		         }
		    }
		});
		
		questOpt4.setCellValueFactory(
                new PropertyValueFactory<Quest, Integer>("id"));
		
		questOpt4.setCellFactory(col -> new TableCell<Quest, Integer>() {
		    @Override
		    protected void updateItem(Integer item, boolean empty) {
		         super.updateItem(item, empty);
		         if (item != null) {
		        	 if(answers != null){
		        		 if(answers.get(0) != null){
		        			 setText(answers.get(3).getAnswer()+""+(answers.get(3).isCorrect() ? " *":""));
		        		 }else{
		        			 setText("Add Answer");
		        		 }
		        	 }
		         }
		    }
		});
		
		questionTable.setItems(questList);
		questionTable.getColumns().addAll(questCol, questOpt1, questOpt2, questOpt3, questOpt4);
		questionTable.getSelectionModel().select(0);
		Quest que = questionTable.getSelectionModel().getSelectedItem();
		setSelectedQuestion(que);
		questionTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
		    if (newValue != null) {
		    	setSelectedQuestion(newValue);
		    }
		});

	}
	
	private void setSelectedQuestion(Quest que){
		Question q = new Question();
    	q.setID(que.getId());
    	q.setQuiz_id(que.getQuiz_id());
    	q.setQuestion(que.getQuestn());
        selectedQuestion = q;
        System.out.println(q.getQuestion());
	}
	
	public class Quest {
	    private final SimpleIntegerProperty id;
	    private final SimpleIntegerProperty quiz_id;
	    private final SimpleStringProperty questn;
	    
		public Quest(int id, int quiz_id, String questn) {
			this.id = new SimpleIntegerProperty(id);
			this.quiz_id = new SimpleIntegerProperty(quiz_id);
			this.questn = new SimpleStringProperty(questn);
		}
		public int getId() {
			return id.get();
		}
		public void setId(int id) {
			this.id.set(id);
		}
		public int getQuiz_id() {
			return quiz_id.get();
		}
		public void setQuiz_id(int quiz_id) {
			this.quiz_id.set(quiz_id);
		}
		public String getQuestn() {
			return questn.get();
		}
		public void setQuestn(String questn) {
			this.questn.set(questn);
		}
	}

}
