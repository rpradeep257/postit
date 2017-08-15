package com.ml.postit.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ml.postit.domain.Note;
import com.ml.postit.service.NoteService;

/**
 * Rest controller for mapping HTTP requests to service
 * 
 * @author PR
 *
 */
@RestController
@RequestMapping("/notes")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping(method = RequestMethod.GET, 
			value = "/{noteId}", 
			produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Note> getNote(@PathVariable("noteId") Long noteId) {
		
		Note note = noteService.getNote(noteId);
		
		if (note != null) {
			return new ResponseEntity<Note>(note, HttpStatus.OK);
		} else {
			return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
		}
    }
	
	@RequestMapping(method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Note> getNotes() {
		return noteService.getNotes();
    }
	
	@RequestMapping(method = RequestMethod.POST, 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }
	
	@RequestMapping(method = RequestMethod.PUT, 
			value = "/{noteId}", 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Note updateNote(@PathVariable("noteId") Long noteId, @RequestBody Note note) {
        return noteService.updateNote(noteId, note);
    }
	
	@RequestMapping(method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable("noteId") Long noteId) {
		noteService.deleteNote(noteId);
    }

}
