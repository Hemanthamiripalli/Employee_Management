package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dtos.ResponseDto;
import com.employee.dtos.UserDto;
import com.employee.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3030")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<ResponseDto> signup(@RequestBody UserDto dto) {

		return new ResponseEntity<ResponseDto>(userService.userSignup(dto), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@RequestBody UserDto dto) {
	    return new ResponseEntity<>(userService.userLogin(dto), HttpStatus.OK);
	}

}