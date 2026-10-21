
package library;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        if (BookManagement.con == null) {
            System.out.println("Database connection failed!");
            return;
        }

        int choice = -1;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Add Book Issue");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Search Book");
            System.out.println("7. View Tables");
            System.out.println("8. View Books Between Dates");
            System.out.println("9. Category Count");
            System.out.println("10. View Book Issue Details ");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {

                    case 1:
                        BookManagement.addBook();
                        break;

                    case 2:
                        BookManagement.addMember();
                        break;

                    case 3:
                        BookManagement.addBookIssue();
                        break;

                    case 4:
                        BookManagement.updateBook();
                        break;

                    case 5:
                        BookManagement.deleteBook();
                        break;

                    case 6:
                        BookManagement.searchBook();
                        break;

                    case 7:

                        int tableChoice = -1;

                        do {
                            System.out.println("\n========== VIEW TABLES ==========");
                            System.out.println("1. View Books Table");
                            System.out.println("2. View Members Table");
                            System.out.println("3. View Book Issues Table");
                            System.out.println("4. View Book Issue Details (JOIN)");
                            System.out.println("5. View Books Between Dates");
                            System.out.println("6. Category Count");
                            System.out.println("0. Back to Main Menu");
                            System.out.print("Enter your choice: ");

                            tableChoice = Integer.parseInt(sc.nextLine().trim());

                            switch (tableChoice) {

                                case 1:
                                    BookManagement.viewBooks();
                                    break;

                                case 2:
                                    BookManagement.viewMembers();
                                    break;

                                case 3:
                                    BookManagement.viewBookIssues();
                                    break;

                                case 4:
                                    BookManagement.viewBookIssueDetails();
                                    break;

                                case 5:
                                    BookManagement.viewBooksBetweenDates();
                                    break;

                                case 6:
                                    BookManagement.categoryCount();
                                    break;

                                case 0:
                                    System.out.println("Returning to main menu...");
                                    break;

                                default:
                                    System.out.println("Invalid table choice.");
                            }

                        } while (tableChoice != 0);

                        break;

                    case 8:
                        BookManagement.viewBooksBetweenDates();
                        break;

                    case 9:
                        BookManagement.categoryCount();
                        break;

                    case 10:
                        BookManagement.viewBookIssueDetails();
                        break;

                    case 0:
                        System.out.println("Exiting Library Management System...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 0);

        try {
            BookManagement.con.close();
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }

        sc.close();
    }
}
