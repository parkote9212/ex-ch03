package com.pgc.excrud.service;

import com.pgc.excrud.domain.Article;
import com.pgc.excrud.dto.ArticleRequest;
import com.pgc.excrud.dto.ArticleResponse;
import com.pgc.excrud.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleMapper articleMapper;

    @Transactional
    public ArticleResponse createArticle(ArticleRequest request){
        Article article = new Article(request.title(), request.content());
        // XML의 useGeneratedKeys 덕분에 article.id가 채워짐
        articleMapper.createArticle(article);
        return ArticleResponse.from(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> getAllArticles(){
        return articleMapper.findAll()
                .stream()
                .map(ArticleResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleResponse getArticleById(Long id) {
        Article article = articleMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을수 없습니다 : " + id));
        return ArticleResponse.from(article);
    }
    
    @Transactional
    public ArticleResponse updateArticle(Long id, ArticleRequest request){
        Article article = articleMapper.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 찾을수 없습니다 : " + id));
        article.update(request.title(), request.content());
//        마이바티스는 명시적 업데이트 호출필요
        articleMapper.updateArticle(article);
        return ArticleResponse.from(article);
       
    }
    
    @Transactional
    public void deleteArticle(Long id){
        articleMapper.deleteById(id);
    }
}
