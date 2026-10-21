
package student;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        if (StudentManagementSystem.con == null) {
            System.out.println("Database connection failed!");
            return;
        }

        int choice = 0;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Student Registration");
            System.out.println("2. Course Management");
            System.out.println("3. Course Enrollment");
            System.out.println("4. Attendance Management");
            System.out.println("5. Marks Entry");
            System.out.println("6. Generate Result");
            System.out.println("7. View Tables");
            System.out.println("8. Count Students Above 80%");
            System.out.println("9. Count Present and Absent");
            System.out.println("10. Students Order By Name and Marks");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {

                    case 1:
                        StudentManagementSystem.StudentRegistration();
                        break;

                    case 2:
                        StudentManagementSystem.addCourse();
                        break;

                    case 3:
                        StudentManagementSystem.courseEnrollment();
                        break;

                    case 4:
                        StudentManagementSystem.manageAttendance();
                        break;

                    case 5:
                        StudentManagementSystem.enterMarks();
                        break;

                    case 6:
                        StudentManagementSystem.generateResult();
                        break;

                    case 7:
                        int tableChoice = -1;

                        do {
                            System.out.println("\n========== VIEW TABLES ==========");
                            System.out.println("1. View Students Table");
                            System.out.println("2. View Courses Table");
                            System.out.println("3. View Enrollments Table");
                            System.out.println("4. View Attendance Table");
                            System.out.println("5. View Marks Table");
                            System.out.println("0. Back to Main Menu");
                            System.out.print("Enter your choice: ");

                            tableChoice = Integer.parseInt(
                                    sc.nextLine().trim());

                            switch (tableChoice) {

                                case 1:
                                    StudentManagementSystem.viewStudents();
                                    break;

                                case 2:
                                    StudentManagementSystem.viewCourses();
                                    break;

                                case 3:
                                    StudentManagementSystem.viewEnrollments();
                                    break;

                                case 4:
                                    StudentManagementSystem.viewAttendance();
                                    break;

                                case 5:
                                    StudentManagementSystem.viewMarks();
                                    break;

                                case 0:
                                    System.out.println(
                                            "Returning to main menu...");
                                    break;

                                default:
                                    System.out.println(
                                            "Invalid table choice.");
                            }

                        } while (tableChoice != 0);

                        break;

                    case 8:
                        StudentManagementSystem.countMarksAbove80();
                        break;

                    case 9:
                        StudentManagementSystem.countPresentAbsent();
                        break;

                    case 10:
                        StudentManagementSystem
                                .viewStudentsOrderByNameMarks();
                        break;

                    case 0:
                        System.out.println(
                                "Exiting Student Management System...");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 0);

        try {
            StudentManagementSystem.con.close();
        } catch (SQLException e) {
            System.out.println(
                    "Error closing connection: " + e.getMessage());
        }

        sc.close();
    }
}