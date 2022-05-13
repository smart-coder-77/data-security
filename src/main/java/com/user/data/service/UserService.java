package com.user.data.service;

import com.user.data.entity.AppUser;
import com.user.data.entity.Role;

import java.util.List;

/**
 * @author Choudhury Subham on 13-05-2022
 */
public interface UserService {

    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void  addRoleToUser(String userName,String roleName);
    AppUser getUser(String userName);
    List<AppUser> getUser();
}
