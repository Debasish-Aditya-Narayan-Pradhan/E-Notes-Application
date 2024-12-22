package com.deba.eNotedProject.reposotory;

import com.deba.eNotedProject.entity.Notes;
import com.deba.eNotedProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoteRepo extends JpaRepository<Notes,Integer> {
    public List<Notes> findByUser(User user);
}
