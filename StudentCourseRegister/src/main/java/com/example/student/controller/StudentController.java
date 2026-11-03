
package com.example.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.student.model.Student;
import com.example.student.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

   
    @GetMapping
    public List<Student> showStudents() {
        return service.showStudents();
    }

   
    @GetMapping("search/{id}")
    public Student searchById(@PathVariable int id) {
        return service.searchById(id);
    }

   
    @GetMapping("/search/{name}")
    public Student searchByName(@PathVariable String name) {
        return service.searchByName(name);
    }

    // 4. Add student
    @PostMapping("/insert")
    public Student addStudent(@Valid @RequestBody Student student) {
        return service.addStudent(student);
    }

    
    @PutMapping("update/{id}")
    public Student updateStudent(
            @PathVariable int id,
            @Valid @RequestBody Student student) {

        return service.updateStudent(id, student);
    }

   
    @DeleteMapping("delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        return service.deleteStudent(id);
    }

   
    @PatchMapping("partial/{id}")
    public Student patchStudent(
            @PathVariable int id,
            @RequestBody Student student) {

        return service.patchStudent(id, student);
    }
}
