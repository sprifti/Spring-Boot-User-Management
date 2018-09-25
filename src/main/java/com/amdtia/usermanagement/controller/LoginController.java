package com.amdtia.usermanagement.controller;

import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.UserRepository;
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
@RequestMapping("/loginPage")
public class LoginController implements WebMvcConfigurer {

    UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("loginPage").setViewName("loginPage");
    }

    @GetMapping
    public String getLogin(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "loginPage";
    }



    @PostMapping
    public String loginUser(@Valid User user, BindingResult bindingResult,Model model){

        User users = userRepository.findByEmail(user.getEmail());

        if(userRepository.findByEmail(user.getEmail())==null){
            bindingResult.rejectValue("email", "error.user", "*This account doesn't exists");
        }
        else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if(encoder.matches(user.getPassword(), users.getPassword())){
                return "redirect:mainPage";
            }
            else{
                bindingResult.rejectValue("password", "error.user", "*Password incorrect");
            }

        }
        if(bindingResult.hasErrors()){
            return "loginPage";
        }
        return "redirect:mainPage";
    }


}
