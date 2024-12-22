package com.deba.eNotedProject.service;

import com.deba.eNotedProject.entity.Notes;
import com.deba.eNotedProject.entity.User;
import com.deba.eNotedProject.reposotory.NoteRepo;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    private NoteRepo noteRepo;

    public Notes saveNotes(Notes notes)
    {
        return noteRepo.save(notes);
    }

    public Notes getNotesById(Integer id)
    {
        Optional<Notes> op = noteRepo.findById(id);
        if(op.isPresent())
        {
            return op.get();
        }
        return null;
    }

    public List<Notes> getNotedByUser(User user)
    {
        return noteRepo.findByUser(user);
    }

    public Notes updateNotes(Notes notes)
    {
        return noteRepo.save(notes);
    }
    public boolean deleteNoteById(Integer id)
    {
        Optional<Notes> op = noteRepo.findById(id);
        if(op.isPresent())
        {
            Notes notes = op.get();
            noteRepo.delete(notes);
            return true;
        }

        return false;
    }
}
