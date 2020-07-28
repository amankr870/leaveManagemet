/**
 * 
 */
package com.nous.test.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nous.test.dao.DAOUser;
import com.nous.test.dao.MyUserDetails;
import com.nous.test.dao.UserDTO;
import com.nous.test.dao.UserDao;

/**
 * @author Aman
 *
 */
@Component
public class JWTUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DAOUser user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		return MyUserDetails.build(user);
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				new ArrayList<>());
	}

	public DAOUser  save(UserDTO user) {
		DAOUser newUser = new DAOUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//		newUser.setRoles(user.);
		return userDao.save(newUser);
	}

}
