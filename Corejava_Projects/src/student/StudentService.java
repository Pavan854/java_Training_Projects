package student;

import java.util.HashMap;
import java.util.Scanner;

public class StudentService {

    HashMap<Integer, Student> students = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    // 1. Add Student
    public void addStudent() {

        System.out.println("Enter Student ID:");
        int id = sc.nextInt();

        if (students.containsKey(id)) {
            System.out.println("Student ID already exists");
            return;
        }

        System.out.println("Enter Student Name:");
        String name = sc.next();

        System.out.println("Enter Student Email:");
        String email = sc.next();

        System.out.println("Enter Student Marks:");
        double marks = sc.nextDouble();

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks");
            return;
        }

        System.out.println("Enter Attendance:");
        int attendance = sc.nextInt();

        if (attendance < 0 || attendance > 100) {
            System.out.println("Invalid attendance");
            return;
        }

        // Course details
        System.out.println("Enter Course ID:");
        int courseId = sc.nextInt();

        System.out.println("Enter Course Name:");
        String courseName = sc.next();

        Course course = new Course(courseId, courseName);

        // Student status
        System.out.println("Enter Status (ACTIVE/INACTIVE/COMPLETED):");

        StudentStatus status;

        try {
            status = StudentStatus.valueOf(sc.next().toUpperCase());

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid status.\nPlease enter ACTIVE, INACTIVE, or COMPLETED."
            );

            return;
        }

        Student student =
                new Student(id, name, email, marks, attendance, status, course);

        students.put(id, student);

        System.out.println("Student added successfully");
    }

    // 2. Display All Students
    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No students available");
            return;
        }

        for (Student student : students.values()) {
            System.out.println(student);
        }
    }

    // 3. Search By Student ID
    public void searchByStudentId() {

        System.out.println("Enter Student ID:");
        int id = sc.nextInt();

        Student student = students.get(id);

        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found");
        }
    }

    // 4. Search By Name
    public void searchByName() {

        System.out.println("Enter Student Name:");
        String name = sc.next();

        boolean found = false;

        for (Student student : students.values()) {

            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found");
        }
    }

    // 5. Update Marks
    public void updateMarks() {

        System.out.println("Enter Student ID:");
        int id = sc.nextInt();

        Student student = students.get(id);

        if (student != null) {

            System.out.println("Enter New Marks:");
            double marks = sc.nextDouble();

            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks");
                return;
            }

            student.setMarks(marks);

            System.out.println("Marks updated successfully");

        } else {

            System.out.println("Student not found");
        }
    }

    // 6. Update Attendance
    public void updateAttendance() {

        System.out.println("Enter Student ID:");
        int id = sc.nextInt();

        Student student = students.get(id);

        if (student != null) {

            System.out.println("Enter New Attendance:");
            int attendance = sc.nextInt();

            if (attendance < 0 || attendance > 100) {
                System.out.println("Invalid attendance");
                return;
            }

            student.setAttendance(attendance);

            System.out.println("Attendance updated successfully");

        } else {

            System.out.println("Student not found");
        }
    }

    // 7. Calculate Grade
    public void calculateGrade() {

        System.out.println("Enter Student ID:");
        int id = sc.nextInt();

        Student student = students.get(id);

        if (student != null) {

            double marks = student.getMarks();

            if (marks >= 90) {
                System.out.println("Grade: A");

            } else if (marks >= 80) {
                System.out.println("Grade: B");

            } else if (marks >= 70) {
                System.out.println("Grade: C");

            } else if (marks >= 60) {
                System.out.println("Grade: D");

            } else if (marks >= 50) {
                System.out.println("Grade: E");

            } else {
                System.out.println("Grade: F");
            }

        } else {

            System.out.println("Student not found");
        }
    }

    // 8. Remove Student
    public void removeStudent() {

        System.out.println("Enter Student ID:");
        int id = sc.nextInt();

        Student removedStudent = students.remove(id);

        if (removedStudent != null) {
            System.out.println("Student removed successfully");

        } else {
            System.out.println("Student not found");
        }
    }

    // 9. Display Topper
    public void displayTopper() {

        if (students.isEmpty()) {
            System.out.println("No students available");
            return;
        }

        Student topper = null;

        for (Student student : students.values()) {

            if (topper == null ||
                    student.getMarks() > topper.getMarks()) {

                topper = student;
            }
        }

        System.out.println("===== TOPPER =====");
        System.out.println(topper);
    }
}