package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.exception.InvalidInputException;

import java.time.LocalDate;

public class Enrollment {
    public static final String ACTIVE = "ACTIVE";
    public static final String COMPLETED = "COMPLETED";
    public static final String CANCELLED = "CANCELLED";

    private int id;
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;
    private String status;

    public Enrollment(int id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = LocalDate.now();
        this.status = ACTIVE;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) {
        if (!isValidStatus(status)) {
            throw new InvalidInputException(
                    "Invalid status: " + status + ". Allowed: ACTIVE, COMPLETED, CANCELLED.");
        }
        this.status = status;
    }

    public static boolean isValidStatus(String status) {
        return ACTIVE.equals(status)
                || COMPLETED.equals(status)
                || CANCELLED.equals(status);
    }

    @Override
    public String toString() {
        return "Enrollment{id=" + id + ", studentId=" + studentId + ", courseId=" + courseId
                + ", date=" + enrollmentDate + ", status=" + status + "}";
    }
}