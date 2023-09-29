package com.khun.studentregistration.controller;

import com.khun.studentregistration.service.CourseService;
import com.khun.studentregistration.service.StudentService;
import com.khun.studentregistration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final CourseService courseService;
    private final StudentService studentService;
    @GetMapping("/")
    public String showHome(ModelMap model){
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(ModelMap model){
        long studentsCount = studentService.getCount();
        model.addAttribute("studentsCount", studentsCount);
        long teachersCount = userService.getTeacherCount();
        model.addAttribute("teachersCount", teachersCount);
        long coursesCount = courseService.getCount();
        model.addAttribute("coursesCount", coursesCount);
        return "index";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "pages-error-404";
    }
}
