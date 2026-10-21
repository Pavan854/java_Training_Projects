package student;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class StudentManagementSystem {
	
	static Scanner sc=new Scanner(System.in);
	
	static Connection con=Database_connection.getConnection();
	
	public static void StudentRegistration() {
		String query="""
				insert into students(name,email,phone,registration_date)values(?,?,?,?)
				""";
		System.out.println("Enter Students Name :");
		String name=sc.nextLine();
		System.out.println("Enter Students Email");
		String email=sc.next();
		System.out.println("Enter Students Mobile NUmber");
		String phone=sc.next();
		System.out.println("Enter Registration Date(yyyy-mm-dd)");
		LocalDate registration_date=LocalDate.parse(sc.next());
		try(PreparedStatement pst=con.prepareStatement(query)) {
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, phone);
			pst.setDate(4, Date.valueOf(registration_date));
			int rows=pst.executeUpdate();
			System.out.println("Sucessfully added  "+rows+" row");
		}catch(SQLException e) {
			System.out.println("Rows not added");
			e.printStackTrace();
		}

	}
	
    public static void addCourse() {
        String query = """
                INSERT INTO courses (course_name, duration)
                VALUES (?, ?)
                """;

        System.out.println("Enter course name:");
        String courseName = sc.nextLine();

        System.out.println("Enter course duration:");
        String duration = sc.nextLine();

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, courseName);
            pst.setString(2, duration);

            int rows = pst.executeUpdate();
            System.out.println(rows + " course added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public static void courseEnrollment() {
        String query = """
                INSERT INTO enrollments
                (student_id, course_id, enrollment_date, status)
                VALUES (?, ?, ?, ?)
                """;

        try {
            System.out.println("Enter student ID:");
            int studentId = sc.nextInt();

            System.out.println("Enter course ID:");
            int courseId = sc.nextInt();
            
            sc.nextLine();
            System.out.println("Enter enrollment date (yyyy-mm-dd):");
            LocalDate date = LocalDate.parse(sc.nextLine());

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, studentId);
                pst.setInt(2, courseId);
                pst.setDate(3, Date.valueOf(date));
                pst.setString(4, "Active");

                int rows = pst.executeUpdate();
                System.out.println(rows + " enrollment completed successfully.");
            }

        } catch (SQLException e) {
            System.out.println(
                "Enrollment failed. Check whether the student and course IDs exist."
            );
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ID or date format.");
        }
    }
    
    public static void manageAttendance() {
        String query = """
                INSERT INTO attendance
                (student_id, course_id, attendance_date, status)
                VALUES (?, ?, ?, ?)
                """;

        System.out.println("Enter Student ID:");
        int studentId = sc.nextInt();

        System.out.println("Enter Course ID:");
        int courseId = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Attendance Date (yyyy-mm-dd):");
        LocalDate date = LocalDate.parse(sc.nextLine());

        System.out.println("Enter Status (Present/Absent):");
        String status = sc.nextLine();

        if (!status.equalsIgnoreCase("Present")
                && !status.equalsIgnoreCase("Absent")) {
            System.out.println("Invalid status. Enter Present or Absent.");
            return;
        }

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            pst.setDate(3, Date.valueOf(date));
            pst.setString(4, status);

            int rows = pst.executeUpdate();
            System.out.println(rows + " attendance record added successfully.");

        } catch (SQLException e) {
            System.out.println("Attendance record was not added.");
            e.printStackTrace();
        }
    }
    
    public static void enterMarks() {
        String query = """
                INSERT INTO marks
                (student_id, course_id, exam_name, marks, total_marks)
                VALUES (?, ?, ?, ?, ?)
                """;

        System.out.println("Enter Student ID:");
        int studentId = sc.nextInt();

        System.out.println("Enter Course ID:");
        int courseId = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Exam Name:");
        String examName = sc.nextLine();

        System.out.println("Enter Obtained Marks:");
        double marks = sc.nextDouble();

        System.out.println("Enter Total Marks:");
        double totalMarks = sc.nextDouble();

        if (totalMarks <= 0 || marks < 0 || marks > totalMarks) {
            System.out.println("Invalid marks. Check the entered values.");
            return;
        }

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            pst.setString(3, examName);
            pst.setDouble(4, marks);
            pst.setDouble(5, totalMarks);

            int rows = pst.executeUpdate();
            System.out.println(rows + " marks record added successfully.");

        } catch (SQLException e) {
            System.out.println("Marks were not added.");
            e.printStackTrace();
        }
    }
    
    public static void viewStudents() {
        String query = "SELECT * FROM students";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
        	System.out.printf("| %-5s | %-20s | %-30s | %-15s | %-15s| %n",
                    "ID", "Name", "Email", "Phone", "Reg Date");
            System.out.println("-".repeat(97));

            while (rs.next()) {
            	
            	System.out.printf("| %-5d | %-20s | %-30s | %-15s | %-15s| %n",
            			rs.getInt("student_id"),rs.getString("name"),
            			rs.getString("email"),rs.getString("phone"),
            			rs.getDate("registration_date"));
            	

            }
            System.out.println("-".repeat(97));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void viewCourses() throws SQLException {
        String query = "SELECT * FROM courses";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.printf("%-10s %-30s %-20s%n",
                    "Course ID", "Course Name", "Duration");
            System.out.println("-".repeat(60));

            while (rs.next()) {
                System.out.printf("%-10d %-30s %-20s%n",
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("duration"));
            }
        }
    }
    public static void viewEnrollments() throws SQLException {
        String query = "SELECT * FROM enrollments";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.printf("%-15s %-12s %-10s %-18s %-12s%n",
                    "Enrollment ID", "Student ID", "Course ID",
                    "Enroll Date", "Status");
            System.out.println("-".repeat(75));

            while (rs.next()) {
                System.out.printf("%-15d %-12d %-10d %-18s %-12s%n",
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date"),
                        rs.getString("status"));
            }
        }
    }
    public static void viewAttendance() throws SQLException {
        String query = "SELECT * FROM attendance";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.printf("%-15s %-12s %-10s %-18s %-12s%n",
                    "Attendance ID", "Student ID", "Course ID",
                    "Attendance Date", "Status");
            System.out.println("-".repeat(75));

            while (rs.next()) {
                System.out.printf("%-15d %-12d %-10d %-18s %-12s%n",
                        rs.getInt("attendance_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("attendance_date"),
                        rs.getString("status"));
            }
        }
    }
    
    public static void viewMarks() throws SQLException {
        String query = "SELECT * FROM marks";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.printf("%-10s %-12s %-10s %-20s %-12s %-12s%n",
                    "Marks ID", "Student ID", "Course ID","Exam Name", "Marks", "Total");
            System.out.println("-".repeat(85));

            while (rs.next()) {
                System.out.printf("%-10d %-12d %-10d %-20s %-12s %-12s%n",
                        rs.getInt("marks_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getString("exam_name"),
                        rs.getBigDecimal("marks"),
                        rs.getBigDecimal("total_marks"));
            }
        }
    }
    
    public static void generateResult() throws SQLException {

        String query = """
                select s.student_id, s.name, c.course_name,
                       m.exam_name, m.marks, m.total_marks
                from marks m
                join students s on m.student_id = s.student_id
                join courses c on m.course_id = c.course_id
                order by s.student_id, c.course_id
                """;

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.printf("| %-10s | %-20s | %-20s | %-15s | %-12s | %-12s | %-12s | %-8s|%n",
                    "Student ID", "Student Name", "Course",
                    "Exam", "Marks", "Total", "Percentage", "Grade");

            System.out.println("-".repeat(125));

            boolean found = false;

            while (rs.next()) {
                found = true;

                double marks = rs.getDouble("marks");
                double totalMarks = rs.getDouble("total_marks");
                double percentage = (marks / totalMarks) * 100;

                String grade;

                if (percentage >= 90) {
                    grade = "A+";
                } else if (percentage >= 80) {
                    grade = "A";
                } else if (percentage >= 70) {
                    grade = "B";
                } else if (percentage >= 60) {
                    grade = "C";
                } else if (percentage >= 40) {
                    grade = "D";
                } else {
                    grade = "F";
                }

                System.out.printf("| %-10d | %-20s | %-20s | %-15s | %-12s | %-12s | %-12.2f | %-8s|%n",
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("course_name"),
                        rs.getString("exam_name"),
                        rs.getDouble("marks"),
                        rs.getDouble("total_marks"),
                        percentage,
                        grade);
            }

            if (!found) {
                System.out.println("No result records found. Enter marks first.");
            }
        }
    }
    

