package com.khun.studentregistration.repository;

import com.khun.studentregistration.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education,Long> {
}
