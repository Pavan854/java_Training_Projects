package library;
import java.util.*;
import java.time.*;

public class LibraryService {
	
	ArrayList<Book> books = new ArrayList<>();
	ArrayList<Librarian> librarians = new ArrayList<>();
	
	HashMap<String,Book> issuedBooks=new HashMap<>();
	HashMap<String,LocalDate> dueDates=new HashMap<>();
	
	static Scanner sc=new Scanner(System.in);
	
	public void addBook() {
		System.out.println("Enter the BookId");
		int bookId=sc.nextInt();
		
		System.out.println("Enter the Book Name ");
		String title=sc.next();
		
		System.out.println("Enter Author name ");
		String author=sc.next();
		
		books.add(new Book(bookId, title,author));
		}
	
	public  void displayBooks() {
		
		Iterator<Book> ite=books.iterator();
		while(ite.hasNext()) {
			System.out.println(ite.next());
		}
	}
	
	public  void searchBook() {
		System.out.println("-----Search a Book-----");
		System.out.println("1.BookId");
		System.out.println("2.Title of Book");
		System.out.println("3.Author ");
		System.out.println("4.Exit");
		
		System.out.println("Enter the choice ");
		int choice=sc.nextInt();
		
		Iterator<Book> ite=books.iterator();
		boolean bookfound=false;
		
		switch(choice) {
		
		case 1:
			System.out.println("Enter the Book Id ");
			int bookId=sc.nextInt();
			while(ite.hasNext()) {
				Book currentbook=ite.next();
				if(bookId==currentbook.getBookId()) {
					bookfound=true;
					System.out.println(currentbook);	
				}	
			}
			if(bookfound) {
				System.out.println("Book found");
			}
			else {
				System.out.println("Book not found");
			}
			break;
		case 2:
			System.out.println("Enter the Book Title ");
			String title=sc.next();
			
			while(ite.hasNext()) {
				Book currentbook=ite.next();
				if(title.equalsIgnoreCase(currentbook.getTitle())) {
					bookfound=true;
					System.out.println(currentbook);	
				}	
			}
			if(bookfound) {
				System.out.println("Book found");
			}
			else {
				System.out.println("Book not found");
			}
			break;
		
		case 3:

			System.out.println("Enter the Book Author ");
			String author=sc.next();
			
			while(ite.hasNext()) {
				Book currentbook=ite.next();
				if(author.equalsIgnoreCase(currentbook.getAuthor())) {
					bookfound=true;
					System.out.println(currentbook);	
				}	
			}
			if(bookfound) {
				System.out.println("Book found");
			}
			else {
				System.out.println("Book not found");
			}
			break;
		case 4:
			System.exit(0);
		default:
			System.out.println("Invalid choice");
		}
		
	}
	public void bookAvailability() {
		System.out.println("Enter Book Id ");
		int bookId=sc.nextInt();
		Iterator<Book> ite=books.iterator();
		boolean bookFound=false;
		while(ite.hasNext()) {
			Book currentBook=ite.next();
			if(bookId==currentBook.getBookId()) {
				bookFound=true;
				
				if(currentBook.isAvailable()) {
					System.out.println("Book is available ");
				}
				else {
					System.out.println("Book is not Available");
				}
	
			}
		}
		if(!bookFound) {
			System.out.println("Book not found ");
			
		}
		
	}
	
	
	public void addLibrarian() {
		System.out.println("Enter Librarian Id ");
		int librarianId=sc.nextInt();
		System.out.println("Enter Librarian Name ");
		String librarianName=sc.next();
		
		librarians.add(new Librarian(librarianId,librarianName));
		System.out.println("Librarian Added Sucessfully");
		
		
		
		
		}
	
	public void issueBook() {

	    System.out.println("Enter User Id ");
	    int userId = sc.nextInt();

	    System.out.println("Enter Book Id ");
	    int bookId = sc.nextInt();

	    System.out.println("Enter Librarian Id ");
	    int librarianId = sc.nextInt();

	    Iterator<Librarian> lib = librarians.iterator();

	    boolean librarianFound = false;

	    while (lib.hasNext()) {

	        Librarian currentLib = lib.next();

	        if (librarianId == currentLib.getLibrarianId()) {

	            librarianFound = true;

	            System.out.println("Librarian Found: "
	                    + currentLib.getLibrarianName());

	            System.out.println("Enter the librarian approval (Yes/No)");
	            String approval = sc.next();

	            if (approval.equalsIgnoreCase("yes")) {

	                System.out.println(
	                        "Librarian Approved to distribute book");

	                Iterator<Book> ite = books.iterator();

	                boolean bookFound = false;

	                while (ite.hasNext()) {

	                    Book currentBook = ite.next();

	                    if (bookId == currentBook.getBookId()) {

	                        bookFound = true;

	                        if (currentBook.isAvailable()) {

	                            System.out.println("Book is available ");

	                            currentBook.setAvailable(false);

	                            String userKey = String.valueOf(userId);

	                            issuedBooks.put(userKey, currentBook);

	                            // Date handling
	                            LocalDate issueDate = LocalDate.now();
	                            
	                            LocalDate dueDate = issueDate.plusDays(7);

	                            dueDates.put(userKey, dueDate);

	                            System.out.println(
	                                    "Book issued successfully");

	                            System.out.println(
	                                    "Issue Date: " + issueDate);

	                            System.out.println(
	                                    "Due Date: " + dueDate);

	                        } else {

	                            System.out.println(
	                                    "Book is not Available");
	                        }
	                    }
	                }

	                if (!bookFound) {
	                    System.out.println("Book not found ");
	                }

	            } else {

	                System.out.println(
	                        "Librarian Not Approved to distribute book");
	            }
	        }
	    }

	    if (!librarianFound) {
	        System.out.println("Librarian not available");
	    }
	}
	
	
	public void returnBook() {

	    System.out.println("Enter user Id ");
	    int userId = sc.nextInt();

	    String userKey = String.valueOf(userId);

	    if (issuedBooks.containsKey(userKey)) {

	        Book currentBook = issuedBooks.get(userKey);

	        currentBook.setAvailable(true);

	        issuedBooks.remove(userKey);
	        dueDates.remove(userKey);

	        System.out.println("Book returned successfully");
	        System.out.println("Returned Book: " + currentBook);

	    } else {

	        System.out.println("No book is issued to this user");
	    }
	}
	
