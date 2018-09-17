package com.amdtia.usermanagement.controller;


import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
@RequestMapping("register")
public class RegisterController implements WebMvcConfigurer {
    UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("register").setViewName("register");
    }

    @GetMapping
    public String getRegister(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }


    @PostMapping
    public String registerUser(@Valid User user, BindingResult bindingResult){


        if(userRepository.findByEmail(user.getEmail()) != null){
            bindingResult.rejectValue("email", "error.user", "An account already exists with this email.");
        }
        if(userRepository.findByUsername(user.getUsername())!=null){
            bindingResult.rejectValue("username", "error.user", "An account already exists with this username.");
        }

        if(bindingResult.hasErrors()){
            return "register";
        }
        else {

            userRepository.save(user);
            return "mainPage";
        }





    }

}
