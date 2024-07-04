package com.springboot.Entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	/*
	 * @Override public boolean isAccountNonExpired() { // TODO Auto-generated
	 * method stub return true; }
	 * 
	 * @Override public boolean isAccountNonLocked() { // TODO Auto-generated method
	 * stub return true; }
	 * 
	 * @Override public boolean isCredentialsNonExpired() { // TODO Auto-generated
	 * method stub return true; }
	 * 
	 * @Override public boolean isEnabled() { // TODO Auto-generated method stub
	 * return true; }
	 */
}
