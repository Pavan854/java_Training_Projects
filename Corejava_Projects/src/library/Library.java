package library;

import java.util.Scanner;

public class Library {

    public static void main(String[] args) {

       Scanner sc=new Scanner(System.in);
       LibraryService service=new LibraryService();

        while (true) {

            System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book");
            System.out.println("4. Book Availability");
            System.out.println("5. Add Librarian");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Calculate Fine");
            System.out.println("9. Collect Fine");
            System.out.println("10. Due Date Reminder");
            System.out.println("11. Generate Report");
            System.out.println("12. Exit");

            System.out.println("Enter your choice: ");
            int choice = LibraryService.sc.nextInt();

            switch (choice) {

            case 1:
                service.addBook();
                break;

            case 2:
                service.displayBooks();
                break;

            case 3:
                service.searchBook();
                break;

            case 4:
                service.bookAvailability();
                break;

            case 5:
                service.addLibrarian();
                break;

            case 6:
                service.issueBook();
                break;

            case 7:
                service.returnBook();
                break;

            case 8:
                service.calculateFine();
                break;

            case 9:
                service.collectFine();
                break;

            case 10:
                service.dueDateReminder();
                break;

            case 11:
                service.generateReport();
                break;

            case 12:
                System.out.println("Thank you for using Library Management System");
                System.exit(0);

            default:
                System.out.println("Invalid choice");
            }
        }
    }
}