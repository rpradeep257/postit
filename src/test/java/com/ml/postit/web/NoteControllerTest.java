package com.ml.postit.web;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ml.postit.domain.Note;
import com.ml.postit.service.NoteService;

@RunWith(SpringRunner.class)
//@WebMvcTest(NoteController.class)
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
			.andExpect(status().isOk());
			
	}

	@Test
	public void testGetNotes() {
		
	}

	@Test
	public void testAddNote() {
	}

	@Test
	public void testUpdateNote() {
	}

	@Test
	public void testDeleteNote() {
	}

}
