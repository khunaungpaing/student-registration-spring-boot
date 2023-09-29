package com.khun.studentregistration.api;

import com.khun.studentregistration.dto.CourseReportDto;
import com.khun.studentregistration.entity.Course;
import com.khun.studentregistration.entity.User;
import com.khun.studentregistration.service.CourseService;
import com.khun.studentregistration.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/")
@Slf4j
public class CourseAPI {

    private final CourseService courseService;
    private final ReportService reportService;
    @GetMapping("fetch-courses")
    public DataTablesOutput<Course> getCourses(@Valid DataTablesInput input) {
        return courseService.getCourses(input);
    }

    @PostMapping("/toggle-course-active/{id}")
    public ResponseEntity<String> toggleUserActive(@PathVariable Long id, @RequestParam Boolean status) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        course.setActive(status);
        courseService.saveCourse(course);

        return ResponseEntity.ok("User status toggled successfully");
    }

    @GetMapping("/export-course-report-pdf")
    public void exportPdfFile(HttpServletResponse response) {
        log.info("generating pdf report ........");
        List<CourseReportDto> courseReportDtoList = courseService.getAllCourses().stream().map(Course::mapToDto).toList();
        reportService.generatePdf(response,courseReportDtoList,"course_report","course-report");
    }

    @GetMapping("/export-course-report-xlsx")
    public void exportExcelFile(HttpServletResponse response) {
        log.info("generating xlsx report ........");
        List<CourseReportDto> courseReportDtoList = courseService.getAllCourses().stream().map(Course::mapToDto).toList();
        reportService.generateExcel(response,courseReportDtoList,"course_report","course-report");
    }
}