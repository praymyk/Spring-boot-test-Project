package com.ys.firstproject.controller.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * H2 DB에 작성 글 정보를 담기 위해 DTO > Entity 가공 클래스
 * 글 내용을 DB에 저장하기 위한 고유id 값을 추가함
 */
@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
