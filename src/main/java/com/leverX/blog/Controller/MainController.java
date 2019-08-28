package com.leverX.blog.Controller;

import com.leverX.blog.domain.Article;
import com.leverX.blog.domain.Status;
import com.leverX.blog.domain.User;
import com.leverX.blog.repos.ArticleRepo;
import com.leverX.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal User user){
        if(user != null && user.isActive()){
            return "redirect:/articles";
        }else
            return "redirect:/auth";
    }

    @GetMapping("/articles")
    public String main(Map<String, Object> model) {

        Iterable<Article> articles = articleRepo.findAll();
        model.put("articles", articles);
        model.put("statuses", Status.values());
        return "main";
    }

    @PostMapping("/articles")
    private String add(@AuthenticationPrincipal User user,
                       @RequestParam Map<String,String> status,
                       @Valid Article article,
                       BindingResult bindingResult,
                       Model model){

        article.setAuthor(user);

        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            Article articleStatus = articleService.determineArticleStatus(status, article);
            model.addAttribute("article", articleStatus);
            model.addAttribute("statuses", Status.values());

        }else {
            Date date = new Date();
            article.setCreatedAt(date);
            article.setUpdatedAt(date);
            Article saveArticle = articleService.determineArticleStatus(status, article);
            model.addAttribute("article", null);
            articleRepo.save(saveArticle);

        }
        Iterable<Article> articles = articleRepo.findAll();
        model.addAttribute("articles", articles);
        model.addAttribute("statuses", Status.values());

        return "main";
    }

    @GetMapping("/my/{user}")
    public String showUserArticles(@PathVariable User user,  Model model){
        Set<Article> articles = user.getArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("openActionForm", false);
        return "myArticles";
    }

    @GetMapping("/articles/{article}")
    public String edit(@PathVariable Article article, Model model){
        model.addAttribute("article", article);
        User user = article.getAuthor();
        model.addAttribute("articles", user.getArticles());
        model.addAttribute("openActionForm", true);
        model.addAttribute("statuses", Status.values());
        return "myArticles";
    }

    @PostMapping("/articles/{article}")
    public String updateArticle(@AuthenticationPrincipal User currentUser,
                                @PathVariable Article article,
                                @RequestParam("id") Article updatedArticle,
                                @RequestParam("title") String title,
                                @RequestParam("text") String text,
                                @RequestParam Map<String,String> status
    ){
        User user = article.getAuthor();
        if(article.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(title)) {
                updatedArticle.setTitle(title);
            }
            if (!StringUtils.isEmpty(text)) {
                updatedArticle.setText(text);
            }
        }
        Date date = new Date();
        updatedArticle.setUpdatedAt(date);
        Article saveArticle = articleService.determineArticleStatus(status, updatedArticle);
        articleRepo.delete(article);
        articleRepo.save(saveArticle);
        return "redirect:/my/"+user.getId();
    }

    @GetMapping("/articles/delete/{article}")
    public String delete(@PathVariable Article article, Model model){
        articleRepo.delete(article);
        return "redirect:/articles";
    }
}