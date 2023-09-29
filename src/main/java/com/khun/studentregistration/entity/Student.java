package com.khun.studentregistration.entity;


import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.khun.studentregistration.dto.StudentReportDto;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data

public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private Date dob;
    private String gender;
    private String phone;
    private boolean deleted=false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Education education;

    private String imgUrl;
    private String createdBy;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @JsonManagedReference
    private Set<Course> courses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public StudentReportDto mapToDto(){
        String filePath = "src/main/resources/static";
        String courseName = courses.stream()
                .map(Course::getName)
                .reduce((name1, name2) -> name1 + ", " + name2)
                .orElse("");
        return new StudentReportDto(code,name,gender,phone,education.getName(),filePath+imgUrl,courseName);
    }
}