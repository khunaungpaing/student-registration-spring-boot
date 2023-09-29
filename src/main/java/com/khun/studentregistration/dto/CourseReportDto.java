package com.khun.studentregistration.dto;

import com.khun.studentregistration.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseReportDto {
    private long id;
    private String code;
    private String name;
    private String description;
    private String teacherName;

}
