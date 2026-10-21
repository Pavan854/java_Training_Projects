
package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BookManagement {

    static Scanner sc = new Scanner(System.in);
    static Connection con = Database_connection.getConnection();

    public static void addBook() {

        String query = """
                insert into books (title, author, category, quantity)
                values (?, ?, ?, ?)
                """;

        try {
            System.out.print("Enter book title: ");
            String title = sc.nextLine();

            System.out.print("Enter author name: ");
            String author = sc.nextLine();

            System.out.print("Enter category: ");
            String category = sc.nextLine();

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());

            if (quantity < 0) {
                System.out.println("Quantity cannot be negative.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, title);
                ps.setString(2, author);
                ps.setString(3, category);
                ps.setInt(4, quantity);

                int rows = ps.executeUpdate();
                System.out.println(rows + " book added successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding book:" + e.getMessage());
        }
    }
    
    public static void addMember() {

        String query = """
                insert into members
                (name, email, phone, registration_date)
                values (?, ?, ?, ?)
                """;

        System.out.println("Enter member name:");
        String name = sc.nextLine();

        System.out.println("Enter email:");
        String email = sc.nextLine();

        System.out.println("Enter phone:");
        String phone = sc.nextLine();

        System.out.println("Enter registration date (yyyy-mm-dd):");
        String date = sc.nextLine();

        try (PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setDate(4, java.sql.Date.valueOf(date));

            int rows = ps.executeUpdate();

            System.out.println(rows + " member added successfully.");

        } catch (SQLException e) {
            System.out.println("Member not added: " + e.getMessage());
        }
    }
    
    
    public static void addBookIssue() {

        String query = """
                insert into book_issues
                (book_id, member_id, issue_date, due_date, status)
                values (?, ?, ?, ?, ?)
                """;

        System.out.println("Enter book ID:");
        int bookId = Integer.parseInt(sc.nextLine());

        System.out.println("Enter member ID:");
        int memberId = Integer.parseInt(sc.nextLine());

        System.out.println("Enter issue date (yyyy-mm-dd):");
        String issueDate = sc.nextLine();

        System.out.println("Enter due date (yyyy-mm-dd):");
        String dueDate = sc.nextLine();

        try (PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);
            ps.setDate(3, java.sql.Date.valueOf(issueDate));
            ps.setDate(4, java.sql.Date.valueOf(dueDate));
            ps.setString(5, "Issued");

            int rows = ps.executeUpdate();

            System.out.println(rows + " book issue added successfully.");

        } catch (SQLException e) {
            System.out.println("Book issue failed: " + e.getMessage());
        }
    }
    
    
    public static void viewBooks() throws SQLException {

        String query = "select * FROM books";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.printf("%-10s %-30s %-25s %-20s %-10s%n",
                    "Book ID", "Title", "Author", "Category", "Quantity");

            System.out.println("-".repeat(100));

            while (rs.next()) {
                System.out.printf("%-10d %-30s %-25s %-20s %-10d%n",
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("quantity"));
            }
        }
    }
    
    public static void viewMembers() throws SQLException {

        String query = "select * FROM members";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.printf("%-10s %-20s %-30s %-15s %-15s%n",
                    "Member ID", "Name", "Email", "Phone", "Reg Date");

            System.out.println("-".repeat(95));

            while (rs.next()) {
                System.out.printf("%-10d %-20s %-30s %-15s %-15s%n",
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDate("registration_date"));
            }
        }
    }
    
    public static void viewBookIssues() throws SQLException {

        String query = "select * FROM book_issues";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.printf("%-10s %-10s %-10s %-15s %-15s %-15s %-12s%n",
                    "Issue ID", "Book ID", "Member ID",
                    "Issue Date", "Due Date", "Return Date", "Status");

            System.out.println("-".repeat(95));

            while (rs.next()) {
                System.out.printf("%-10d %-10d %-10d %-15s %-15s %-15s %-12s%n",
                        rs.getInt("issue_id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("issue_date"),
                        rs.getDate("due_date"),
                        rs.getDate("return_date"),
                        rs.getString("status"));
            }
        }
    }

   
    
    public static void updateBook() {

        String query = """
                update books
                set title = ?, author = ?, category = ?, quantity = ?
                where book_id = ?
                """;

        try {
            System.out.print("Enter book ID to update: ");
            int bookId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new title: ");
            String title = sc.nextLine();

            System.out.print("Enter new author: ");
            String author = sc.nextLine();

            System.out.print("Enter new category: ");
            String category = sc.nextLine();

            System.out.print("Enter new quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());

            if (quantity < 0) {
                System.out.println("Quantity cannot be negative.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, title);
                ps.setString(2, author);
                ps.setString(3, category);
                ps.setInt(4, quantity);
                ps.setInt(5, bookId);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Book updated successfully.");
                } else {
                    System.out.println("Book ID not found.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

   
    public static void deleteBook() {

        String query = "delete from books where book_id = ?";

        try {
            System.out.print("Enter book ID to delete: ");
            int bookId = sc.nextInt();
            sc.nextLine();

            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, bookId);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Book deleted successfully.");
                } else {
                    System.out.println("Book ID not found.");
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Could not delete book. It may have issue records. "
                    + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid book ID.");
        }
    }
    
    public static void viewBookIssueDetails() throws SQLException {

        String query = """
                SELECT b.title, b.author,
                       m.name, m.phone,
                       bi.issue_date, bi.due_date, bi.status
                FROM book_issues bi
                JOIN books b ON bi.book_id = b.book_id
                JOIN members m ON bi.member_id = m.member_id
                """;

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Book Name: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Member Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Issue Date: " + rs.getDate("issue_date"));
                System.out.println("Due Date: " + rs.getDate("due_date"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("----------------------------------");
            }
        }
    }
    
    public static void viewBooksBetweenDates() throws SQLException {
        String query = """
                SELECT *
                FROM book_issues
                WHERE issue_date BETWEEN ? AND ?
                """;

        System.out.println("Enter start date (yyyy-mm-dd):");
        String startDate = sc.nextLine();

        System.out.println("Enter end date (yyyy-mm-dd):");
        String endDate = sc.nextLine();

        try (PreparedStatement ps = con.prepareStatement(query)) {

            ps.setDate(1, java.sql.Date.valueOf(startDate));
            ps.setDate(2, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {

                System.out.printf("%-10s %-10s %-10s %-15s %-15s %-15s %-12s%n",
                        "Issue ID", "Book ID", "Member ID",
                        "Issue Date", "Due Date", "Return Date", "Status");

                System.out.println("-".repeat(95));

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.printf("%-10d %-10d %-10d %-15s %-15s %-15s %-12s%n",
                            rs.getInt("issue_id"),
                            rs.getInt("book_id"),
                            rs.getInt("member_id"),
                            rs.getDate("issue_date"),
                            rs.getDate("due_date"),
                            rs.getDate("return_date"),
                            rs.getString("status"));
                }

                if (!found) {
                    System.out.println("No book issues found between these dates.");
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date. Use yyyy-mm-dd format.");
        }
    }
    
    public static void searchBook() {

        String query = """
                SELECT * FROM books
                WHERE title LIKE ? OR author LIKE ?
                """;

        try {
            System.out.print("Enter book title or author to search: ");
            String search = "%" + sc.nextLine() + "%";

            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, search);
                ps.setString(2, search);

                try (ResultSet rs = ps.executeQuery()) {

                    boolean found = false;

                    while (rs.next()) {
                        found = true;

                        System.out.println("\nBook ID: "
                                + rs.getInt("book_id"));
                        System.out.println("Title: "
                                + rs.getString("title"));
                        System.out.println("Author: "
                                + rs.getString("author"));
                        System.out.println("Category: "
                                + rs.getString("category"));
                        System.out.println("Quantity: "
                                + rs.getInt("quantity"));
                        System.out.println("---------------------------");
                    }

                    if (!found) {
                        System.out.println("No matching books found.");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching books: " + e.getMessage());
        }
    }
    
    public static void categoryCount() throws SQLException {

        String query = """
                select category, count(*) AS total_books
                FROM books
                GROUP BY category
                """;

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.printf("%-25s %-15s%n",
                    "Category", "Total Books");

            System.out.println("-".repeat(40));

            while (rs.next()) {
                System.out.printf("%-25s %-15d%n",
                        rs.getString("category"),
                        rs.getInt("total_books"));
            }
        }
    }
    
    
    
    
}
