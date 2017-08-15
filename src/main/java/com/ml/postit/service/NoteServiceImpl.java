package com.ml.postit.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ml.postit.domain.Note;
import com.ml.postit.domain.NoteRepository;

/**
 * 
 * Implementation of NoteService interface for performing CRUD operations
 * on Note
 * 
 * @author PR
 *
 */

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(NoteServiceImpl.class);
	
	@Autowired
	private NoteRepository noteRepository;
	
    public Note getNote(Long noteId) {
    	LOGGER.info("Retrieving note with id: {}", noteId);
       
    	return noteRepository.findOne(noteId);
    }
	
    public List<Note> getNotes() {
    	LOGGER.info("Retrieving all notes");
    	
    	List<Note> list = new ArrayList<Note>();
    	
    	noteRepository.findAll().iterator().forEachRemaining(list::add);
        
    	return list;
    }
	
    public Note addNote(Note newNote) {
    	
    	LOGGER.info("Adding new note: {}", newNote);
    	
        return noteRepository.save(newNote);
    }
	
    public Note updateNote(Long noteId, Note newNote) {
    	
    	LOGGER.info("Updating note with id {} and setting message to {}", noteId, newNote.getMessage());
    	
    	Note existingNote = noteRepository.findOne(noteId);
    	existingNote.setMessage(newNote.getMessage());
        
    	return noteRepository.save(existingNote);
    }
	
    public void deleteNote(Long noteId) {
    	
    	LOGGER.info("Deleting notew with id : {}", noteId);
    	
    	noteRepository.delete(noteId);
    }

}
