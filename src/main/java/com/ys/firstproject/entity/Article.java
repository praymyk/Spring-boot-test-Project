package com.ys.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * H2 DB에 작성 글 정보를 담기 위해 DTO > Entity 가공 클래스
 * 글 내용을 DB에 저장하기 위한 고유id 값을 추가함
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

}
