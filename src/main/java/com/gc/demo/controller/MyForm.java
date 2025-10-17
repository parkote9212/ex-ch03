package com.gc.demo.controller;

import com.gc.demo.dto.ArticleRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyForm {

    @GetMapping("/article-get")
    public String articleGet(@RequestParam String title,@RequestParam String content){
        String result = "제목 : " + title + "\n" + "<br>내용 : " + content;
        return result;
    }
//
//    @PostMapping("/article-post")
//    public String articlePost(@ModelAttribute ArticleRequest request){
//       String result = "제목 : " + request.title() + "\n" + "내용 : " + request.content();
//        return result;
//    }

    @PostMapping("/article-post")
    public String articlePost(@RequestBody ArticleRequest request){
        String result = "제목 : " + request.title() + "\n" + "내용 : " + request.content();
        return result;
    }

    @PostMapping("/article-auto")
    public String articleAuto(ArticleRequest request){
        String result = "제목 : " + request.title() + "\n" + "내용 : " + request.content();
        return result;
    }

}
