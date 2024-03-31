package com.ys.firstproject.service;

import com.ys.firstproject.dto.CommentDto;
import com.ys.firstproject.entity.Article;
import com.ys.firstproject.entity.Comment;
import com.ys.firstproject.repository.ArticleRepository;
import com.ys.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 2. 엔티티 > dto 전환
        List<CommentDto> dtos = new ArrayList<CommentDto>();

        for(int i = 0; i < comments.size(); i++){

            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);

            dtos.add(dto);
        }

        // 3. 결과반환
        return dtos;
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        //1. articleId에 해당하는 article이 존재하는지 확인 -> 없으면 예외 발생시키기
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new IllegalArgumentException("댓글 생성실패!" + "해당 게시글 존재하지 않음")
        );
        
        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 3. 댓글 엔티티를 DB 저장
        Comment created = commentRepository.save(comment);

        // DTO 변환 후 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        // 1. DB에 수정 요청 Comment 검색 후 존재하지 않으면 예외처리
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 수정 실패!" + "해당 댓글이 존재하지 않음")
        );

        // 2. comment에 수정 요청 내용 반영시키기
        comment.patch(dto);

        // 3. DB에 갱신
        Comment updated = commentRepository.save(comment);

        // 4. dto형태로 결과 반납
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 1. DB에 삭제 요청 comment 조회 후 존재하지 않을 경우 예외 처리
        Comment target = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 삭제 실패!" + "해당 댓글이 존재하지 않음")
        );

        // 2. DB에서 삭제 및 결과 반환
        commentRepository.delete(target);

        // 3. 삭제된 댓글을 dto로 변환
        CommentDto result = CommentDto.createCommentDto(target);

        return result;
    }
}
