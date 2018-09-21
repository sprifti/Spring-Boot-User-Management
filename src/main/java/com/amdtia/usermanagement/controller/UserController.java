package com.amdtia.usermanagement.controller;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.GroupRepository;
import com.amdtia.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Controller
public class UserController implements WebMvcConfigurer  {
   UserRepository userRepository;
   GroupRepository groupRepository;

    public UserController(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
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

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@RequestParam(name="email")String email,Model model) {
        model.addAttribute("user", userRepository.findByEmail(email));
        return "register";

    }

    @RequestMapping(value = "/addUserToGroup", method = RequestMethod.POST)
    public String addUserToGroup(@RequestParam(name="email")String email, @RequestParam(name="id") Long id){

        User user = userRepository.findByEmail(email);
        Optional<Groups> groups = groupRepository.findById((long)id);

    if( groups.isPresent()) {
        groups.get().getUser().add(user);
        groupRepository.save(groups.get());
    }
        return "redirect:mainPage";
    }

}
