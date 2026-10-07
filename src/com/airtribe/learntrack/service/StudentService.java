package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.ArrayList;

public class StudentService {
    private final ArrayList<Student> students = new ArrayList<>();

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        Student student = new Student(IdGenerator.getNextStudentId(), firstName, lastName, email, batch);
        students.add(student);
        return student;
    }

    // Overloading: email optional
    public Student addStudent(String firstName, String lastName, String batch) {
        Student student = new Student(IdGenerator.getNextStudentId(), firstName, lastName, batch);
        students.add(student);
        return student;
    }

    public Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new EntityNotFoundException("Student", id);
    }

    public void updateStudent(int id, String firstName, String lastName, String email, String batch) {
        Student s = findStudentById(id);
        s.setFirstName(firstName);
        s.setLastName(lastName);
        s.setEmail(email);
        s.setBatch(batch);
    }

    public void deactivateStudent(int id) {
        findStudentById(id).setActive(false);
    }

    public void removeStudent(int id) {
        students.remove(findStudentById(id));
    }

    public ArrayList<Student> listStudents() {
        return new ArrayList<>(students);
    }
}