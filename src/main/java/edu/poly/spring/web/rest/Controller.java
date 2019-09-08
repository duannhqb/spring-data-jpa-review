package edu.poly.spring.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@RequestMapping("")
	public String defaultMapping() {
		return "Hello";
	}
	
}
