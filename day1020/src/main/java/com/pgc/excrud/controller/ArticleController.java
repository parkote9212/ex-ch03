package com.pgc.excrud.controller;


import com.pgc.excrud.dto.ArticleRequest;
import com.pgc.excrud.dto.ArticleResponse;
import com.pgc.excrud.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
// HTTP요청을 받는 창구
public class ArticleController {

//    의존성 주입후 변경되지 않도록 보장
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponse> createArticle(@RequestBody ArticleRequest request) {
        ArticleResponse response = articleService.createArticle(request);
        return ResponseEntity.ok(response);
    }

    // R: 전체 조회
    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        List<ArticleResponse> responses = articleService.getAllArticles();
        return ResponseEntity.ok(responses);
    }

    // R: 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable Long id) {
        ArticleResponse response = articleService.getArticleById(id);
        return ResponseEntity.ok(response);
    }

    // U: 수정
    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id, @RequestBody ArticleRequest request) {
        ArticleResponse response = articleService.updateArticle(id, request);
        return ResponseEntity.ok(response);
    }

    // D: 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
