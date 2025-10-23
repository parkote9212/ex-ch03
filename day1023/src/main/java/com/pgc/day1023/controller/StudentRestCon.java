package com.pgc.day1023.controller;

import com.pgc.day1023.domain.Student;
import com.pgc.day1023.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentRestCon {

    private final StudentService studentService;

    /**
     * ResponseEntity로 묶어 상황에 따른 정확한 코드를 보내기
     * 200 객체반환
     * 201 생성 성공
     * 데이터를 못 찾알을시 404
     * 삭제 성공시 204
     */
    @GetMapping
    public ResponseEntity<List<Student>> list(){
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students); // 200 OK
    }


    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student){
        Student createdStudent = studentService.createStudent(student);
        // 201 Created (생성 성공)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build(); // 204 No Content (삭제 성공)
    }
    // --- [여기까지 추가] ---
}