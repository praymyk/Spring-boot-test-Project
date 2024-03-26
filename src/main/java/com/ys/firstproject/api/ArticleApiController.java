package com.ys.firstproject.api;

import com.ys.firstproject.dto.ArticleForm;
import com.ys.firstproject.entity.Article;
import com.ys.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    //GET
    //POST
    //PATCH
    //DELETE
    @Autowired
    ArticleRepository articleRepository;

    /**
     * REST API 전체 게시글 조회
     * @return
     */
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    /**
     * REST API 단일 게시글 조회
     * @param id
     * @return
     */
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){

        return articleRepository.findById(id).orElse(null);
    }

    /**
     * REST API 게시글 생성
     * @param dto
     * @return
     */
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){

        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    /**
     * REST API 게시글 수정
     * @param id
     * @return
     */
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){

        // step1. 수정 요청 데이터 Entity 가공
        Article article = dto.toEntity();
        log.info("id : {}, article : {}", id, article.toString());
        // step2. 수정 대상 Entity 조회
        Article target = articleRepository.findById(id).orElse(null);

        if(target == null || id != article.getId()){
            // step3. 요청이 잘못됐을때 (요청 대상이 없거나 주소 id값과 수정 요청 id값이 다를때)
            log.info("잘못된 요청! id : {}, article : {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            // step4. 요청이 정상적일때 target(기존데이터)에 article(수정요청데이터)를 덮어씌움 -> 일부 수정 시 null 값 방지
            target.patch(article);
            Article updated = articleRepository.save(target);
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        }
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

        Article target = articleRepository.findById(id).orElse(null);
        log.info("id : {}, target : {}", id, target);

        if(target == null){
            log.info("잘못된 요청! id : {}, target : {}", id, target);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            articleRepository.delete(target);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }
}
