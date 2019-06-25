package com.leverX.blog.repos;

import com.leverX.blog.domain.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepo extends CrudRepository<Article, Long> {
}
