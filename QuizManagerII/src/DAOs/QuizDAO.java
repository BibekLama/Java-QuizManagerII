package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Quiz;

public class QuizDAO {
	
	private DBConnection db;
	
	public QuizDAO() {
		this.db = new DBConnection("jdbc:mysql://localhost:3306/quiz_manager_db", "root","");
	}
	
	public ObservableList<Quiz> getAllQuizes(){
		ObservableList<Quiz> quizList = FXCollections.<Quiz>observableArrayList();
		try {
			Statement st = this.db.getConnection().createStatement();
			String sql = "SELECT * FROM quizes ORDER BY id DESC";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Quiz quiz = new Quiz(rs.getInt("id"), rs.getString("title"));
				quizList.add(quiz);
			}
			rs.close();
			st.close();
			this.db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizList;
	}
	
	public boolean saveQuiz(Quiz quiz){
		try {
			String sql = " INSERT INTO quizes (title)"
			        + " VALUES (?)";
			PreparedStatement preparedStmt = this.db.getConnection().prepareStatement(sql);
			preparedStmt.setString (1, quiz.getTitle());
			if(preparedStmt.executeUpdate() == 1){
				return true;
			}
			this.db.closeConnection();
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Quiz getQuizByID(int id){
		try {
			String sql = " SELECT * FROM quizes WHERE id = '"+id+"'";
			Statement st = this.db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
//				System.out.println(rs.getString("title"));
				Quiz quiz = new Quiz();
				quiz.setID(rs.getInt("id"));
				quiz.setTitle(rs.getString("title"));
				return quiz;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean editQuiz(Quiz quiz){
		try {
			String sql = " UPDATE quizes set title = ? WHERE id = ?";
			PreparedStatement preparedStmt = this.db.getConnection().prepareStatement(sql);
			preparedStmt.setString (1, quiz.getTitle());
			preparedStmt.setInt (2, quiz.getID());
			if(preparedStmt.executeUpdate() == 1){
				return true;
			}
			this.db.closeConnection();
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteQuiz(Quiz quiz){
		try {
			String sql = " DELETE FROM quizes WHERE id = ?";
			PreparedStatement preparedStmt = this.db.getConnection().prepareStatement(sql);
			preparedStmt.setInt (1, quiz.getID());
			if(preparedStmt.executeUpdate() == 1){
				return true;
			}
			this.db.closeConnection();
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
