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
    public String getProfile(@RequestParam(name = "email")String email, Model model){
        model.addAttribute("user",userRepository.findByEmail(email));
        model.addAttribute("groups",groupRepository.findAll());
//        model.addAttribute("groupName",groupRepository.findByUserId((long) 1));

        return "profile";
    }


}
