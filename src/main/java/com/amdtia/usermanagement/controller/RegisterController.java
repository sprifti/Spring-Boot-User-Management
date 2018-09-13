package com.amdtia.usermanagement.controller;


import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class RegisterController {
    UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getRegister(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }


    @PostMapping
    public String registerUser(@ModelAttribute User user){
        userRepository.save(user);
        return "mainPage";
    }

}
