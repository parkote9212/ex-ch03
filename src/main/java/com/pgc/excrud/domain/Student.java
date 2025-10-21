package com.pgc.excrud.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Student {

	private Long id;
	private String name;
	private String email;
	private Integer age;
//    스네이크로쓰면 설명에 카멜케이스 자동변환으로 null반환
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
