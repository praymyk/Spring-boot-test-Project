package com.ys.firstproject.api;

import com.ys.firstproject.dto.CommentDto;
import com.ys.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 1. 댓글 조회
    @GetMapping("api/articles/{articleId}/comments")
    private ResponseEntity<List<CommentDto>> comments (@PathVariable Long articleId){

        List<CommentDto> dtos = commentService.comments(articleId);
        log.info("articleId : {}", articleId);
        log.info("result : {}", dtos.toString());

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 2. 댓글 생성
    @PostMapping("api/articles/{articleId}/comments")
    private ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto){
        CommentDto created = commentService.create(articleId, dto);
        log.info("articleId : {}", articleId);
        log.info("dto : {}", dto.toString());
        log.info("created : {}", created.toString());

        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 3. 댓글 수정
    @PatchMapping("api/comments/{id}")
    private ResponseEntity<CommentDto> update(@PathVariable Long id,
                                              @RequestBody CommentDto dto) {
        // 서비스에 위임
        CommentDto updateDto = commentService.update(id, dto);

        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 4. 댓글 삭제
    @DeleteMapping("api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        // 서비스에 위임
        CommentDto deleted = commentService.delete(id);

        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }

}
