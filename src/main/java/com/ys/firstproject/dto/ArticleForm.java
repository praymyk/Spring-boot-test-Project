package com.ys.firstproject.dto;

import com.ys.firstproject.controller.entity.Article;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;

/**
 * 게시글 작성 내용을 담는 DTO
 * @author : 윤석
 * @param title : 글 제목 필드
 * @param content : 글 내용 필드
 */

public class ArticleForm {
    private String title;
    private String content;

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
