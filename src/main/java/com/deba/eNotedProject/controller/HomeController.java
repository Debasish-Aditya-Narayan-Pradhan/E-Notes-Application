package com.deba.eNotedProject.controller;

import com.deba.eNotedProject.entity.User;
import com.deba.eNotedProject.service.UserService;
import com.deba.eNotedProject.service.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }


    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session)
    {
        boolean f = userService.existEmailCheck(user.getEmail());
        if(f)
        {
            session.setAttribute("msg","email id already exist");
        }
        else
        {
            User user1 = userService.saveUser(user);
            if(user1 != null)
            {
                session.setAttribute("msg","Register success");
            }
            else
            {
                session.setAttribute("msg","Someting ");
            }
        }

        return "redirect:/register";
    }

    @PostMapping("/userLogin")
    public String userLogin()
    {
        return "";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }


}
