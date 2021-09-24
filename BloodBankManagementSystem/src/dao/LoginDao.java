package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.MyDatabaseConnection;
import pojo.*;
import java.sql.*;

public class LoginDao {

	public LoginDao() {
	}
	
	public boolean checkDetails(Login l) throws Exception {
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		boolean isCorrect = false;
//		
//		String query = new String();
//		con = MyDatabaseConnection.getConnection();
//		query = "select * from user;";
//		st = con.createStatement();
//		rs = st.executeQuery(query);
//		rs.next();
//		String correctUser = rs.getString(1);
//	    String correctPswd = rs.getString(2);
//		
//	    String pswd = new String(l.getPswd()); //converting char[] into string
//	    if(pswd.equals(correctPswd) && l.getUname().equals(correctUser)) {
//	    	isCorrect = true;
//	    }
//	    rs.close();
//	    st.close();
//	    con.close();
//	    return isCorrect;
		return true;
	}
	
	public boolean changeDetails(Login l) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		con = MyDatabaseConnection.getConnection();
		String query = "UPDATE user SET uname = ? , pswd = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, l.getUname());
		String pswd = new String(l.getPswd()); //converting char[] into string
		ps.setString(2, pswd);
		int count = ps.executeUpdate();
		ps.close();
		con.close();
		if(count > 0)
			return true;
		return false;
	}
}
