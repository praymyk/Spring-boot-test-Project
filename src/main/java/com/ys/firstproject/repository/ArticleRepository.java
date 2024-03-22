package com.ys.firstproject.repository;

import com.ys.firstproject.controller.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
