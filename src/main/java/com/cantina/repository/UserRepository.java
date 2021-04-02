package com.cantina.repository;

import org.springframework.data.repository.CrudRepository;

import com.cantina.model.User;

public interface UserRepository extends CrudRepository<User,Long>{
	
	User findByUsername(String user);
	
	User findByEmail(String email);

}
