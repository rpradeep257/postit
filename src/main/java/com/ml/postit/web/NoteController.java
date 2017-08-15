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
	
	/**
	 * Get a note
	 * 
	 * @param noteId
	 * @return Http Response
	 */
	
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
	
	/**
	 * Get list of all notes
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Note>> getNotes() {
		return new ResponseEntity<List<Note>>(noteService.getNotes(), HttpStatus.OK);
    }
	
	/**
	 * 
	 * Add a new note
	 * 
	 * @param note
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        Note addedNote =  noteService.addNote(note);
        return new ResponseEntity<Note>(addedNote, HttpStatus.OK);
    }
	
	/**
	 * 
	 * Update a existing note
	 * 
	 * @param noteId
	 * @param note
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, 
			value = "/{noteId}", 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Note> updateNote(@PathVariable("noteId") Long noteId, @RequestBody Note note) {
        Note updateNote = noteService.updateNote(noteId, note);
        return new ResponseEntity<Note>(updateNote, HttpStatus.OK);
    }
	
	/**
	 * 
	 * Delete a note
	 * 
	 * @param noteId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE,
			value = "/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable("noteId") Long noteId) {
		noteService.deleteNote(noteId);
		return new ResponseEntity<>(HttpStatus.OK);
    }

}
