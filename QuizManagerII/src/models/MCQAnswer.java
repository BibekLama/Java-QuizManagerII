package models;

public class MCQAnswer {
	private int ID;
	private int quest_id;
	private String answer;
	private boolean isCorrect;
	
	public MCQAnswer(){}
	
	public MCQAnswer(int quest_id, String answer, boolean isCorrect) {
		super();
		this.quest_id = quest_id;
		this.answer = answer;
		this.isCorrect = isCorrect;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getQuest_id() {
		return quest_id;
	}
	public void setQuest_id(int quest_id) {
		this.quest_id = quest_id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}
