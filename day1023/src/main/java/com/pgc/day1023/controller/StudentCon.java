package com.pgc.day1023.controller;


import com.pgc.day1023.domain.Student;
import com.pgc.day1023.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/students")
@Controller
@RequiredArgsConstructor
public class StudentCon {

    private final StudentService studentService;

    /**
     * [기존] 학생 목록 페이지 (GET /students)
     */
    @GetMapping
    public String showList(Model model) {
        List<Student> studentList = studentService.getAllStudents();
        model.addAttribute("studentList", studentList);
        return "student/list"; // (list.html)
    }

    // --- [신규 추가된 코드] ---

    /**
     * [신규] 학생 추가 폼 페이지 (GET /students/new)
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // 1. 폼(th:object)에서 사용할 비어있는 Student 객체를 모델에 담습니다.
        model.addAttribute("student", new Student());
        // 2. student/create.html 템플릿을 반환합니다.
        return "student/create";
    }

    /**
     * [신규] 학생 추가 처리 (POST /students/create)
     */
    @PostMapping("/create")
    public String processCreate(@ModelAttribute Student student) {
        // 1. @ModelAttribute가 폼 데이터를 Student 객체로 받아줍니다.
        // 2. Service를 호출하여 학생을 DB에 저장합니다.
        studentService.createStudent(student);
        // 3. 완료 후, 학생 목록 페이지(/students)로 리다이렉트합니다.
        return "redirect:/students";
    }
    // --- [여기까지 신규 추가] ---


    /**
     * [기존] 학생 수정 폼 페이지 (GET /students/edit/{id})
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student/edit"; // (edit.html)
    }

    /**
     * [기존] 학생 정보 수정 처리 (POST /students/update/{id})
     */
    @PostMapping("/update/{id}")
    public String processUpdate(@PathVariable Long id,
                                @ModelAttribute Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }

    /**
     * [기존] 학생 삭제 처리 (POST /students/delete/{id})
     */
    @PostMapping("/delete/{id}")
    public String processDelete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}