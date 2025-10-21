package com.pgc.excrud.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pgc.excrud.domain.Student;
import com.pgc.excrud.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본값은 읽기 전용
public class StudentService {

    private final StudentMapper studentMapper;

    public List<Student> getAllStudents() {
        return studentMapper.findAll();
    }

    // --- [!] 추가된 메서드 ---

    /**
     * 학생 생성 (데이터 변경이 있으므로 @Transactional)
     */
    @Transactional
    public Student createStudent(Student student) {
        studentMapper.insert(student);
        // insert 쿼리에서 keyProperty="id"를 사용했기 때문에
        // 파라미터로 받은 student 객체의 id 필드가 채워진 상태입니다.
        return student;
    }

    /**
     * 학생 수정 (데이터 변경이 있으므로 @Transactional)
     */
    @Transactional
    public int updateStudent(Student student) {
        return studentMapper.update(student);
    }

    /**
     * 학생 삭제 (데이터 변경이 있으므로 @Transactional)
     */
    @Transactional
    public int deleteStudent(Long id) {
        return studentMapper.delete(id);
    }
}