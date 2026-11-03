
package com.example.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.student.exception.StudentNotFoundException;
import com.example.student.model.Student;

@Service
public class StudentService {

    private List<Student> studentList = new ArrayList<>();

    private int nextId = 101;

  
    public List<Student> showStudents() {
        return studentList;
    }


    public Student searchById(int id) {

        for (Student student : studentList) {
            if (student.getStudentId() == id) {
                return student;
            }
        }

        throw new StudentNotFoundException(
                "Student not found with ID: " + id);
    }

    
    public Student searchByName(String name) {

        for (Student student : studentList) {
            if (student.getStudentName().equalsIgnoreCase(name)) {
                return student;
            }
        }

        throw new StudentNotFoundException(
                "Student not found: " + name);
    }

    public Student addStudent(Student student) {

        student.setStudentId(nextId++);

        studentList.add(student);

        return student;
    }


    public Student updateStudent(int id, Student newStudent) {

        Student oldStudent = searchById(id);

        oldStudent.setStudentName(newStudent.getStudentName());
        oldStudent.setEmail(newStudent.getEmail());
        oldStudent.setCourse(newStudent.getCourse());
        oldStudent.setFee(newStudent.getFee());
        oldStudent.setEnrollmentStatus(
                newStudent.getEnrollmentStatus());
        oldStudent.setDuration(newStudent.getDuration());

        return oldStudent;
    }


    public String deleteStudent(int id) {

        Student student = searchById(id);

        studentList.remove(student);

        return "Student deleted successfully";
    }

    public Student patchStudent(int id, Student newStudent) {

        Student oldStudent = searchById(id);

        if (newStudent.getStudentName() != null) {
            oldStudent.setStudentName(newStudent.getStudentName());
        }

        if (newStudent.getEmail() != null) {
            oldStudent.setEmail(newStudent.getEmail());
        }

        if (newStudent.getCourse() != null) {
            oldStudent.setCourse(newStudent.getCourse());
        }

        if (newStudent.getFee() != null) {
            oldStudent.setFee(newStudent.getFee());
        }

        if (newStudent.getEnrollmentStatus() != null) {
            oldStudent.setEnrollmentStatus(
                    newStudent.getEnrollmentStatus());
        }

        if (newStudent.getDuration() != null) {
            oldStudent.setDuration(newStudent.getDuration());
        }

        return oldStudent;
    }
}
