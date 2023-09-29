package com.khun.studentregistration.repository;

import com.khun.studentregistration.entity.Course;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends DataTablesRepository<Course,Long> {
    Course findByName(String name);
    List<Course> findCoursesByActiveIsTrue();
}
