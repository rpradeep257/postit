package com.ml.postit.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * Note repository to perform CRUD operations. Implementation provided by Spring Boot
 * 
 * @author PR
 *
 */
public interface NoteRepository extends CrudRepository<Note, Long> {
	

}
