package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private Connection conn;
	private String url;
	private String name;
	private String password;
	
	public DBConnection(String url, String name, String password){
		this.url = url;
		this.name = name;
		this.password = password;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
	
	public Connection getConnection(){
		try {
			this.conn = DriverManager.getConnection(url,this.name,this.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(this.conn != null){
			System.out.println("Connection Successfull");
		}else{
			System.out.println("No Connection");
		}
		return this.conn;
	}
	
	public void closeConnection(){
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
