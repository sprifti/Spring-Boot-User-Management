package com.amdtia.usermanagement.controller;


import com.amdtia.usermanagement.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class RegisterController {


    @GetMapping
    public String getRegister(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }


    @PostMapping
    public String registerUser(Model model){

    }

}
