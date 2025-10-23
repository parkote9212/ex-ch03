package com.pgc.excrud.mapper;

import com.pgc.excrud.domain.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {

//    생성후 객체가 자동 담김
    void createArticle(Article article);

    Optional<Article> findById(Long id);

    List<Article> findAll ();

    void updateArticle(Article article);

    void deleteById(Long id);
}
