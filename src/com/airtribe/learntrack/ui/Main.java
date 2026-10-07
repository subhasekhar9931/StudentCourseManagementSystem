package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService =
            new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n===== LearnTrack =====");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("0. Exit");
            String choice = prompt("Choose an option: ");

            try {
                switch (choice) {
                    case "1": studentMenu(); break;
                    case "2": courseMenu(); break;
                    case "3": enrollmentMenu(); break;
                    case "0": running = false; break;
                    default: System.out.println("Invalid option. Please try again.");
                }
            } catch (InvalidInputException | EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }

    // ---------- Student menu ----------
    private static void studentMenu() {
        System.out.println("\n-- Students --");
        System.out.println("1. Add student");
        System.out.println("2. View all students");
        System.out.println("3. Search student by ID");
        System.out.println("4. Deactivate student");
        String choice = prompt("Choose an option: ");

        switch (choice) {
            case "1": addStudent(); break;
            case "2": printStudents(); break;
            case "3":
                int id = readInt("Student ID: ");
                System.out.println(studentService.findStudentById(id));
                break;
            case "4":
                studentService.deactivateStudent(readInt("Student ID: "));
                System.out.println("Student deactivated.");
                break;
            default: System.out.println("Invalid option.");
        }
    }

    private static void addStudent() {
        String first = InputValidator.requireNonEmpty(prompt("First name: "), "First name");
        String last = InputValidator.requireNonEmpty(prompt("Last name: "), "Last name");
        String batch = InputValidator.requireNonEmpty(prompt("Batch: "), "Batch");
        String email = prompt("Email (press Enter to skip): ");

        Student student;
        if (email.trim().isEmpty()) {
            student = studentService.addStudent(first, last, batch);
        } else {
            student = studentService.addStudent(first, last, InputValidator.requireEmail(email), batch);
        }
        System.out.println("Added: " + student);
    }

    private static void printStudents() {
        if (studentService.listStudents().isEmpty()) {
            System.out.println("No students yet.");
            return;
        }
        for (Student s : studentService.listStudents()) {
            System.out.println(s);
        }
    }

    // ---------- Course menu ----------
    private static void courseMenu() {
        System.out.println("\n-- Courses --");
        System.out.println("1. Add course");
        System.out.println("2. View all courses");
        System.out.println("3. Activate course");
        System.out.println("4. Deactivate course");
        String choice = prompt("Choose an option: ");

        switch (choice) {
            case "1":
                String name = InputValidator.requireNonEmpty(prompt("Course name: "), "Course name");
                String desc = prompt("Description: ");
                int weeks = readInt("Duration (weeks): ");
                System.out.println("Added: " + courseService.addCourse(name, desc, weeks));
                break;
            case "2":
                if (courseService.listCourses().isEmpty()) {
                    System.out.println("No courses yet.");
                }
                for (Course c : courseService.listCourses()) {
                    System.out.println(c);
                }
                break;
            case "3":
                courseService.setCourseActive(readInt("Course ID: "), true);
                System.out.println("Course activated.");
                break;
            case "4":
                courseService.setCourseActive(readInt("Course ID: "), false);
                System.out.println("Course deactivated.");
                break;
            default: System.out.println("Invalid option.");
        }
    }

    // ---------- Enrollment menu ----------
    private static void enrollmentMenu() {
        System.out.println("\n-- Enrollments --");
        System.out.println("1. Enroll student in course");
        System.out.println("2. View enrollments for a student");
        System.out.println("3. Mark enrollment completed");
        System.out.println("4. Mark enrollment cancelled");
        String choice = prompt("Choose an option: ");

        switch (choice) {
            case "1":
                int studentId = readInt("Student ID: ");
                int courseId = readInt("Course ID: ");
                System.out.println("Enrolled: " + enrollmentService.enroll(studentId, courseId));
                break;
            case "2":
                int id = readInt("Student ID: ");
                if (enrollmentService.getEnrollmentsForStudent(id).isEmpty()) {
                    System.out.println("No enrollments for this student.");
                }
                for (Enrollment e : enrollmentService.getEnrollmentsForStudent(id)) {
                    System.out.println(e);
                }
                break;
            case "3":
                enrollmentService.updateStatus(readInt("Enrollment ID: "), Enrollment.COMPLETED);
                System.out.println("Marked as completed.");
                break;
            case "4":
                enrollmentService.updateStatus(readInt("Enrollment ID: "), Enrollment.CANCELLED);
                System.out.println("Marked as cancelled.");
                break;
            default: System.out.println("Invalid option.");
        }
    }

    // ---------- Input helpers ----------
    private static String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static int readInt(String message) {
        return InputValidator.parseInt(prompt(message), "Value");
    }
}