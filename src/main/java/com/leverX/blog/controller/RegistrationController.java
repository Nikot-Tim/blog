package com.leverX.blog.controller;

import com.leverX.blog.domain.User;
import com.leverX.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("confirmPassword") String confirmPassword, @Valid User user, BindingResult bindingResult, Model model) {

        boolean confirmError = StringUtils.isEmpty(confirmPassword);
        if(confirmError){
            model.addAttribute("confirmPasswordError","Password confirmation can't be empty");
        }

        if(user.getPassword()!= null && !user.getPassword().equals(confirmPassword)){
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if(confirmError || bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);

            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User with the same username already exists!");
            return "registration";
        }

        return "greeting";
    }

    @GetMapping("/auth/confirm/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if(isActivated){
            model.addAttribute("messageType","success");
            model.addAttribute("message","User activated");
        }else {
            model.addAttribute("messageType","danger");
            model.addAttribute("message","Activation code is not found!");
        }

        return "auth";
    }
}
