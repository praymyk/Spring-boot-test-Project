package com.ys.firstproject.dto;


import com.ys.firstproject.entity.Article;
import com.ys.firstproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class CommentDto {

    private Long id;
    private Long articleId;
    private String nickname;
    private String body;


    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody());
    }

    public Comment toEntity() {
        return new Comment(
                null,
                new Article(articleId, null, null),
                nickname,
                body);
    }
}
