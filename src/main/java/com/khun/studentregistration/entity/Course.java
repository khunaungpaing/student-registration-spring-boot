package com.khun.studentregistration.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.khun.studentregistration.dto.CourseReportDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    private boolean active=true;

    @ManyToOne(fetch = FetchType.EAGER)
    private User teacher;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Student> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public CourseReportDto mapToDto(){
        return new CourseReportDto(id,code,name,description, teacher.getName());
    }
}