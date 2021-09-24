package dao;

import connection.*;
import java.sql.*;

public class QuantityByBg {

	public QuantityByBg() {
	}
	
	public int getQuantityByName(String bg) {
		int bloodQuantity = 0;
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		try {
			con = MyDatabaseConnection.getConnection();
			//fetching no. of blood units currently present
			String query = "select Quantity from blood where BG = '" + bg + "';";
			st = con.createStatement();
			rs= st.executeQuery(query);
			rs.beforeFirst();
			rs.next();
			bloodQuantity = rs.getInt(1);

	        st.close();
	        rs.close();
	        con.close();
	        
		}
		catch(Exception e) {
			bloodQuantity = -1;
		}
		finally {
			   try {
				   if(st != null) {
					 st.close();
				   }
			   } catch (SQLException se) {
			   }
			   try {
	                if (rs != null) {
	                    rs.close();
	                }
	            } catch (SQLException se) {
	            }
				try {
		            if (con != null) {
		            	con.close();
		            }
				} catch (SQLException se) {
		        }			   
		}
		return bloodQuantity;
	}
	
}
