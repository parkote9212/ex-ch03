package com.pgc.excrud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pgc.excrud.domain.Student;

@Mapper
public interface StudentMapper {

	List<Student> findAll();

    int insert(Student student);


    int update(Student student);


    int delete(Long id);

}
