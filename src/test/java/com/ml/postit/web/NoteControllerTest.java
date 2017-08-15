package com.ml.postit.web;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ml.postit.domain.Note;
import com.ml.postit.service.NoteService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class NoteControllerTest {
	
	@MockBean
	private NoteService noteService;
	
	@Autowired
    private MockMvc mockMvc;

	@Test
	public void testGetNote() throws Exception {
		
		Note note = new Note();
		note.setId(1);
		note.setMessage("note1");
		
		when(this.noteService.getNote(1l)).thenReturn(note);
		
		mockMvc.perform(
				get("/notes/100"))
			.andExpect(status().isNotFound());
		
		mockMvc.perform(
				get("/notes/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("id", equalTo(1)))
			.andExpect(jsonPath("message", equalTo("note1")));
		
		verify(noteService, times(2)).getNote(anyLong());
			
	}

	@Test
	public void testGetNotes() throws Exception {
		
		Note note1 = new Note();
		note1.setId(1);
		note1.setMessage("note1");
		
		Note note2 = new Note();
		note2.setId(2);
		note2.setMessage("note2");
		
		when(this.noteService.getNotes()).thenReturn(Arrays.asList(note1, note2));
		
		mockMvc.perform(
				get("/notes"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)));
		
		verify(noteService, times(1)).getNotes();
		
	}

	@Test
	public void testAddNote() throws Exception {
		
		mockMvc.perform(
				post("/notes").content("{\"message\": \"new note\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
		
		verify(noteService, times(1)).addNote(any(Note.class));
			
	}

	@Test
	public void testUpdateNote() throws Exception {
		
		mockMvc.perform(
				put("/notes/1").content("{\"message\": \"updated note\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
		
		verify(noteService, times(1)).updateNote(anyLong(), any(Note.class));
	}

	@Test
	public void testDeleteNote() throws Exception {
		mockMvc.perform(
				delete("/notes/1"))
			.andExpect(status().isOk());
	}

}
