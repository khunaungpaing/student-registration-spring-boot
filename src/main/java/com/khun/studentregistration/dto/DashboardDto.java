package com.khun.studentregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto {
    private long studentCount;
    private long courseCount;
    private long teacherCount;
}
