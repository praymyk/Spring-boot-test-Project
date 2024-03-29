package com.ys.firstproject.service;

import com.ys.firstproject.dto.ArticleForm;
import com.ys.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // ArticleService 클래스 검증
        // 1. 예상 데이터 작성
        Article a = new Article(1L, "더미 제목1", "더미 내용 1");
        Article b = new Article(2L, "더미 제목2", "더미 내용 2");
        Article c = new Article(3L, "더미 제목3", "더미 내용 3");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        // 2. 실제 데이터 획득
        List<Article> articleList = articleService.index();
        // 3. 예상 데이터와 실제 데이터 비교 검증
        assertEquals(expected.toString(), articleList.toString());
    }

    @Test
    void show_성공() {
        Long id = 1L;
        Article expected = new Article(1L, "더미 제목1", "더미 내용 1");
        Article actual = articleService.show(id);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void show_실패() {
        Long id = 100L;
        Article expected = null;
        Article actual = articleService.show(id);

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void create_성공() {
        Article expected = new Article(4L, "더미 제목5", "더미 내용 5");
        String title = "더미 제목5";
        String content = "더미 내용 5";
        ArticleForm articleForm = new ArticleForm(null, title, content);
        Article actual = articleService.create(articleForm);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void create_실패(){

        Article expected = null;
        Article actual = articleService.create(new ArticleForm(1L, "더미 제목5", "더미 내용 5"));

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto_입력(){

        ArticleForm articleForm = new ArticleForm(1L, "수정 제목", "수정 내용");
        Article expected = new Article(1L, "수정 제목", "수정 내용");

        Article actual = articleService.update(1L, articleForm);

        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력(){

        ArticleForm articleForm = new ArticleForm(1L, "수정 제목1", null);
        Article expected = new Article(1L, "수정 제목1", "더미 내용 1");

        Article actual = articleService.update(1L, articleForm);

        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    @Transactional
    void update_실패_존재하지_않는_id_의_dto_입력(){

            ArticleForm articleForm = new ArticleForm(100L, "수정 제목", "수정 내용");
            Article expected = null;
            Article actual = articleService.update(100L, articleForm);

            assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력(){

        Long id = 1L;
        Article expected = new Article(1L, "더미 제목1", "더미 내용 1");
        Article actual = articleService.delete(id);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력(){

        Long id = 100L;
        Article expected = null;
        Article actual = articleService.delete(id);

        assertEquals(expected, actual);
    }

}