package DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import models.Admin;

public class AdminDAO {
	private DBConnection db;
	
	public AdminDAO() {
		this.db = new DBConnection("jdbc:mysql://localhost:3306/quiz_manager_db", "root","");
	}
	
	public boolean isAuthenticate(Admin admin){
		System.out.println(admin.getHashedPassword(admin.getPassword()));
		try{
			Statement st = this.db.getConnection().createStatement();
			String sql = "SELECT * FROM admins WHERE username='"+admin.getUsername()+"' AND password='"+admin.getHashedPassword(admin.getPassword())+"'";
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				if(!rs.getString("username").isEmpty() && !rs.getString("password").isEmpty()){
					return true;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		db.closeConnection();
		return false;
	}
	
}
