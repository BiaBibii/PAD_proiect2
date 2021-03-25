package com.cantina.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/api")  
public class HomeController {
	
	@RequestMapping("/ceva")
	public String afisezCeva() {
		return "Ceva";
	}

}
