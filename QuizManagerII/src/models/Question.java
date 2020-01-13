package models;

public class Question {
	private int ID;
	private int quiz_id;
	private String question;
	
	public Question(){}
	
	public Question(int quiz_id, String question){
		this.quiz_id = quiz_id;
		this.question = question;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getQuiz_id() {
		return quiz_id;
	}
	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}
