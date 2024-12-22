package com.deba.eNotedProject.service;

import com.deba.eNotedProject.entity.User;
import com.deba.eNotedProject.reposotory.UserReposotory;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserReposotory userReposotory;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userReposotory.save(user);
    }

    @Override
    public boolean existEmailCheck(String email) {
        List<User> op = userReposotory.findAll();

        for(User u:op)
        {
            if(u.getEmail().equalsIgnoreCase(email))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public User findByEmail(String email) {
        List<User> userList = userReposotory.findAll();
       for(User u:userList)
       {
           if(u.getEmail().equalsIgnoreCase(email))
           {
               return u;
           }
       }
        return null;
    }

    public void removeSessionMessage()
    {
      HttpSession httpSession = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
      httpSession.removeAttribute("msg");
    }
}
