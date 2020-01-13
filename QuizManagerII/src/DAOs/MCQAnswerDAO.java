package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.MCQAnswer;

public class MCQAnswerDAO {
	private DBConnection db;
	
	public MCQAnswerDAO() {
		this.db = new DBConnection("jdbc:mysql://localhost:3306/quiz_manager_db", "root","");
	}
	
	public int saveMCQAnswer(MCQAnswer answer){
		try {
			String sql = " INSERT INTO mcq_answers (quest_id, answer, isCorrect)"
			        + " VALUES (?, ?, ?)";
			PreparedStatement preparedStmt = this.db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setInt (1, answer.getQuest_id());
			preparedStmt.setString (2, answer.getAnswer());
			preparedStmt.setBoolean (3, answer.isCorrect());
			if(preparedStmt.executeUpdate() > 0){
				ResultSet rs = preparedStmt.getGeneratedKeys();
				if (rs.next()) {
			        return rs.getInt(1);
			    }
			}
			preparedStmt.close();
			this.db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public ObservableList<MCQAnswer> getMCQAnswersByID(int quest_id){
		ObservableList<MCQAnswer> ansList = FXCollections.<MCQAnswer>observableArrayList();
		try {
			String sql = " SELECT * FROM mcq_answers WHERE quest_id = ?";
			PreparedStatement preparedStmt = this.db.getConnection().prepareStatement(sql);
			preparedStmt.setInt (1, quest_id);
			ResultSet rs = preparedStmt.executeQuery();
			while(rs.next()) {
				MCQAnswer answer = new MCQAnswer();
				answer.setID(rs.getInt("id"));
				answer.setQuest_id(rs.getInt("quest_id"));
				answer.setAnswer(rs.getString("Answer"));
				answer.setCorrect(rs.getBoolean("isCorrect"));
				ansList.add(answer);
			}
			rs.close();
			preparedStmt.close();
			this.db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ansList;
	}
}
