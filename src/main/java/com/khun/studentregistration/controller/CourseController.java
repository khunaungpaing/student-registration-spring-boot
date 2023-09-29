package com.khun.studentregistration.controller;

import com.khun.studentregistration.entity.Course;
import com.khun.studentregistration.entity.User;
import com.khun.studentregistration.service.CourseService;
import com.khun.studentregistration.service.UserService;
import com.khun.studentregistration.utils.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;

    @GetMapping("/list")
    public String courseList() {
        return "course-list";
    }

    @GetMapping("/add")
    public ModelAndView courseAddView(ModelMap model) {
        Long count = courseService.getCount();
        Course course = new Course();
        course.setCode(String.format("COU%04d", count + 1));
        List<User> teachers = userService.getUsersByRole(Roles.ROLE_TEACHER.toString());
        for (User teacher : teachers) {
            System.out.println(teacher.getUsername());
        }
        model.addAttribute("teachers", teachers);
        return new ModelAndView("course-add", "course", course);
    }

    @PostMapping("/add")
    public String courseAdd(@ModelAttribute("course") Course course, RedirectAttributes redirectAttributes) {
        try {
            courseService.saveCourse(course);
            redirectAttributes.addFlashAttribute("success", "courseAddSuccess");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "courseDupe");
            return "redirect:/course/add";
        }

        return "redirect:/course/list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView courseEditView(@PathVariable Long id, ModelMap model) {
        Course course = courseService.getCourseById(id);
        List<User> teachers = userService.getUsersByRole("TEACHER");
        model.addAttribute("teachers", teachers);
        return new ModelAndView("course-edit", "course", course);
    }

    @PostMapping("/edit")
    public String courseEdit(@ModelAttribute Course course, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        try {
            courseService.saveCourse(course);
            redirectAttributes.addFlashAttribute("success", "courseEditSuccess");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "courseDupe");
            return "redirect:/course/edit/" + course.getId();
        }
        return "redirect:/course/list";
    }
}
