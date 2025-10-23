package com.pgc.excrud.controller;

import com.pgc.excrud.dto.ArticleRequest;
import com.pgc.excrud.dto.ArticleResponse;
import com.pgc.excrud.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleViewController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public String getArticleList(Model model){
//        서비스에서 데이터 가져오기
        List<ArticleResponse> articles = articleService.getAllArticles();
//    Model 객체에 데이터를 담아 뷰로 전달
//        articles라는 이름으로 리스트 전달
        model.addAttribute("articles", articles);
//        templates 폴더 밑에 있는 "article-list.html 반환
        return "article-list";
    }

    @GetMapping("/articles/{id}")
    public String getArticleDetail(@PathVariable Long id, Model model){
        ArticleResponse article = articleService.getArticleById(id);
        model.addAttribute("article",article);

        return "article-detail";
    }

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "article-form";
    }

    @PostMapping("/articles")
    public String createArticle(ArticleRequest request){
       articleService.createArticle(request);

       return "redirect:/articles";

    }


}
