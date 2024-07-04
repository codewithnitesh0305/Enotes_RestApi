package com.springboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.springboot.CustomeException.EmptyInputFieldException;
import com.springboot.Entity.Notes;
import com.springboot.Entity.User;
import com.springboot.Repository.NotesRepository;

@Service
public class NotesServiceImp implements NotesService{

	@Autowired
	private NotesRepository repository;
	
	@Override
	public Notes saveNotes(Notes notes) {
		// TODO Auto-generated method stub
		if(ObjectUtils.isEmpty(notes)) {
			throw new EmptyInputFieldException("603", "Some field the empty");
		}
		return repository.save(notes);
	}

	@Override
	public Notes getNotesById(int id) {
		// TODO Auto-generated method stub
		Notes note = repository.findById(id);
		return note;
	}

	@Override
	public List<Notes> getAllNotes(Notes notes) {
		// TODO Auto-generated method stub
		List<Notes> getAllNotes = repository.findAll();
		return getAllNotes;
	}

	@Override
	public Notes updateNotes(Notes notes) {
		// TODO Auto-generated method stub
		return repository.save(notes);
	}

	@Override
	public boolean deleteNotes(int id) {
		// TODO Auto-generated method stub
		Notes notes = repository.findById(id);
		if(notes != null) {
			repository.delete(notes);
			return true;
		}
		return false;
	}

	@Override
	public List<Notes> getAllNotesByUser(User user) {
		// TODO Auto-generated method stub
		List<Notes> notes = repository.findByUser(user);
		return notes;
	}

}
