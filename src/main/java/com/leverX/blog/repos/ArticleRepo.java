package com.leverX.blog.repos;

import com.leverX.blog.domain.Article;
import com.leverX.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;

public interface ArticleRepo extends CrudRepository<Article, Long> {

    Page<Article> findAll(Pageable pageable);

    Page<Article> findByAuthor(User user, Pageable pageable);

    Page<Article> findByTag(String tag, Pageable pageable);
}
