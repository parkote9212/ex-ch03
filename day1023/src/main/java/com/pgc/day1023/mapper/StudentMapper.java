package com.pgc.day1023.mapper;

import com.pgc.day1023.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

        List<Student> findAll();

    int insert(Student student);

    Student findById(Long id);

    int update(Student student);

    int delete(Long id);
}
