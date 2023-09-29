package com.khun.studentregistration.api;


import com.khun.studentregistration.dto.CourseReportDto;
import com.khun.studentregistration.dto.StudentReportDto;
import com.khun.studentregistration.entity.Course;
import com.khun.studentregistration.entity.Student;
import com.khun.studentregistration.entity.User;
import com.khun.studentregistration.service.ReportService;
import com.khun.studentregistration.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
@Slf4j
public class StudentAPI {

    private final StudentService studentService;
    private final ReportService reportService;

    @GetMapping("/fetch-students")
    public DataTablesOutput<Student> getUsers(@Valid DataTablesInput input) {
        return studentService.getStudents(input);
    }


    @GetMapping("/export-student-report-pdf")
    public void exportPdfFile(HttpServletResponse response) {
        log.info("generating pdf report ........");
        List<StudentReportDto> studentReportDtoList = studentService.getAllStudents().stream().map(Student::mapToDto).toList();
        reportService.generatePdf(response,studentReportDtoList,"student_report","student-report");
    }

    @GetMapping("/export-student-report-xlsx")
    public void exportExcelFile(HttpServletResponse response) {
        log.info("generating xlsx report ........");
        List<StudentReportDto> studentReportDtoList = studentService.getAllStudents().stream().map(Student::mapToDto).toList();
        reportService.generateExcel(response,studentReportDtoList,"student_report","student-report");    }

//    @PostMapping("/add")
//    public ResponseEntity<String> addStudent(
//            @ModelAttribute("student") Student student,
//            @RequestParam("photo") MultipartFile file
//    ) {
//        try {
//            String path = "src/main/resources/static/images/";
//            String imgUrl = new FileService(file, path).create("/images/");
//            student.setImgUrl(imgUrl);
//            studentService.saveStudent(student);
//            return ResponseEntity.ok("success");
//        } catch (DataIntegrityViolationException | IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
//        }
//    }

//
//    @PostMapping("/toggle-user-active/{id}")
//    public ResponseEntity<String> toggleUserActive(@PathVariable Long id, @RequestParam Boolean status) {
//        User user = userService.getUserById(id);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        user.setEnabled(status);
//        userService.saveUser(user);
//
//        return ResponseEntity.ok("User status toggled successfully");
//    }

}