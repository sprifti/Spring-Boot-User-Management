package com.amdtia.usermanagement.controller;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.model.Permissions;
import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.GroupRepository;
import com.amdtia.usermanagement.repository.UserRepository;
import com.amdtia.usermanagement.security.UserRestictionProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {
    UserRepository userRepository;
    GroupRepository groupRepository;
    private UserRestictionProvider userRestictionProvider;

    public ProfileController(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping("profile")
    public String getProfile(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("user", userRepository.findByUsername(auth.getName()));
        model.addAttribute("groups", groupRepository.findAll());
        User user = userRepository.findByUsername(auth.getName());


        List<Permissions> permissions = new ArrayList<>();
        for (Groups groups : user.getGroups()) {
            for (Permissions permission : groups.getPermissions()) {
                if (!permissions.contains(permission)) {
                    permissions.add(permission);
                }
            }

        }

        model.addAttribute("permissions", permissions);


        return "profile";
    }


    @PostMapping("profile")
    public String setProfile(@RequestParam(name = "email") String email, Model model) {


        model.addAttribute("user", userRepository.findByEmail(email));
        model.addAttribute("groups", groupRepository.findAll());
        User user = userRepository.findByEmail(email);

        //permissions te nje useri tek te gjith grupet//
        List<Permissions> permissions = new ArrayList<>();
        for (Groups groups : user.getGroups()) {
            for (Permissions permission : groups.getPermissions()) {
                if (!permissions.contains(permission)) {
                    permissions.add(permission);
                }
            }

        }

        model.addAttribute("permissions", permissions);
//        model.addAttribute("groupName",groupRepository.findByUserId((long) 1));

        return "profile";
    }


}
