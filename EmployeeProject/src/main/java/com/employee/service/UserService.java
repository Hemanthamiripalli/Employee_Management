package com.employee.service;

import com.employee.dtos.ResponseDto;
import com.employee.dtos.UserDto;

public interface UserService {
	
	ResponseDto userSignup(UserDto dto);
	ResponseDto userLogin(UserDto dto);
}
