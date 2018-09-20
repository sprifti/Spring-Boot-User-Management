package com.amdtia.usermanagement.controller;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.model.Permissions;
import com.amdtia.usermanagement.repository.PermissionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PermissionController implements WebMvcConfigurer {

    PermissionRepository permissionRepository;

    public PermissionController(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("permissions").setViewName("permissions");
    }
    @RequestMapping("showPermissions")
    public String showFormPermissions(Model model){
        Permissions permissions = new Permissions();
        model.addAttribute("permissions",permissions);
        return "addPermission";
    }

    @RequestMapping("permissions")
    public String showPermissions(Model model){
       model.addAttribute("permissions",permissionRepository.findAll());
       return "permissions";
   }

    @PostMapping("updatePermission")
    public String updatePermission(@RequestParam(name="title")String title,Model model){

        model.addAttribute("permissions",permissionRepository.findByTitle(title));

        return "addPermission";
    }

    @PostMapping("deletePermission")
    public String deletePermission(@RequestParam(name="id")Long id,Model model){
        permissionRepository.deleteById(id);
        return "redirect:permissions";
    }


    @PostMapping("addPermissions")
    public String saveOrUpdate(@RequestParam(name="id")Long id,@Valid Permissions permissions,BindingResult bindingResult ){
        if(id==null){
            if(validateRegister(permissions,bindingResult)){
                permissionRepository.save(permissions);
                return "redirect:permissions";
            }else{
                return "addPermission";
            }
        }
        else
        {
            if(validateUpdate(permissions,bindingResult)){
                permissionRepository.save(permissions);
                return "redirect:permissions";
            } else
                return "addPermission";

        }


    }




    public boolean validateRegister(@Valid Permissions permissions, BindingResult bindingResult){


        if(permissions.getTitle().length()<4){
            bindingResult.rejectValue("title", "error.permissions","*Must have more than 4 characters");
        }
        if(permissionRepository.findByTitle(permissions.getTitle())!=null){
            bindingResult.rejectValue("title", "error.permissions","*This permission already exists");
        }
        if(bindingResult.hasErrors()){
            return false;
        }
        return true;
    }



    public boolean validateUpdate(@Valid Permissions permissions, BindingResult bindingResult){

        Optional<Permissions> permission = permissionRepository.findById(permissions.getId());

        if(permissions.getTitle().length()<4){
            bindingResult.rejectValue("title", "error.permissions","*Must have more than 4 characters");
        }
        if(!permission.get().getTitle().equals(permissions.getTitle()) && permissionRepository.findByTitle(permissions.getTitle())!=null ){
            bindingResult.rejectValue("title", "error.permissions","*This permission already exists");
        }
        if(bindingResult.hasErrors()){
            return false;
        }

        return true;
    }


}
