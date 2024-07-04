package com.springboot.Controllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Entity.User;
import com.springboot.Model.JwtRequest;
import com.springboot.Model.JwtResponce;
import com.springboot.Security.JwtHelper;
import com.springboot.Service.UserServiceImp;

@RestController
@RequestMapping("/User")
public class HomeControllor {

	@Autowired
	private UserServiceImp userServiceImp;
	
	@Autowired
    private JwtHelper helper;

	@Autowired
	private UserDetailsService userDetailsService;
	
	

	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveUsers(@RequestBody User user){
		
		User saveUser = userServiceImp.saveUser(user);
		return new ResponseEntity<User>(saveUser,HttpStatus.CREATED);
	}
	

    @PostMapping("/login")
    public ResponseEntity<JwtResponce> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
        JwtResponce response = JwtResponce.builder()
                .token(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
    
}
