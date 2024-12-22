package com.deba.eNotedProject.config;

import com.deba.eNotedProject.entity.User;
import com.deba.eNotedProject.reposotory.UserReposotory;
import com.deba.eNotedProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserService implements UserDetailsService {
    private final UserService userService;
    @Autowired
    public CustomeUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        if(user  == null)
        {
            throw  new UsernameNotFoundException("User not found");
        }
        else
        {
            return new CustomUser(user);
        }

    }
}
