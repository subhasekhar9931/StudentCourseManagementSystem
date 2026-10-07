package com.airtribe.learntrack.entity;

public class Student extends Person{
     private String batch;
     private boolean active;

    // Constructor overloading: with and without email
    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    public Student(int id, String firstName, String lastName, String batch) {
        this(id, firstName, lastName, "not provided", batch);
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName()
    {
        return "Student's Name : " + getFirstName() + " " + getLastName();
    }

    @Override
    public String toString() {
        return "Student{id=" + getId() + ", name=" + getDisplayName()
                + ", email=" + getEmail() + ", active=" + active + "}";
    }
}
