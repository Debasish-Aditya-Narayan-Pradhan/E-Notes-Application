package com.deba.eNotedProject.service;

import com.deba.eNotedProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



public interface UserService {
    User saveUser(User user);

    boolean existEmailCheck(String email);

    User findByEmail(String email);

}
