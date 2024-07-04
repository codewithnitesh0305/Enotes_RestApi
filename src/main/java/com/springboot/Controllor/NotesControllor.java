package com.springboot.Controllor;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Entity.Notes;
import com.springboot.Entity.User;
import com.springboot.Repository.UserRepository;
import com.springboot.Service.NotesServiceImp;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/Notes")
public class NotesControllor {

	@Autowired
	private NotesServiceImp serviceImp;
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/getNotes")
	public String getNotes() {
		return "Notes Controllor";
	}
	
	public User getuser(Principal principal , Model model) {
		String email = principal.getName();
		Optional<User> user = repository.findByEmail(email);
		return user.get();
	}
	
	@PostMapping("/saveNotes")
	public ResponseEntity<?> saveNotes(@RequestBody Notes notes, Principal principal, Model model, HttpSession session){
		notes.setUser(getuser(principal, model));
		notes.setDate(LocalDate.now());
		Notes saveNotes = serviceImp.saveNotes(notes);
		if(saveNotes != null) {
			return new ResponseEntity<Notes>(saveNotes,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Something went wrong...",HttpStatus.BAD_REQUEST);
		}		
	}
	
	@GetMapping("/allNotes")
	public ResponseEntity<?> getAllNotes(Principal principal , Model model){
		User user = getuser(principal, model);
		List<Notes> allNotes = serviceImp.getAllNotesByUser(user);
		return new ResponseEntity<List>(allNotes,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/deleteNotes/{id}")
	public ResponseEntity<?> deleteNotes(@PathVariable("id") int id){
		boolean deleteNotes = serviceImp.deleteNotes(id);
		if(deleteNotes) {
			return new ResponseEntity<String>("Delete Notes Successfully...",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Something went wrong...",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updateNotes/{id}")
	public ResponseEntity<?> updateNotes(@PathVariable("id") int id, @RequestBody Notes notes, Principal principal, Model model){
		User user = getuser(principal, model);
		notes.setDate(LocalDate.now());
		notes.setUser(user);
		Notes saveNotes = serviceImp.updateNotes(notes);
		if(saveNotes != null) {
			return new ResponseEntity<Notes>(saveNotes,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Something went wrong...",HttpStatus.BAD_REQUEST);
		}
	}
    
  
}
