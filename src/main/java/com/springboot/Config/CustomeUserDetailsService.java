package com.springboot.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springboot.Entity.User;
import com.springboot.Repository.UserRepository;

@Component
public class CustomeUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//Load User from database		
			User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));	
			return  user;
	}
}
