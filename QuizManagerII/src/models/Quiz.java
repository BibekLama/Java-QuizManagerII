package models;

public class Quiz {
	
	private int ID;
	private String title;
	
	public Quiz(){}
	
	public Quiz(int ID, String title){
		this.ID = ID;
		this.title = title;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
