package com.cantina;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cantina.model.security.ERole;
import com.cantina.model.security.Role;
import com.cantina.payload.request.SignupRequest;
import com.cantina.service.UserService;


@SpringBootApplication
public class CantinaUptApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CantinaUptApplication.class, args);
	}

}