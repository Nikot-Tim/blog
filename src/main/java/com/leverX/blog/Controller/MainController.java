package com.leverX.blog.Controller;

import com.leverX.blog.domain.Article;
import com.leverX.blog.domain.User;
import com.leverX.blog.repos.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private ArticleRepo articleRepo;
    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal User user, Map<String, Object> model){
        if(user != null && user.isActive()){
            return "redirect:/articles";
        }else
        return "redirect:/auth";
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
            article.setUpdatedAt(date);
            model.addAttribute("article",null);
            articleRepo.save(article);
        }
        Iterable<Article> articles = articleRepo.findAll();
        model.addAttribute("articles", articles);

        return "main";
    }

    @GetMapping("/my/{user}")
    public String showUserArticles(@PathVariable User user,  Model model){
        Set<Article> articles = user.getArticles();
        model.addAttribute("articles", articles);
        return "myArticles";
    }

    @GetMapping("/articles/{article}")
    public String edit(@PathVariable Article article, Model model){
        model.addAttribute("article", article);
        User user = article.getAuthor();
        model.addAttribute("articles", user.getArticles());
        return "myArticles";
    }
    @PostMapping("/articles/{article}")
    public String updateArticle(@AuthenticationPrincipal User currentUser, @PathVariable Article article, @RequestParam("id") Article updatedArticle,
                                @RequestParam("title") String title, @RequestParam("text") String text
    ){
        User user = article.getAuthor();
        if(article.getAuthor().equals(currentUser)){
            if(!StringUtils.isEmpty(title)){
                updatedArticle.setTitle(title);
            }
            if(!StringUtils.isEmpty(text)){
                updatedArticle.setText(text);
            }
        }
        Date date = new Date();
        updatedArticle.setUpdatedAt(date);
        articleRepo.save(updatedArticle);

        return "redirect:/my/"+user.getId();
    }
}