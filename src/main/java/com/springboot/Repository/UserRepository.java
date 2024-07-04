package com.springboot.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public boolean existsByEmail(String email);
	public Optional<User> findByEmail(String email);
}
