package com.example.demo.resource;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/students")
public class Controller {
    private HashMap<Integer, Student> map = new HashMap<>();
    private int id = 1;

    @GetMapping
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        for (Student student : map.values()) {
            students.add(student);
        }
        if (students.isEmpty()) {
            throw new RuntimeException("Students not found");
        }
        return students;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        Student student = map.get(id);
        if (student == null) {
            throw new RuntimeException("Not found student by id");
        }
        return student;
    }

    @GetMapping("/uni/{uni}")
    public List<Student> getStudentByUniversity(@PathVariable String uni) {
        List<Student> students = new ArrayList<>();
        for (Student student : map.values()) {
            if (student.getUniversity().equalsIgnoreCase(uni)) {
                students.add(student);
            }
        }
        return students;
    }

    @PostMapping
    public String createStudent(@RequestBody Student student) {
        student.setId(id);
        map.put(id, student);
        id++;
        return "Student Added";
    }

}

@Getter
@Setter
@AllArgsConstructor
class Student {
    private String name;
    private String university;
    private String Adhar;
    private int age;
    private int id;
}