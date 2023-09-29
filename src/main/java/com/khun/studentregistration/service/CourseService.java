package com.khun.studentregistration.service;

import com.khun.studentregistration.entity.Course;
import com.khun.studentregistration.entity.User;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface CourseService {
    void saveCourse(Course course);
    void addUserToCourse(String courseName, String username);
    Course getCourse(String courseName);
    Long getCount();
    DataTablesOutput<Course> getCourses(DataTablesInput input);

    List<Course> getAvailableCourses();

    List<Course> getAllCourses();

    Course getCourseById(Long id);
}
