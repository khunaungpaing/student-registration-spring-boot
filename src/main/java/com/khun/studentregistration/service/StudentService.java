package com.khun.studentregistration.service;

import com.khun.studentregistration.entity.Course;
import com.khun.studentregistration.entity.Education;
import com.khun.studentregistration.entity.Student;
import com.khun.studentregistration.entity.User;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    void saveStudent(Student student);
    Student getStudent(String code);
    List<Education> getEducations();
    Long getCount();
    DataTablesOutput<Student> getStudents(DataTablesInput input);

    List<Student> getAllStudents();
}
