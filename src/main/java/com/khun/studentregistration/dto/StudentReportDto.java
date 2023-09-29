package com.khun.studentregistration.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentReportDto {
    private String code;
    private String name;
    private String gender;
    private String phone;
    private String educationName;
    private String imgUrl;
    private String courseName;

}
