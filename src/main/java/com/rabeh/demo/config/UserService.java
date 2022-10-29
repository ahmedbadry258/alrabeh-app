package com.rabeh.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rabeh.demo.model.User;
import com.rabeh.demo.repositories.UserRepository;

public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found with email "+user.getEmail());
		}
		return  new com.rabeh.demo.config.UserDetails(user);
		
	}

}