	public void calculateFine() {

	    System.out.println("Enter User Id ");
	    int userId = sc.nextInt();

	    String userKey = String.valueOf(userId);

	    if (dueDates.containsKey(userKey)) {

	        LocalDate dueDate = dueDates.get(userKey);
	        LocalDate today = LocalDate.now();

	        if (today.isAfter(dueDate)) {

	            long overdueDays = java.time.temporal.ChronoUnit.DAYS
	                    .between(dueDate, today);

	            double fine = overdueDays * 10;

	            System.out.println("Due Date: " + dueDate);
	            System.out.println("Today: " + today);
	            System.out.println("Overdue Days: " + overdueDays);
	            System.out.println("Fine Amount: ₹" + fine);

	        } else {

	            System.out.println("Book is not overdue");
	            System.out.println("No Fine");
	        }

	    } else {

	        System.out.println("No due date found for this user");
	    }
	}
	
	public void collectFine() {

	    System.out.println("Enter User Id ");
	    int userId = sc.nextInt();

	    String userKey = String.valueOf(userId);

	    if (dueDates.containsKey(userKey)) {

	        LocalDate dueDate = dueDates.get(userKey);
	        LocalDate today = LocalDate.now();

	        if (today.isAfter(dueDate)) {

	            long overdueDays = java.time.temporal.ChronoUnit.DAYS
	                    .between(dueDate, today);

	            double fine = overdueDays * 10;

	            System.out.println("Due Date: " + dueDate);
	            System.out.println("Overdue Days: " + overdueDays);
	            System.out.println("Fine Amount: ₹" + fine);

	            System.out.println("Collect Fine? (Yes/No)");
	            String payment = sc.next();

	            if (payment.equalsIgnoreCase("yes")) {

	                System.out.println("Fine collected successfully");
	                System.out.println("Amount Paid: ₹" + fine);

	            } else {

	                System.out.println("Fine payment pending");
	            }

	        } else {

	            System.out.println("No fine for this user");
	        }

	    } else {

	        System.out.println("No due date found for this user");
	    }
	}
	
	public void dueDateReminder() {

	    System.out.println("Enter User Id ");
	    int userId = sc.nextInt();

	    String userKey = String.valueOf(userId);

	    if (dueDates.containsKey(userKey)) {

	        LocalDate dueDate = dueDates.get(userKey);
	        LocalDate today = LocalDate.now();

	        if (today.isAfter(dueDate)) {

	            long overdueDays = java.time.temporal.ChronoUnit.DAYS
	                    .between(dueDate, today);

	            System.out.println("Book is overdue by "
	                    + overdueDays + " days");

	        } else if (today.isEqual(dueDate)) {

	            System.out.println("Reminder: Book is due today");

	        } else {

	            long remainingDays = java.time.temporal.ChronoUnit.DAYS
	                    .between(today, dueDate);

	            if (remainingDays <= 2) {

	                System.out.println("Reminder: Book is due in "
	                        + remainingDays + " days");

	            } else {

	                System.out.println("Book is due in "
	                        + remainingDays + " days");
	            }
	        }

	    } else {

	        System.out.println("No due date found for this user");
	    }
	}
	
	public void generateReport() {

	    int availableBooks = 0;
	    int issuedBookCount = 0;

	    Iterator<Book> ite = books.iterator();

	    while (ite.hasNext()) {

	        Book currentBook = ite.next();

	        if (currentBook.isAvailable()) {
	            availableBooks++;
	        } else {
	            issuedBookCount++;
	        }
	    }

	    System.out.println("========== LIBRARY REPORT ==========");

	    System.out.println("Total Books      : " + books.size());
	    System.out.println("Available Books  : " + availableBooks);
	    System.out.println("Issued Books     : " + issuedBookCount);
	    System.out.println("Total Librarians : " + librarians.size());

	    System.out.println("\n----- Issued Book Details -----");

	    if (issuedBooks.isEmpty()) {

	        System.out.println("No books are currently issued");

	    } else {

	        for (Map.Entry<String, Book> entry : issuedBooks.entrySet()) {

	            String userKey = entry.getKey();
	            Book book = entry.getValue();

	            System.out.println("User ID   : " + userKey);
	            System.out.println("Book      : " + book);

	            if (dueDates.containsKey(userKey)) {
	                System.out.println("Due Date  : " + dueDates.get(userKey));
	            }

	            System.out.println("--------------------------------");
	        }
	    }

	    System.out.println("====================================");
	}

}
