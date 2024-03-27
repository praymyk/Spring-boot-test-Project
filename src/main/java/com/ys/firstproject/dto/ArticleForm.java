package com.ys.firstproject.dto;

import com.ys.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 게시글 작성 내용을 담는 DTO
 * @author : 윤석
 * @param title : 글 제목 필드
 * @param content : 글 내용 필드
 */

@AllArgsConstructor
@ToString
@Getter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
