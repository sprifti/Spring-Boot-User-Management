package com.amdtia.usermanagement.controller;

import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
//@RequestMapping("/loginPage")
public class LoginController implements WebMvcConfigurer {

    UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("loginPage").setViewName("loginPage");
    }

    @GetMapping("/loginPage")
    public String getLogin(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "loginPage";
    }



    @PostMapping("/loginPage")
    public String loginUser(){

        return "redirect:mainPage";
    }
        //find all permissions and then based on those permissions redirect them to different pages//






}
