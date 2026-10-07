package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.ArrayList;

public class CourseService {
    private final ArrayList<Course> courses = new ArrayList<>();

    public Course addCourse(String name, String description, int durationInWeeks) {
        Course course = new Course(IdGenerator.getNextCourseId(), name, description, durationInWeeks);
        courses.add(course);
        return course;
    }

    public Course findCourseById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new EntityNotFoundException("Course", id);
    }

    public void setCourseActive(int id, boolean active) {
        findCourseById(id).setActive(active);
    }

    public ArrayList<Course> listCourses() {
        return new ArrayList<>(courses);
    }
}