package com.ys.firstproject.controller;

import com.ys.firstproject.entity.Article;
import com.ys.firstproject.dto.ArticleForm;
import com.ys.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
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
        log.info(form.toString());
        // 2. 리파지터리로 엔티티 DB에 보내기
        Article saved = articleRepository.save(article);
        log.info(article.toString());


        return "redirect:/articles/" + saved.getId();
    }

    /**
     * 단일 게시글 내용 조회 메서드
     * @param id - 게시글 번호
     * @param model - DB게시글 정보를 담을 Model
     * @return 임시로 show(전체 게시판 조회 페이지로 보냄)
     */
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // step1. id 조회해 데이터 가져오기
        Article articleEntity =  articleRepository.findById(id).orElse(null);
        // step2. 모델 데이터에 등록
        model.addAttribute("article", articleEntity);
        // step3. 뷰 페이지 보내기
        return "articles/show";
    }

    @GetMapping("/articles/index")
    public String index(Model model){

        // step1. 모든 게시글 목록 가져오기
        ArrayList<Article> articleEntityList = (ArrayList<Article>) articleRepository.findAll();
        // step2. 모델 데이터 등럭
        model.addAttribute("articleEntityList", articleEntityList);
        // step3. 뷰 페이지 보내기
        return "articles/index";
    }

}
