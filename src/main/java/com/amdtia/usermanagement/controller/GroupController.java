package com.amdtia.usermanagement.controller;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.repository.GroupRepository;
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

import javax.validation.Valid;

import static java.time.zone.ZoneRulesProvider.refresh;

@Controller
public class GroupController implements WebMvcConfigurer {
    GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("groups").setViewName("groups");
    }



    @RequestMapping("showFormGroup")
        public String showFormGroup(Model model){
        Groups groups = new Groups();
        model.addAttribute("groups",groups);
        return "addGroup";
    }




    @PostMapping("addGroups")
    public String checkGroupInfo(@Valid Groups groups, BindingResult bindingResult){
        if(groups.getDescription().length()>244){
            bindingResult.rejectValue("description", "error.groups", "*Must contain 255 characters");
        }

        if(bindingResult.hasErrors()){
            return "addGroup";
        }
        groupRepository.save(groups);
        return "redirect:groups";
    }

    @RequestMapping("groups")
    public String getGroupList(Model model){
        model.addAttribute("groups",groupRepository.findAll());
        return "groups";
    }

//    @RequestMapping("delete")
//    public String deleteGroup(@ModelAttribute Groups group){
//        groupRepository.delete(group);
//        return  "mainPage";
//    }




}
