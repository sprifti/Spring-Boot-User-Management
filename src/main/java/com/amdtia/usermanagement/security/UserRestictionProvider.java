package com.amdtia.usermanagement.security;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.model.Paths;
import com.amdtia.usermanagement.model.Permissions;
import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.PermissionRepository;
import com.amdtia.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRestictionProvider {
    @Autowired
    private PermissionRepository permissionRepository;
    public boolean findURL(String url){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ArrayList<String> roles = new ArrayList<>();
       roles.add(auth.getAuthorities().toString());

        for(int i=0; i<roles.size(); i++){

            if(roles.get(i).contains("ADMIN")){
                Permissions permissions = permissionRepository.findByTitle("ADMIN");
                List<Paths> paths = new ArrayList<>();

                for (Paths path : permissions.getPaths()) {
                    if(path.getPath().equals(url)){
                        return true;
                    }
                }


            }
            else{
                if(roles.get(i).contains("EMPLOYEES")){
                    Permissions permissions = permissionRepository.findByTitle("EMPLOYEES");
                    List<Paths> paths = new ArrayList<>();

                    for (Paths path : permissions.getPaths()) {
                        if(path.getPath().equals(url)){
                            return true;
                        }
                    }


                }

            }
        }
        return false;
    }

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
