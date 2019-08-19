package com.leverX.blog.Controller;

import com.leverX.blog.domain.Article;
import com.leverX.blog.domain.User;
import com.leverX.blog.repos.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
    private String add(@AuthenticationPrincipal User user,
                       @Valid Article article,
                       BindingResult bindingResult,
                       Model model){

        article.setAuthor(user);

        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("article", article);

        }else {
            Date date = new Date();
            article.setCreatedAt(date);
            model.addAttribute("article",null);
            articleRepo.save(article);
        }
        Iterable<Article> articles = articleRepo.findAll();
        model.addAttribute("articles", articles);

        return "main";
    }
}