* Application uses a inmemory database (H2)

* Two records are loaded in database

* There are no validation, example: note exists, note size or duplicate note

* There is no end-to-end integration test

* To run the application

	git clone https://github.com/rpradeep257/postit.git
	cd postit
	mvn spring-boot:run
	
	or
	
	git clone https://github.com/rpradeep257/postit.git
	cd postit
	mvn package
	java -jar target/postit-0.0.1-SNAPSHOT.jar
	
* To test

	Get all notes
	
	URL: http://localhost:8080/notes
	HTTP: GET
		
	Get a note
	
	URL: http://localhost:8080/notes/<noteId>
	HTTP: GET
	
	Add a note
	
	URL: http://localhost:8080/notes
	HTTP: POST
	Content-Type: application/json
	Body: {"message": "new note"}
	
	Update a note
	
	URL: http://localhost:8080/notes/<noteId>
	HTTP: PUT
	Content-Type: application/json
	Body: {"message": "updated note"}
	
	Delete a note
	
	URL: http://localhost:8080/notes/<noteId>
	HTTP: DELETE
	
	
AAAAAAA	

bbbbbbbb:wq


ccccccc
