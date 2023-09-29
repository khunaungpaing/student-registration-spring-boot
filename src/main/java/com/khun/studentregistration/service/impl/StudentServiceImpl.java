package com.khun.studentregistration.service.impl;

import com.khun.studentregistration.entity.Course;
import com.khun.studentregistration.entity.Education;
import com.khun.studentregistration.entity.Student;
import com.khun.studentregistration.entity.User;
import com.khun.studentregistration.repository.CourseRepository;
import com.khun.studentregistration.repository.EducationRepository;
import com.khun.studentregistration.repository.StudentRepository;
import com.khun.studentregistration.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
 public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final EducationRepository educationRepository;

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student getStudent(String code) {
        return studentRepository.findByCode(code);
    }

    @Override
    public List<Education> getEducations() {
        return educationRepository.findAll();
    }

    @Override
    public Long getCount() {
        return studentRepository.count();
    }

    @Override
    public DataTablesOutput<Student> getStudents(DataTablesInput input) {
        return studentRepository.findAll(input);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
