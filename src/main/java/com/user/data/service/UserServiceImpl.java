package com.user.data.service;

import com.user.data.entity.AppUser;
import com.user.data.entity.Role;
import com.user.data.repository.RoleRepo;
import com.user.data.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Choudhury Subham on 13-05-2022
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser= userRepo.findByUserName(username);
        if (appUser==null){
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        }else {
            log.info("User inside database found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(appUser.getUserName(), appUser.getPassword(),authorities );
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("saving user into data base",user.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving role into data base" ,role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("adding role to user" ,userName,roleName);
           AppUser appUser = userRepo.findByUserName(userName);
           Role role = roleRepo.findByName(roleName);
           appUser.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String userName) {
        log.info("fetching user" ,userName);
        return userRepo.findByUserName(userName);
    }

    @Override
    public List<AppUser> getUser() {
        log.info("fetching all user");
        return userRepo.findAll();
    }


}
