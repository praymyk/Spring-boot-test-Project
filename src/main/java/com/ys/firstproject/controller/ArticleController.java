package com.ys.firstproject.controller;

import com.ys.firstproject.controller.entity.Article;
import com.ys.firstproject.dto.ArticleForm;
import com.ys.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){

        // 1. DTO를 Entity로 변환하기
        Article article = form.toEntity();
        System.out.println(article.toString());
        // 2. 리파지터리로 엔티티 DB에 보내기
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}
