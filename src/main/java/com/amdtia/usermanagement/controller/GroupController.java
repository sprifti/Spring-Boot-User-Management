package com.amdtia.usermanagement.controller;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.repository.GroupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.time.zone.ZoneRulesProvider.refresh;

@Controller
public class GroupController {
    GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }



    @RequestMapping("showFormGroup")
        public String showFormGroup(Model model){
        Groups groups = new Groups();
        model.addAttribute("groups",groups);
        return "addGroup";
    }

    @PostMapping("groups")
    public String addGroup(@ModelAttribute Groups group,Model model){
        groupRepository.save(group);

     return getGroupList(model);
    }

    @RequestMapping("groups")
    public String getGroupList(Model model){
        model.addAttribute("groups",groupRepository.findAll());
        return "groups";
    }




}
