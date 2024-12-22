package com.deba.eNotedProject.controller;

import com.deba.eNotedProject.entity.Notes;
import com.deba.eNotedProject.entity.User;
import com.deba.eNotedProject.reposotory.UserReposotory;
import com.deba.eNotedProject.service.NoteService;
import com.deba.eNotedProject.service.UserService;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.servlet.http.HttpSession;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    @ModelAttribute
    public User getUser(Principal p, Model m)
    {
        String email= p.getName();
        User user = userService.findByEmail(email);
        m.addAttribute("user",user);

        return user;
    }

    @GetMapping("/addNotes")
    public String addNotes()
    {
        return "add_notes";
    }

    @GetMapping("/viewNotes")
    public String viewNotes(Model m,Principal p)
    {
        User user = getUser(p,m);
        List<Notes> notes = noteService.getNotedByUser(user);
        m.addAttribute("noteList",notes);
        m.addAttribute("size",notes.size());
        return "view_notes";
    }

    @GetMapping("/{id}")
    public void delete(@PathVariable Integer id)
    {
        noteService.deleteNoteById(id);
    }
    @GetMapping("/editNotes/{id}")
    public String editNotes(@PathVariable Integer id,Model m)
    {
       Notes notes =  noteService.getNotesById(id);
       m.addAttribute("n",notes);
       return "edit_notes";
    }

    @PostMapping("/saveNotes")
    public String saveNotes(@ModelAttribute Notes notes, HttpSession session,Principal p,Model m)
    {
        notes.setDate(LocalDate.now());
        notes.setUser(getUser(p,m));
        Notes notes1 = noteService.saveNotes(notes);
        if(notes1 != null)
        {
            session.setAttribute("msg","Note has been Saved");
        }
        else
        {
            session.setAttribute("msg","Something wrong on server");
        }
        return "redirect:/user/addNotes";
    }

    @PostMapping("/updateNotes")
    public String updateNotes(@ModelAttribute Notes notes, HttpSession session,Principal p,Model m)
    {
        notes.setDate(LocalDate.now());
        notes.setUser(getUser(p,m));
        Notes notes1 = noteService.saveNotes(notes);
        if(notes1 != null)
        {
            session.setAttribute("msg","Note updated successfully");
        }
        else
        {
            session.setAttribute("msg","Something wrong on server");
        }
        return "redirect:/user/viewNotes";
    }

    @GetMapping("/deleteNotes/{id}")
    public String deleteNotes(@ModelAttribute Notes notes, HttpSession session,Principal p,Model m)
    {
        notes.setDate(LocalDate.now());
        notes.setUser(getUser(p,m));
        boolean notes1 = noteService.deleteNoteById(notes.getId());
        if(notes1)
        {
            session.setAttribute("msg","Note updated successfully");
        }
        else
        {
            session.setAttribute("msg","Something wrong on server");
        }
        return "redirect:/user/viewNotes";
    }
}
