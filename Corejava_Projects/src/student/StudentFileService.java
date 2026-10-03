package student;

import java.io.*;
import java.util.HashMap;

public class StudentFileService {

    // Save students to file
    public void saveStudents(HashMap<Integer, Student> students) {

        try (ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream("students.txt"))) {

            out.writeObject(students);

            System.out.println("Students saved successfully");

        } catch (IOException e) {
            System.out.println("Error while saving students");
            e.printStackTrace();
        }
    }

    // Load students from file
    public HashMap<Integer, Student> loadStudents() {

        try (ObjectInputStream in =new ObjectInputStream(new FileInputStream("students.txt"))) {

            HashMap<Integer, Student> students =(HashMap<Integer, Student>) in.readObject();

            System.out.println("Students loaded successfully");

            return students;

        } catch (FileNotFoundException e) {
            System.out.println("Student file not found");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading students");
            e.printStackTrace();
        }

        return new HashMap<>();
    }
}