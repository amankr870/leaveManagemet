package com.nous.test.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.print.attribute.DocAttributeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MyUserDetails implements UserDetails {

	private DAOUser user;
	
	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
	
	
	public MyUserDetails(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	public MyUserDetails(DAOUser user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		
		return authorities;
	}
	
	

//	@Override
//	public String getPassword() {
//		return user.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return user.getUsername();
//	}
//
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public DAOUser getUser() {
		return user;
	}
	
	public void setUser(DAOUser user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return user.getPassword();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public static MyUserDetails build(DAOUser user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRolename()))
				.collect(Collectors.toList());

		return new MyUserDetails(user);
	}
	
	
//	public static MyUserDetails build(DAOUser user) {
//		List<GrantedAuthority> authorities = user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
//		return new MyUserDetails(user);
//	}

}
