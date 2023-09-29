package com.khun.studentregistration.controller;

import com.khun.studentregistration.entity.Student;
import com.khun.studentregistration.service.CourseService;
import com.khun.studentregistration.service.StudentService;
import com.khun.studentregistration.service.impl.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final CourseService courseService;
    private final StudentService studentService;

    @GetMapping("/list")
    public String studentList() {
        return "student-list";
    }

    @GetMapping("/add")
    public ModelAndView studentAddView(ModelMap modelMap) {
        Student student = new Student();
        student.setCode(String.format("STU%04d",studentService.getCount()+1));
        modelMap.addAttribute("education",studentService.getEducations());
        modelMap.addAttribute("courses", courseService.getAvailableCourses());
        return new ModelAndView("student-add", "student", student);
    }

    // ... (your existing code)


    @PostMapping("/add")
    public String addStudent(
            @ModelAttribute("student") Student student,
            RedirectAttributes redirectAttributes,
            @RequestParam("photo") MultipartFile file
    ){
        try{
            String path = "src/main/resources/static/images/";
            String imgUrl = new FileService(file,path).uploadFile("/images/");
            student.setImgUrl(imgUrl);
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("success","studentAddSuccess");

        }   catch (DataIntegrityViolationException e){
            redirectAttributes.addFlashAttribute("error","studentDupeError");
            return "redirect:/student/add";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/student/list";
    }

    @GetMapping("/edit")
    public ModelAndView studentEditView() {
        return new ModelAndView("student-edit", "student", new Student());
    }

    @PostMapping("/edit")
    public String studentEdit(@ModelAttribute("student") Student student) {
        return "redirect:/student/list";
    }

    @GetMapping("/delete")
    public String deleteStudent() {
        return "redirect:/student/list";
    }
}
