package com.pgc.excrud.dto;

import com.pgc.excrud.domain.Article;

//응답용
public record ArticleResponse(
        Long id,
        String title,
        String content
) {
//    DTO 변환 메소드
    public static ArticleResponse from(Article article){
        return new ArticleResponse(
                article.getId(),
                article.getTitle(),
                article.getContent()
        );
    }
}
