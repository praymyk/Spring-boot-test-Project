package com.ys.firstproject.api;

import com.ys.firstproject.dto.ArticleForm;
import com.ys.firstproject.entity.Article;
import com.ys.firstproject.repository.ArticleRepository;
import com.ys.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;
    /**
     * REST API 전체 게시글 조회
     * @return
     */
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    /**
     * REST API 단일 게시글 조회
     * @param id
     * @return
     */
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){

        return articleService.show(id);
    }

    /**
     * REST API 게시글 생성
     * @param dto
     * @return
     */
    @PostMapping("/api/articles")
    public ResponseEntity<Article> created(@RequestBody ArticleForm dto){

        Article created = articleService.create(dto);
        log.info("dto : {}", dto.toString());

        return created != null ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * REST API 게시글 수정
     * @param id
     * @return
     */
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){

        Article updated = articleService.update(id, dto);

        return updated != null ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * REST API 게시글 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

        Article deleted = articleService.delete(id);

        return deleted != null ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * Rest API 트렌젝션 처리 테스트용
     */
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){

        List<Article> createdList = articleService.createArticles(dtos);

        return createdList != null ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
