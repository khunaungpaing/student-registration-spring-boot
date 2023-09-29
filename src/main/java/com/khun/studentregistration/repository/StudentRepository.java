package com.khun.studentregistration.repository;

import com.khun.studentregistration.entity.Course;
import com.khun.studentregistration.entity.Student;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends DataTablesRepository<Student,String> {
  Student findByCode(String code);
}
