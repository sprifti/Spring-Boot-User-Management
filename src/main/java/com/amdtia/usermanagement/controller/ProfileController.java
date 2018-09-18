package com.amdtia.usermanagement.controller;

import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.GroupRepository;
import com.amdtia.usermanagement.repository.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ProfileController  {
    UserRepository userRepository;
    GroupRepository groupRepository;

    public ProfileController(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }



    @PostMapping("profile")
    public String getProfile(@RequestParam(name = "email")String email, Model model, Model model1){
        model.addAttribute("user",userRepository.findByEmail(email));
        model1.addAttribute("groups",groupRepository.findAll());

        return "profile";
    }


}
