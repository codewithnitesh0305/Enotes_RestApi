package com.springboot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.CustomeException.BusinessException;
import com.springboot.CustomeException.EmptyInputFieldException;
import com.springboot.Entity.User;
import com.springboot.Repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		User saveUser = null;
		if (user.getEmail() == null || user.getEmail().isEmpty()) {
	        throw new EmptyInputFieldException("601", "Email field is empty", "email");
	    } else if (user.getName() == null || user.getName().isEmpty()) {
	        throw new EmptyInputFieldException("601", "Name field is empty", "name");
	    } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
	        throw new EmptyInputFieldException("601", "Password field is empty", "password");
	    } else {
	        if (existUserEmail(user.getEmail())) {
	            throw new BusinessException("602", "Email already exists", "email");
	        }
	        user.setPassword(encoder.encode(user.getPassword()));
	        saveUser = repository.save(user);
	    }
		 
		return saveUser;
	}

	@Override
	public boolean existUserEmail(String email) {
		// TODO Auto-generated method stub
		Boolean existEmail = repository.existsByEmail(email);
		return existEmail;
	}

}
