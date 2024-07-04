package com.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Entity.Notes;
import com.springboot.Entity.User;
import java.util.List;


public interface NotesRepository extends JpaRepository<Notes, Integer>{

	public Notes findById(int id);
	public List<Notes> findByUser(User user);
	
}
