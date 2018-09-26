package com.amdtia.usermanagement.security;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.model.Permissions;
import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRestictionProvider {


    private UserRepository userRepository;





    public List<Permissions> permissionsOfUser(User user){
        List<Permissions> permissions = new ArrayList<>();
        for (Groups groups : user.getGroups()) {
            for (Permissions permission: groups.getPermissions()) {
                if(!permissions.contains(permission)){
                    permissions.add(permission);
                }
            }
        }
        return permissions;
    }






}