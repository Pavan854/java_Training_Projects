package student;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Table_creation {
	
	public static Connection con=Database_connection.getConnection();
	

	public static void createStudentTable() {
		String stu_table="""
				create table if not exists students(
								student_id int primary key auto_increment,
								name varchar(50) not null,
								email varchar(40) unique  not null,
								phone varchar(15) not null,
								registration_date date not null)""";
		CreateTable(stu_table,"Students");
	}
	

	public static void createCourseTable() {
		String course_table="""
				create table if not exists courses(
								course_id int primary key auto_increment,
								course_name varchar(50) not null,
								duration varchar(30))""";
		CreateTable(course_table,"courses");
	}
	

	public static void createEnrollmentTable() {
		String Enrol_table="""
				create table if not exists Enrollments(
					enrollment_id int primary key auto_increment,
					student_id int not null,
					course_id int not null,
					enrollment_date date not null,
					status varchar(32),
					foreign key (student_id) references Students(student_id),
					foreign key (course_id) references courses(course_id)
				)""";
		CreateTable(Enrol_table,"Enrollments");
	}
	public static void createAttendanceTable() {

	    String attendance_table = """
	            CREATE TABLE IF NOT EXISTS attendance (
	                attendance_id INT PRIMARY KEY AUTO_INCREMENT,
	                student_id INT NOT NULL,
	                course_id INT NOT NULL,
	                attendance_date DATE NOT NULL,
	                status VARCHAR(10) NOT NULL,
	                FOREIGN KEY (student_id) REFERENCES students(student_id),
	                FOREIGN KEY (course_id) REFERENCES courses(course_id)
	            )
	            """;

	    CreateTable(attendance_table, "Attendance");
	}
	
	public static void createMarksTable() {
		String marks_table="""
				create table if not exists Marks(
					marks_id int primary key auto_increment,
					student_id int not null,
					course_id int not null,
					exam_name varchar(40) not null,
					marks decimal(5,2) not null,
					total_marks decimal(5,2) not null,
					foreign key (student_id) references students(student_id),
					foreign key (course_id) references courses(course_id)
				)""";
		CreateTable(marks_table,"Marks");
	}
	
	
	
	
	private static void CreateTable(String query,String tableName) {
		if(con==null) {
			System.out.println("Database connection failed");
			return;
		}
		try(Statement st=con.createStatement()){
			st.execute(query);
			System.out.println(tableName +" table is created");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
//	public static void main(String[] args) {
//		if(con==null) {
//			System.out.println("Connection failed");
//			return ;
//		}
//		
//		createStudentTable();
//		createCourseTable();
//		createEnrollmentTable();
//		createAttendanceTable();
//		createMarksTable();
//		
//	}


}
