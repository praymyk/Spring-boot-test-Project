package com.ys.firstproject.repository;

import com.ys.firstproject.entity.Article;
import com.ys.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        // Case 1   4번글 모든 댓글 출력
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;

            // 3. 예상 데이터
            String nickName = "닉네임1";
            String body1 = "더미 댓글 1";
            String body2 = "더미 댓글 2";
            String body3 = "더미 댓글 3";
            Article article = new Article(articleId, "댓글이 달린다!", "더미 내용 4");

            List<Comment> expected = new ArrayList<>();
            expected.add(new Comment(1L, article, nickName, body1));
            expected.add(new Comment(2L, article, nickName, body2));
            expected.add(new Comment(3L, article, nickName, body3));


            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByArticleId(articleId);

            assertEquals(expected.toString(), actual.toString(), "4번글 모든 댓글 출력");
        }

        // Case 2   1번글 모든 댓글 출력
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L;

            // 3. 예상 데이터
            Article article = new Article(articleId, "더미 제목1", "더미 내용 1");
            List<Comment> expected = Arrays.asList();

            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByArticleId(articleId);

            assertEquals(expected.toString(), actual.toString(), "1번글의 모든 댓글 출력");
        }

        // Case 3. 9번 계시글의 모든 댓글 조회
        {
            // 1. 입력데이터 준비
            Long articleId = 9L;

            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByArticleId(articleId);

            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();

            // 4. 검증
            assertEquals(expected, actual, "9번 게시글의 모든 댓글 조회(비어있음)");
        }

        // Case 4. 999번 게시글의 모든댓글 조회
        {
            // 1. 입력데이터 준비
            Long articleId = 999L;

            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByArticleId(articleId);

            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();

            // 4. 검증
            assertEquals(expected, actual, "999번 게시글의 모든 댓글 조회(비어있음)");
        }

        // Case 5. -1번 게시글의 모든 댓글 조회
        {
            // 1. 입력데이터 준비
            Long articleId = -1L;

            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByArticleId(articleId);

            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();

            // 4. 검증
            assertEquals(expected, actual, "-1번 게시글의 모든 댓글 조회(존재하지 않음)");
        }


    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {

        {   // Case 1. "닉네임1"의 모든 댓글 조회
            //1. 입력 데이터 준비
            // "닉네임1"의 모든 댓글 조회
            String nickname = "닉네임1";

            //2. 실제 데이터
            List<Comment> actual = commentRepository.findByNickname(nickname);
            Article article = new Article(4L, "댓글이 달린다!", "더미 내용 4");

            //3. 예상 데이터
            List<Comment> expected = Arrays.asList(
                    new Comment(1L, article, nickname, "더미 댓글 1"),
                    new Comment(2L, article, nickname, "더미 댓글 2"),
                    new Comment(3L, article, nickname, "더미 댓글 3")
            );

            //4. 검증
            assertEquals(expected.toString(), actual.toString(), "닉네임1의 모든 댓글 조회");
        }

        {   //Case2. "닉네임2" 의 모든 댓글 조회

            // 1. 입력 데이터 준비
            String nickname = "닉네임2";

            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByNickname(nickname);

            // 3. 예상 데이터
            Article article = new Article(5L, "댓글을 붙여주세요", "더미 내용 5");
            Comment a = new Comment(4L, article, nickname, "더미 댓글 1");
            Comment b = new Comment(5L, article, nickname, "더미 댓글 2");
            Comment c = new Comment(6L, article, nickname, "더미 댓글 3");

            List<Comment> expected = Arrays.asList(a, b, c);

            // 4. 검증
            assertEquals(expected.toString(), actual.toString(), "닉네임2의 모든 댓글 조회");


        }
    }


}