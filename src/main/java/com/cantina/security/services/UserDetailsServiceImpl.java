package com.cantina.security.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cantina.model.User;
import com.cantina.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null)
		throw new UsernameNotFoundException("Username not founded!");

		return UserDetailsImpl.build(user);
	}

}
