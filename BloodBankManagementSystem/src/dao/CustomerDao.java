package dao;

import connection.*;
import pojo.*;
import java.sql.*;

public class CustomerDao {

	public CustomerDao() {
	}
	public boolean insert(Customer c) {
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		Connection con = null;
		try {
			con = MyDatabaseConnection.getConnection();	
			con.setAutoCommit(false);
			
			//insert values into customer db
			String query = "insert into customer values(?,?,?,?,?,?,?,?,?);";
			ps = con.prepareStatement(query);
			ps.setString(1, c.getName());
			ps.setString(2, c.getGender());
			ps.setString(3, c.getBg());
			ps.setString(4, c.getDate());
			ps.setString(5, c.getPhone());
			ps.setString(6, c.getAddress());
			ps.setInt(7, c.getUnits());
			ps.setFloat(8, c.getRate());
			ps.setFloat(9, c.getAmount());
			int count = ps.executeUpdate();

			//fetching no. of blood units currently present
			query = "select Quantity from blood where BG = '" + c.getBg() + "';";
			st = con.createStatement();
			rs= st.executeQuery(query);
			rs.beforeFirst();
			rs.next();
			int bloodQuantity = rs.getInt(1);
			
			//updating bloodQuantity
			bloodQuantity = bloodQuantity - c.getUnits();
			query = "UPDATE blood SET Quantity = ? WHERE BG = ?;";
			ps2 = con.prepareStatement(query);
			ps2.setInt(1, bloodQuantity);
			ps2.setString(2, c.getBg());
			int count2 = ps2.executeUpdate();
			
			con.commit();
			
			ps.close();
	        st.close();
	        rs.close();
	        ps2.close();
	        con.close();
	        
	        if(count != 0 && count2 != 0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			try{
				if(con!=null)
				con.rollback();
				}catch(SQLException se){
				}
			return false;
		}
		finally {
			   try {
	                if (ps != null) {
	                    ps.close();
	                }
	            } catch (SQLException se1) {
	            }
			   try {
				   if(st != null) {
					 st.close();
				   }
			   } catch (SQLException se2) {
			   }
			   try {
	                if (rs != null) {
	                    rs.close();
	                }
	            } catch (SQLException se3) {
	            }
			   try {
	                if (ps2 != null) {
	                    ps2.close();
	                }
	            } catch (SQLException se4) {
	            }
			   try {
				   if(con != null) {
					 con.close();
				   }
			   } catch (SQLException se5) {
			   }
		}
	}
}
