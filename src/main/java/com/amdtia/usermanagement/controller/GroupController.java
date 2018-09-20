package com.amdtia.usermanagement.controller;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.model.Permissions;
import com.amdtia.usermanagement.repository.GroupRepository;
import com.amdtia.usermanagement.repository.PermissionRepository;
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
    PermissionRepository permissionRepository;



    public GroupController(GroupRepository groupRepository, PermissionRepository permissionRepository) {
        this.groupRepository = groupRepository;
        this.permissionRepository = permissionRepository;
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

    @PostMapping("viewDetails")
    public String viewDetails(@RequestParam(name="grName")String grName, Model model,Model model1){
        model.addAttribute("groups",groupRepository.findByGroupName(grName));
        model1.addAttribute("permissions",permissionRepository.findAll());
        return "groupDetails";
    }


    @PostMapping("addGroups")
    public String saveOrUpdate(@RequestParam(name="id")Long id,@Valid Groups groups,BindingResult bindingResult ){
        if(id==null){
            if(validateRegister(groups,bindingResult)){
                groupRepository.save(groups);
                return "redirect:groups";
            }else{
                return "addGroup";
             }
          }
         else
        {
            if(validateUpdate(groups,bindingResult)){
                groupRepository.save(groups);
                return "redirect:groups";
            } else
                return "addGroup";

        }


    }




    public boolean validateRegister(@Valid Groups groups, BindingResult bindingResult){


        if(groups.getGroupName().length()<4){
            bindingResult.rejectValue("groupName", "error.groups","*Must have more than 4 characters");
        }
        if(groupRepository.findByGroupName(groups.getGroupName())!=null){
            bindingResult.rejectValue("groupName", "error.groups","*This group already exists");
        }
        if(bindingResult.hasErrors()){
            return false;
        }
        return true;
    }



    public boolean validateUpdate(@Valid Groups groups, BindingResult bindingResult){

        Optional<Groups> group = groupRepository.findById(groups.getId());

        if(groups.getGroupName().length()<4){
            bindingResult.rejectValue("groupName", "error.groups","*Must have more than 4 characters");
        }
        if(!group.get().getGroupName().equals(groups.getGroupName()) && groupRepository.findByGroupName(groups.getGroupName())!=null ){
            bindingResult.rejectValue("groupName", "error.groups","*This group already exists");
        }
        if(bindingResult.hasErrors()){
            return false;
        }

        return true;
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
