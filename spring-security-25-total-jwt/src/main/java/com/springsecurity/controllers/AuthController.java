package com.springsecurity.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.entities.Otp;
import com.springsecurity.entities.User;
import com.springsecurity.services.UserService;

@RestController
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/add")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@PostMapping("/user/auth")
	public void auth(@RequestBody User user) {
		userService.auth(user);
	}
	
	@PostMapping("/otp/check")
	public void check(@RequestBody Otp otp, HttpServletResponse response) {
		if(userService.check(otp)) {
			response.setStatus(HttpStatus.OK.value());
		} else {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			
		}
	}
}
