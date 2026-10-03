package student;

public class StudentPortal {

    public static void main(String[] args) {

        StudentService service = new StudentService();
        StudentFileService fileService = new StudentFileService();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search By Student ID");
            System.out.println("4. Search By Name");
            System.out.println("5. Update Marks");
            System.out.println("6. Update Attendance");
            System.out.println("7. Calculate Grade");
            System.out.println("8. Remove Student");
            System.out.println("9. Display Topper");
            System.out.println("10. Save Students");
            System.out.println("11. Load Students");
            System.out.println("12. Exit");

            System.out.println("Enter your choice:");

            int choice = service.sc.nextInt();

            switch (choice) {

            case 1:
                service.addStudent();
                break;

            case 2:
                service.displayAllStudents();
                break;

            case 3:
                service.searchByStudentId();
                break;

            case 4:
                service.searchByName();
                break;

            case 5:
                service.updateMarks();
                break;

            case 6:
                service.updateAttendance();
                break;

            case 7:
                service.calculateGrade();
                break;

            case 8:
                service.removeStudent();
                break;

            case 9:
                service.displayTopper();
                break;

            case 10:
                fileService.saveStudents(service.students);
                break;

            case 11:
                fileService.loadStudents();
                break;

            case 12:
                System.out.println("Thank you!");
                System.exit(0);

            default:
                System.out.println("Invalid choice");
            }
        }
    }
}