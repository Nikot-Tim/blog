package com.leverX.blog.controller;

import com.leverX.blog.domain.Article;
import com.leverX.blog.domain.Comment;
import com.leverX.blog.domain.Status;
import com.leverX.blog.domain.User;
import com.leverX.blog.repos.CommentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

@Controller
public class CommentsController {
    @Autowired
    CommentsRepo commentsRepo;

    @GetMapping("/articles/{article}/comments")
    public String showComments(Model model, @PathVariable Article article,
                               @PageableDefault(sort={"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        Page<Comment> page = commentsRepo.findByPost(article, pageable);
        model.addAttribute("page", page);
        String url = "/articles/" + article.getId() + "/comments";
        model.addAttribute("url",url );
        model.addAttribute("statuses", Status.values());
        model.addAttribute("article", article);
        return "articleWithComments";
    }

    @GetMapping("/articles/{article}/comments/{comment}")
    public String deleteComment(@PathVariable Article article, @PathVariable Comment comment){
        commentsRepo.delete(comment);
        return "redirect:/articles/{article}/comments";
    }

    @PostMapping("/articles/{article}/comments")
    private String addComment(@AuthenticationPrincipal User user,
                              @PathVariable Article article,
                              @Valid Comment comment,
                              BindingResult bindingResult,
                              Model model,
                              @PageableDefault(sort={"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable){

        comment.setAuthor(user);
        comment.setPost(article);

        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);

        }else {
            Date date = new Date();
            comment.setCreatedAt(date);
            model.addAttribute("comment", null);
            commentsRepo.save(comment);
        }
        Page<Comment> page = commentsRepo.findByPost(article, pageable);
        model.addAttribute("page", page);
        String url = "/articles/" + article.getId() + "/comments";
        model.addAttribute("url",url );
        model.addAttribute("article", article);

        return "articleWithComments";
    }
}
