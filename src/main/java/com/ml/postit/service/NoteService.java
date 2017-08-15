package com.ml.postit.service;

import java.util.List;

import com.ml.postit.domain.Note;

/**
 * Service interface for CRUD operations on Note
 * 
 * @author PR
 *
 */
public interface NoteService {
	
	
	/**
	 * Get a note using the provided id
	 * 
	 * @param noteId
	 * @return
	 */
	Note getNote(Long noteId);
	
	/**
	 * Get all notes in the system
	 * 
	 * TODO: Pagination
	 * 
	 * @return
	 */
    List<Note> getNotes();
	
    /**
     * 
     * Add a new note
     * 
     * @param newNote
     * @return
     */
    Note addNote(Note newNote) ;
	
    /**
     * Update a note using the id and new note content
     * 
     * @param noteId
     * @param newNote
     * @return
     */
    Note updateNote(Long noteId, Note newNote) ;
	
    /**
     * Delete a note using the provided id
     * 
     * @param id
     */
    void deleteNote(Long id);

}
