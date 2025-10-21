package com.pgc.excrud.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping; // [!] 임포트
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;  // [!] 임포트
import org.springframework.web.bind.annotation.PostMapping;  // [!] 임포트
import org.springframework.web.bind.annotation.PutMapping;   // [!] 임포트
import org.springframework.web.bind.annotation.RequestBody;  // [!] 임포트
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgc.excrud.domain.Student;
import com.pgc.excrud.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> list() {
        return studentService.getAllStudents();
    }

    // --- [!] 추가된 엔드포인트 ---

    /**
     * 학생 생성 (Create)
     * POST /api/students
     * Body: { "name": "이순신", "email": "lee@example.com", "age": 40 }
     */
    @PostMapping
    public Student create(@RequestBody Student student) {
        // 서비스에서 id가 채워진 student 객체를 반환합니다.
        return studentService.createStudent(student);
    }

    /**
     * 학생 수정 (Update)
     * PUT /api/students/1
     * Body: { "name": "이순신", "email": "lee@updated.com", "age": 41 }
     */
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id); // URL의 id를 객체에 설정
        int rowsAffected = studentService.updateStudent(student);
        if (rowsAffected > 0) {
            return "ID " + id + " updated successfully.";
        } else {
            return "ID " + id + " not found.";
        }
    }

    /**
     * 학생 삭제 (Delete)
     * DELETE /api/students/1
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        int rowsAffected = studentService.deleteStudent(id);
        if (rowsAffected > 0) {
            return "ID " + id + " deleted successfully.";
        } else {
            return "ID " + id + " not found.";
        }
    }
}