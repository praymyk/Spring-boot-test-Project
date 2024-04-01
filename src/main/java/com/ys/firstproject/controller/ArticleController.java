package com.ys.firstproject.controller;

import com.ys.firstproject.dto.CommentDto;
import com.ys.firstproject.entity.Article;
import com.ys.firstproject.dto.ArticleForm;
import com.ys.firstproject.repository.ArticleRepository;
import com.ys.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    /**
     * 게시글 작성페이지 이동용 메서드
     * @return
     */
    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }

    /**
     * 게시글 작성용 메소드
     * @param form
     * @return
     */
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){

        // 1. DTO를 Entity로 변환하기
        Article article = form.toEntity();
        log.info(form.toString());
        // 2. 리파지터리로 엔티티 DB에 보내기
        Article saved = articleRepository.save(article);
        log.info(article.toString());


        return "redirect:/articles/" + saved.getId();
    }

    /**
     * 단일 게시글 내용 조회 메서드
     * @param id - 게시글 번호
     * @param model - DB게시글 정보를 담을 Model
     * @return 임시로 show(전체 게시판 조회 페이지로 보냄)
     */
    @GetMapping("/articles/{id}")
    public String show(
            @PathVariable Long id,
            Model model){
        log.info("id = " + id);
        // step1. id 조회해 데이터 가져오기
        Article articleEntity =  articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        // step2. 모델 데이터에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);
        // step3. 뷰 페이지 보내기
        return "articles/show";
    }

    /**
     * 게시글 리스트 조회용 메소드
     * @param model
     * @return
     */
    @GetMapping("/articles")
    public String index(Model model){

        // step1. 모든 게시글 목록 가져오기
        ArrayList<Article> articleEntityList = (ArrayList<Article>) articleRepository.findAll();
        // step2. 모델 데이터 등럭
        model.addAttribute("articleEntityList", articleEntityList);
        // step3. 뷰 페이지 보내기
        return "articles/index";
    }

    /**
     * 게시글 수정 페이지 이동용 메소드
     * @param id
     * @param model
     * @return
     */
    @GetMapping("articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    /**
     * 게시글 수정용 메소드
     * @param form
     * @return
     *
     * step 1. 수정할 게시글의 정보를 DTO 클래스 형태로 받기
     */
    @PostMapping("articles/update")
    public String update(ArticleForm form){

        log.info(form.toString());
        // step2. 변경할 게시글의 정보를 엔티티 타입으로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // step3. 변결할 게시글의 정보를 DB에서 조회
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        if(target != null){
            articleRepository.save(articleEntity);
        }

        return "redirect:/articles/" + articleEntity.getId();
    }

    /**
     * 게시글 삭제 처리용 메서드
     * @param id
     * @param rttr
     * @return
     */
    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){

        log.info("삭제요청이 들어왔습니다.");

        //step1. 삭제 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        //step2. 대상 엔티티 삭제하기
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제완료");
        }

        //step3. 결괴페이지 리다이렉트
        return "redirect:/articles";
    }

}