package com.khun.studentregistration.service.impl;

import com.khun.studentregistration.entity.Course;
import com.khun.studentregistration.entity.Role;
import com.khun.studentregistration.entity.User;
import com.khun.studentregistration.repository.CourseRepository;
import com.khun.studentregistration.repository.UserRepository;
import com.khun.studentregistration.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void addUserToCourse(String courseName, String userCode) {
        log.info("assign teacher {} to course {}",userCode,courseName);
        Course course = courseRepository.findByName(courseName);
        User user = userRepository.findByCode(userCode);
        course.setTeacher(user);
    }

    @Override
    public Course getCourse(String courseName) {
        return courseRepository.findByName(courseName);
    }

    @Override
    public Long getCount() {
        return courseRepository.count();
    }

    @Override
    public DataTablesOutput<Course> getCourses(DataTablesInput input) {
        return courseRepository.findAll(input);
    }

    @Override
    public List<Course> getAvailableCourses() {
        return courseRepository.findCoursesByActiveIsTrue();
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }
}
