package jdbcbasics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {
	public static void main(String args[])
	{
		Connection connection=null;
		Statement statement = null;
		ResultSet rs=null;
		try {
			 connection=DriverManager.getConnection("jdbc:mysql://localhost/my_db","root","SAIKrishna@1");
			 statement = connection.createStatement();
			 rs=statement.executeQuery("select * from account");
			while(rs.next()) {
				int id=rs.getInt(1);
				String fname=rs.getString(2);
				String lname=rs.getString(3);
				int balance=rs.getInt(4);
				System.out.println(id+"|"+fname+"|"+lname+"|"+balance);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
			connection.close();
			statement.close();
			rs.close();}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
