package com.amdtia.usermanagement.controller;


import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;


@Controller
@RequestMapping("register")
public class RegisterController implements WebMvcConfigurer {
    UserRepository userRepository;
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]";


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
        if(user.getPassword().length()<8){
            bindingResult.rejectValue("password", "error.user", "*Password must contain more than 8 characters");
        }

        if(user.getPassword().length()<8){
            bindingResult.rejectValue("password", "error.user", "*Password must contain more than 8 characters");
        }

//        if(user.getUsername().matches(USERNAME_PATTERN)){
//            bindingResult.rejectValue("username", "error.user", "*Username can only contain letters and numbers");
//        }


        if(userRepository.findByEmail(user.getEmail()) != null){
            bindingResult.rejectValue("email", "error.user", "*An account already exists with this email.");
        }

        if(userRepository.findByUsername(user.getUsername())!=null){
            bindingResult.rejectValue("username", "error.user", "*An account already exists with this username.");
        }

        if(bindingResult.hasErrors()){
            return "register";
        }
        else {

//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "mainPage";
        }





    }

}
