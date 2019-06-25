package com.leverX.blog.Controller;

import com.leverX.blog.domain.Article;
import com.leverX.blog.repos.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ArticleRepo articleRepo;
    @GetMapping("/")
    public String greeting(Map<String, Object> model){
        return "greeting";
    }

    @GetMapping("/articles")
    public String main(Map<String, Object> model) {

        Iterable<Article> articles = articleRepo.findAll();
        model.put("articles", articles);
        return "main";
    }

    @PostMapping("/articles")
    private String add(@RequestParam String title,
                       @RequestParam String text,
                       Map<String, Object> model){

        Article article = new Article(title, text);

        Date date = new Date();
        article.setCreatedAt(date);

        articleRepo.save(article);

        Iterable<Article> articles = articleRepo.findAll();
        model.put("articles", articles);

        return "main";
    }
}