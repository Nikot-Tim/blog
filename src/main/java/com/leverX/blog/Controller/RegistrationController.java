package com.leverX.blog.Controller;

import com.leverX.blog.domain.User;
import com.leverX.blog.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByFirstName(user.getFirstName());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        Date date = new Date();
        user.setCreatedAt(date);

        userRepo.save(user);

        return "redirect:/auth";
    }
}