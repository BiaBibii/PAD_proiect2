package com.cantina.repository;

import org.springframework.data.repository.CrudRepository;

import com.cantina.model.security.Role;

public interface RoleRepository extends CrudRepository <Role,Long>{
	
	

}
