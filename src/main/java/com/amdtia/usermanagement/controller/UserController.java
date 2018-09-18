package com.amdtia.usermanagement.controller;

import com.amdtia.usermanagement.repository.GroupRepository;
import com.amdtia.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class UserController implements WebMvcConfigurer {
   UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("users").setViewName("users");
    }


    @RequestMapping("users")
    public String listUsers(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(name="Id")Long Id) {
        userRepository.deleteById(Id);
        return  "redirect:users";
    }

}
