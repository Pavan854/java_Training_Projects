package student;
import java.util.*;
import java.io.*;
import java.sql.*;

public class Database_connection {
	
	static String url="jdbc:mysql://localhost:3306/student_management";
	static String user="root";
	static String pass="root";
	
	
	public static Connection getConnection() {
		
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			System.out.println("Database connected sucessfully");
			return con;
		}catch(Exception e) {
			
			System.err.println("Database Not connected");
			e.printStackTrace();
			return null;
		}
		
	}

	public static void main(String[] args) {
		Connection con=getConnection();
		if(con!=null) {
			try {
				con.close();
				System.out.println("Connection closed");
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

}
