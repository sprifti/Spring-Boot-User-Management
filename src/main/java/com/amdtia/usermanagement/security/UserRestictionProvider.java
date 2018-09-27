package com.amdtia.usermanagement.security;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.model.Permissions;
import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRestictionProvider {



//    private UserRepository userRepository;
//    //finds the name of the user who is logged in.
////    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////    String name = auth.getName();
//
//
//
//    public List<Permissions> permissionsOfUser(User user){
//        List<Permissions> permissions = new ArrayList<>();
//        for (Groups groups : user.getGroups()) {
//            for (Permissions permission: groups.getPermissions()) {
//                if(!permissions.contains(permission)){
//                    permissions.add(permission);
//                }
//            }
//        }
//        return permissions;
//    }






}
