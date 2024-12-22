package com.deba.eNotedProject.reposotory;

import com.deba.eNotedProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReposotory extends JpaRepository<User,Integer> {

}
