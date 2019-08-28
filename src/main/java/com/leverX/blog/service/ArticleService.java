package com.leverX.blog.service;

import com.leverX.blog.domain.Article;
import com.leverX.blog.domain.Status;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class ArticleService {

    public Article determineArticleStatus(Map<String, String> status, Article article) {
        int checker = -1;
        for(String key : status.keySet()){
            if(key.equals("articleStatus")){
                if(status.get(key).equals("DRAFT")){
                    article.setStatuses(Collections.singleton(Status.DRAFT));
                    checker = 1;
                }
                else {
                    article.setStatuses(Collections.singleton(Status.PUBLIC));
                    checker = 1;
                }
            }
        }
        if(checker == -1){
            article.setStatuses(Collections.singleton(Status.PUBLIC));
        }
        return article;
    }
}
