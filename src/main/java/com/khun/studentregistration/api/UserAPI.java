package com.khun.studentregistration.api;

import com.khun.studentregistration.entity.User;
import com.khun.studentregistration.service.ReportService;
import com.khun.studentregistration.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/")
@Slf4j
public class UserAPI {

    private final UserService userService;
    private final ReportService reportService;

    @GetMapping("fetch-users")
    public DataTablesOutput<User> getUsers(@Valid DataTablesInput input) {
        return userService.getUsers(input);
    }


    @GetMapping("/export-user-report-pdf")
    public void exportPdfFile(HttpServletResponse response) {
        log.info("generating pdf report ........");
        reportService.generatePdf(response,userService.getAllUsers(),"user_report","user-report");
    }

    @GetMapping("/export-user-report-xlsx")
    public void exportExcelFile(HttpServletResponse response) {
        log.info("generating xlsx report ........");
        reportService.generateExcel(response,userService.getAllUsers(),"user_report","user-report");
    }

    @GetMapping("/save-and-export-user-report-html")
    public ResponseEntity<byte[]> exportHtmlFile(HttpServletResponse response) {
        log.info("generating html report ........");
        try {
            // Read the HTML file content
            String filePath = reportService.saveHtml(userService.getAllUsers(), "user_report", "user-report");
            File file = new File(filePath);
            byte[] fileContent = FileCopyUtils.copyToByteArray(file);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);
            headers.setContentDispositionFormData("attachment", "user-report.html");

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions here
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/show-preview")
//    @ResponseBody
//    public String showPreview() {
//        try {
//            List<User> data = userService.getAllUsers();
//            String pdfBase64 =  reportService.preview(data, "user_report");
//            return "data:application/pdf;base64," + pdfBase64;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error generating PDF preview.";
//        }
//    }


    @PostMapping("/toggle-user-active/{id}")
    public ResponseEntity<String> toggleUserActive(@PathVariable Long id, @RequestParam Boolean status) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setEnabled(status);
        userService.saveUser(user);

        return ResponseEntity.ok("User status toggled successfully");
    }

}