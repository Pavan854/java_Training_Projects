
package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_connection {

    public static Connection getConnection() {

        Connection con = null;

        String url = "jdbc:mysql://localhost:3306/library_management";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    url, username, password);

            System.out.println("Database connected successfully!");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        return con;
    }
    public static void main(String[] args) {
		getConnection();
	}
}
