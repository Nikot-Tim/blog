package com.leverX.blog.repos;

import com.leverX.blog.domain.Article;
import com.leverX.blog.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CommentsRepo extends CrudRepository<Comment, Long> {

    Page<Comment> findByPost(Article article, Pageable pageable);
}
