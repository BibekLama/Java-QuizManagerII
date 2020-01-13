package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Question;

public class QuestionDAO {
	private DBConnection db;
	
	public QuestionDAO() {
		this.db = new DBConnection("jdbc:mysql://localhost:3306/quiz_manager_db", "root","");
	}
	
	public int saveQuestion(Question question){
		try {
			String sql = " INSERT INTO questions (quiz_id, question)"
			        + " VALUES (?, ?)";
			PreparedStatement preparedStmt = this.db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setInt (1, question.getQuiz_id());
			preparedStmt.setString (2, question.getQuestion());
			if(preparedStmt.executeUpdate() > 0){
				ResultSet rs = preparedStmt.getGeneratedKeys();
				if (rs.next()) {
			        return rs.getInt(1);
			    }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public ObservableList<Question> getQuestionByQuiz(int quiz_id){
		ObservableList<Question> questList = FXCollections.<Question>observableArrayList();
		try {
			String sql = " SELECT * FROM questions WHERE quiz_id = ?";
			PreparedStatement preparedStmt = this.db.getConnection().prepareStatement(sql);
			preparedStmt.setInt (1, quiz_id);
			ResultSet rs = preparedStmt.executeQuery();
			while(rs.next()) {
				Question quest = new Question();
				quest.setID(rs.getInt("id"));
				quest.setQuiz_id(rs.getInt("quiz_id"));
				quest.setQuestion(rs.getString("question"));
				questList.add(quest);
			}
			rs.close();
			preparedStmt.close();
			this.db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questList;
	}
}
