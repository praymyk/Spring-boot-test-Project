package com.ys.firstproject.service;

import com.ys.firstproject.dto.ArticleForm;
import com.ys.firstproject.entity.Article;
import com.ys.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index(){
        return articleRepository.findAll();
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm created) {
        Article article = created.toEntity();
        return article.getId() != null ? null : articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm updated) {
        Article article = updated.toEntity();
        Article target = articleRepository.findById(id).orElse(null);
        log.info("수정 내용 : {}", article);

        if(target == null || target.getId() != id){
            return null;
        }

        target.patch(article);
        return articleRepository.save(target);
    }

    public Article delete(Long id) {

        Article target = articleRepository.findById(id).orElse(null);

        if(target == null){
            return null;
        }
        articleRepository.delete(target);
        return target;
    }

    // 트렌젝션 처리 실험용 메서드
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {

//        // 1. dto 묶음을 엔티티 묵음으로 변환하기
//        List<Article> articleList = new ArrayList<>();
//
//        for( ArticleForm dto : dtos ){
//            Article article = dto.toEntity();
//            log.info("article : {}", article.toString());
//            articlesList.add(article);

        // 스트림 문법 사용
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        
        // 3. 테스틀 위해 강제 예외사항 만들기
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제실패"));

        // 결과 반환
        return articleList;

    }
}
