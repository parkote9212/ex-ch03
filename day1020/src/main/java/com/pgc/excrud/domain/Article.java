package com.pgc.excrud.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

// 1. @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 자동 생성
@Data
// 2. Mybatis가 DB 조회결과 담을 기본 생성자
@NoArgsConstructor
public class Article {

    private Long id;
    private String title;
    private String content;

//    3. Service -> DTO
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }





}
