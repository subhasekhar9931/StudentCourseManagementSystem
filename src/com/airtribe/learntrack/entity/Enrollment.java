package com.airtribe.learntrack.entity;

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

    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Enrollment{id=" + id + ", studentId=" + studentId + ", courseId=" + courseId
                + ", date=" + enrollmentDate + ", status=" + status + "}";
    }
}