public static void updateStudent() {

    String query = "update students set name = ?, email = ?, phone = ? where student_id = ?";

    try (PreparedStatement ps = con.prepareStatement(query)) {

        System.out.print("Enter Student ID to update: ");
        int studentId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new email: ");
        String email = sc.nextLine();

        System.out.print("Enter new phone: ");
        String phone = sc.nextLine();

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, phone);
        ps.setInt(4, studentId);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student ID not found.");
        }

    } catch (SQLException e) {
        System.out.println("Error updating student: " + e.getMessage());
    }
}



public static void deleteStudentAndRelatedRecords() {

    System.out.print("Enter Student ID to delete: ");
    int studentId = sc.nextInt();
    sc.nextLine();

    try {

        con.setAutoCommit(false);

        
        try (PreparedStatement ps = con.prepareStatement(
                "DELETE FROM enrollments WHERE student_id = ?")) {
            ps.setInt(1, studentId);
            ps.executeUpdate();
        }

        
        try (PreparedStatement ps = con.prepareStatement(
                "DELETE FROM attendance WHERE student_id = ?")) {
            ps.setInt(1, studentId);
            ps.executeUpdate();
        }

        // Delete marks records
        try (PreparedStatement ps = con.prepareStatement(
                "DELETE FROM marks WHERE student_id = ?")) {
            ps.setInt(1, studentId);
            ps.executeUpdate();
        }

        
        int rows;
        try (PreparedStatement ps = con.prepareStatement(
                "DELETE FROM students WHERE student_id = ?")) {
            ps.setInt(1, studentId);
            rows = ps.executeUpdate();
        }

        if (rows > 0) {
            con.commit();
            System.out.println(
                "Student and related records deleted successfully!");
        } else {
            con.rollback();
            System.out.println("Student ID not found.");
        }

    } catch (SQLException e) {

        try {
            con.rollback();
            System.out.println("Transaction rolled back.");
        } catch (SQLException se) {
            System.out.println( se.getMessage());
        }

        System.out.println("Error: " + e.getMessage());

    } finally {
        try {
            con.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
    }
}
public static void countMarksAbove80() throws SQLException {

    String query = """
            SELECT COUNT(student_id) AS total_students
            FROM marks
            WHERE (marks / total_marks) * 100 > 80
            """;

    try (Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(query)) {

        if (rs.next()) {
            System.out.println("Students who scored above 80%: "
                    + rs.getInt("total_students"));
        }
    }
}

public static void countPresentAbsent() throws SQLException {

    String query = """
            SELECT status, COUNT(*) AS total
            FROM attendance
            GROUP BY status
            """;

    try (Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(query)) {

        int presentCount = 0;
        int absentCount = 0;

        while (rs.next()) {
            String status = rs.getString("status");
            int total = rs.getInt("total");

            if (status.equalsIgnoreCase("Present")) {
                presentCount = total;
            } else if (status.equalsIgnoreCase("Absent")) {
                absentCount = total;
            }
        }

        System.out.println("Present Count: " + presentCount);
        System.out.println("Absent Count: " + absentCount);
    }
}

public static void viewStudentsOrderByNameMarks() throws SQLException {

    String query = """
            SELECT s.name, m.marks
            FROM students s
            JOIN marks m ON s.student_id = m.student_id
            ORDER BY s.name , m.marks DESC
            """;

    try (Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(query)) {

        System.out.printf("%-25s %-10s%n", "Student Name", "Marks");
        System.out.println("-".repeat(35));

        while (rs.next()) {
            System.out.printf("%-25s %-10.2f%n",
                    rs.getString("name"),
                    rs.getDouble("marks"));
        }
    }
}



}
