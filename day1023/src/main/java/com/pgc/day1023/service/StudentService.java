package com.pgc.day1023.service;

import com.pgc.day1023.domain.Student;
import com.pgc.day1023.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
// (readOnly = true) : 기본적으로 모든 메소드는 읽기 전용으로 설정
@Transactional(readOnly = true)
public class StudentService {

    private final StudentMapper studentMapper;

    /**
     * 모든 학생 조회 (기존 코드)
     */
    public List<Student> getAllStudents() {
        return studentMapper.findAll();
    }

    /**
     * 학생 생성 (기존 코드)
     * 쓰기 작업이므로 @Transactional을 개별로 붙여 readOnly=false 적용
     */
    @Transactional
    public Student createStudent(Student student) {
        studentMapper.insert(student);
        // useGeneratedKeys="true" 덕분에 student 객체에 id가 채워짐
        return student;
    }

    // --- [추가된 코드] ---

    /**
     * ID로 학생 1명 조회
     */
    public Student getStudentById(Long id) {
        return studentMapper.findById(id);
    }

    /**
     * 학생 정보 수정
     */
    @Transactional // 쓰기 작업
    public Student updateStudent(Long id, Student studentDetails) {
        // 1. ID로 기존 학생 정보를 조회합니다.
        Student existingStudent = studentMapper.findById(id);

        // 2. 학생이 존재하지 않으면 null 반환 (또는 예외 처리)
        if (existingStudent == null) {
            // 여기서는 간단히 null을 반환하지만,
            // RestController에서 404 Not Found를 반환하려면
            // throw new RuntimeException("Student not found"); 처럼 예외를 던지는 것이 더 좋습니다.
            return null;
        }

        // 3. (Optional) DTO가 아닌 Domain 객체를 직접 사용할 경우
        // 넘어온 값으로 기존 객체의 필드를 업데이트합니다.
        existingStudent.setName(studentDetails.getName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setAge(studentDetails.getAge());
        // (updatedAt은 XML 쿼리에서 NOW()로 자동 설정됩니다)

        // 4. 수정된 내용으로 DB를 업데이트합니다.
        studentMapper.update(existingStudent);

        // 5. 수정된 객체를 반환합니다.
        return existingStudent;
    }

    /**
     * 학생 삭제
     */
    @Transactional // 쓰기 작업
    public void deleteStudent(Long id) {
        studentMapper.delete(id);
    }
    // --- [여기까지 추가] ---
}