
package library;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Table_creation {

    static Connection con = Database_connection.getConnection();

    
    public static void createBooksTable() {

        String query = """
                CREATE TABLE IF NOT EXISTS books (
                    book_id INT PRIMARY KEY AUTO_INCREMENT,
                    title VARCHAR(100) NOT NULL,
                    author VARCHAR(100) NOT NULL,
                    category VARCHAR(50),
                    quantity INT NOT NULL
                )
                """;

        createTable(query, "books");
    }


    public static void createMembersTable() {

        String query = """
                CREATE TABLE IF NOT EXISTS members (
                    member_id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) UNIQUE NOT NULL,
                    phone VARCHAR(15) NOT NULL,
                    registration_date DATE NOT NULL
                )
                """;

        createTable(query, "members");
    }

    
    public static void createBookIssuesTable() {

        String query = """
                CREATE TABLE IF NOT EXISTS book_issues (
                    issue_id INT PRIMARY KEY AUTO_INCREMENT,
                    book_id INT NOT NULL,
                    member_id INT NOT NULL,
                    issue_date DATE NOT NULL,
                    due_date DATE NOT NULL,
                    return_date DATE,
                    status VARCHAR(20) NOT NULL DEFAULT 'Issued',

                    FOREIGN KEY (book_id)
                        REFERENCES books(book_id),

                    FOREIGN KEY (member_id)
                        REFERENCES members(member_id)
                )
                """;

        createTable(query, "book_issues");
    }

    // Reusable method to create tables
    private static void createTable(String query, String tableName) {

        try (Statement st = con.createStatement()) {

            st.execute(query);
            System.out.println(tableName + " table created successfully.");

        } catch (SQLException e) {

            System.out.println("Error creating " + tableName + " table.");
            e.printStackTrace();
        }
    }

    // Main method to create all tables
    public static void main(String[] args) {

        if (con == null) {
            System.out.println("Database connection failed!");
            return;
        }

        try {
            createBooksTable();
            createMembersTable();
            createBookIssuesTable();

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
