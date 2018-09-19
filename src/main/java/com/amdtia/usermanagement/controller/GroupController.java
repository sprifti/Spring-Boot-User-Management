package com.amdtia.usermanagement.controller;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.repository.GroupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

import java.util.Optional;

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


        if(groups.getGroupName().length()<4){
            bindingResult.rejectValue("groupName", "error.groups","*Must have more than 4 characters");
        }
        if(groupRepository.findByGroupName(groups.getGroupName())!=null){
            bindingResult.rejectValue("groupName", "error.groups","*This group already exists");
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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteGroup(@RequestParam(name="Id")Long Id) {
        groupRepository.deleteById(Id);
        return  "redirect:groups";
    }



    @PostMapping("update")
    public String updateGroup(@RequestParam(name="groupName")String groupName,Model model){

          model.addAttribute("groups",groupRepository.findByGroupName(groupName));

        return "addGroup";
    }




}
