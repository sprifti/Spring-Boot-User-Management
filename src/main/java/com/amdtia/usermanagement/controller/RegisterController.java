package com.amdtia.usermanagement.controller;


import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
import java.util.Optional;


@Controller

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

    @GetMapping("register")
    public String getRegister(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("register")
    public String updateOrSave(@RequestParam(name="id")Long id,@Valid  User user, BindingResult bindingResult){
        if(id==null){
            if(validateRegister(user,bindingResult)){
                userRepository.save(user);
                return "redirect:users";
            }else{
                return "register";
            }
        }
        else
        {
            if(validateUpdate(user,bindingResult)){
                userRepository.save(user);
                return "redirect:users";
            } else
                return "register";

        }

    }



    public boolean validateRegister(@Valid User user, BindingResult bindingResult){

        if (user.getPassword().length() < 8) {
            bindingResult.rejectValue("password", "error.user", "*Password must contain more than 8 characters");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.user", "*An account already exists with this email.");
        }

        if (userRepository.findByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.user", "*An account already exists with this username.");
        }

        if(bindingResult.hasErrors()){
            return false;
        }
        else{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return true;
        }
    }



    public boolean validateUpdate(@Valid User user, BindingResult bindingResult){

        Optional<User> users = userRepository.findById(user.getId());

        if(!users.get().getEmail().equals(user.getEmail()) && userRepository.findByEmail(user.getEmail())!=null ){
            bindingResult.rejectValue("email", "error.user", "*An account already exists with this email.");
        }
        if(!users.get().getUsername().equals(user.getUsername()) && userRepository.findByUsername(user.getUsername())!=null ){
            bindingResult.rejectValue("username", "error.user", "*An account already exists with this username.");
        }

        if(user.getPassword().equals("")){

            user.setPassword(users.get().getPassword());
        }
        else{
            if (user.getPassword().length() < 8) {
                bindingResult.rejectValue("password", "error.user", "*Password must contain more than 8 characters");
            }
        }


        if(bindingResult.hasErrors()){
            return false;
        }
        else {

            return true;

        }

    }



}
