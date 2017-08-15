package com.ml.postit.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ml.postit.domain.Note;
import com.ml.postit.domain.NoteRepository;

/**
 * Unit test for NoteServiceImpl with mocked repository
 * 
 * @author PR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteServiceImplTest {

	@MockBean
	private NoteRepository noteRepository;
	
	@Autowired
	private NoteService toTest;
	
	@Test
	public void testGetNote() {
		
		// Mock findOne method
		when(this.noteRepository.findOne(anyLong())).thenReturn(new Note("note1"));
		
		// Invoke service call
		Note note = toTest.getNote(5l);
		
		// Checks
		assertNotNull(note);
		assertEquals("note1", note.getMessage());
		
		verify(noteRepository, times(1)).findOne(5l);
	}

	@Test
	public void testGetNotes() {
		
		// Mock findAll method
		when(this.noteRepository.findAll()).thenReturn(Arrays.asList(new Note("note1"), new Note("note2")));
		
		// Invoke service call
		List<Note> notes = toTest.getNotes();
		
		// Checks
		assertNotNull(notes);
		assertEquals(2, notes.size());
		
		verify(noteRepository, times(1)).findAll();
	}

	@Test
	public void testAddNote() {
		
		// Mock save method to return passed argument
		when(this.noteRepository.save(any(Note.class))).thenAnswer(new Answer<Object>() {
		    public Object answer(InvocationOnMock invocation) {
		        return invocation.getArguments()[0];
		    }
		});
		
		// Invoke servoce call
		Note note = toTest.addNote(new Note("new note"));
		
		// Checks
		assertNotNull(note);
		assertEquals("new note", note.getMessage());
		
		verify(noteRepository, times(1)).save(any(Note.class));
	}

	@Test
	public void testUpdateNote() {
		
		Note note = new Note();
		note.setId(1l);
		note.setMessage("note");
		
		// Mock findOne method
		when(this.noteRepository.findOne(anyLong())).thenReturn(note);
		
		// Mock save method
		when(this.noteRepository.save(any(Note.class))).thenAnswer(new Answer<Object>() {
		    public Object answer(InvocationOnMock invocation) {
		        return invocation.getArguments()[0];
		    }
		});
		
		// Invoke service call
		Note updatedNote = toTest.updateNote(1l, new Note("note updated"));
		
		// Checks
		assertNotNull(updatedNote);
		assertEquals(1l, updatedNote.getId());
		assertEquals("note updated", updatedNote.getMessage());
		
		verify(noteRepository, times(1)).findOne(anyLong());
		verify(noteRepository, times(1)).save(any(Note.class));
	}

	@Test
	public void testDeleteNote() {
		
		// Invoke service call
		toTest.deleteNote(1L);
		
		// Checks
		verify(noteRepository, times(1)).delete(anyLong());
		
	}

}
