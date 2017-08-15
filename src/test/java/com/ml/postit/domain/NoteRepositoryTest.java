package com.ml.postit.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NoteRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private NoteRepository toTest;

	@Test
	public void testGetNote() {
		
		Note note = toTest.findOne(1l);
		
		assertNotNull(note);
		assertEquals("note1", note.getMessage());
		
		note = toTest.findOne(3l);
		
		assertNull(note);
	}

	@Test
	public void testGetAllNotes() {
		
		List<Note> list = new ArrayList<Note>();
    	
		toTest.findAll().iterator().forEachRemaining(list::add);
		
		assertEquals(2, list.size());
		
	}
	
	@Test
	public void testAddNote() {
		Note newNote = new Note();
		newNote.setMessage("note3");
		
		toTest.save(newNote);
		
		assertNotNull(newNote.getId());
		assertEquals(3, toTest.count());
	}

	@Test
	public void testUpdateNote() {
		
		Note noteToUpdate = toTest.findOne(2l);
		noteToUpdate.setMessage("note2 updated");
		
		toTest.save(noteToUpdate);
		assertEquals(2, toTest.count());
	}
	
	@Test
	public void testDeleteNote() {
		
		toTest.delete(1l);
		
		assertEquals(1, toTest.count());
	}
}
