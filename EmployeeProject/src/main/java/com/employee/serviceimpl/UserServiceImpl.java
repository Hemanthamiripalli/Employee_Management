package com.employee.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.dtos.AuthResponseDto;
import com.employee.dtos.ResponseDto;
import com.employee.dtos.UserDto;
import com.employee.models.Users;
import com.employee.repository.UserRepository;
import com.employee.security.JwtService;
import com.employee.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	PasswordEncoder encode;

	@Autowired
	JwtService jwtService;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public ResponseDto userSignup(UserDto dto) {

		ResponseDto response = new ResponseDto();

		String email = dto.getEmail();
		String password = dto.getPassword();

		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {

			response.setMessage("Email and Password are required");
			response.setStatus("FAILED");
			response.setStatusCode("400");

			return response;
		}

		if (userRepo.existsByEmail(email)) {

			response.setMessage("Email Already Exists");
			response.setStatus("FAILED");
			response.setStatusCode("409");

			return response;
		}

		Users user = new Users();

		user.setEmail(email);
		user.setPassword(encode.encode(password));
		user.setPasswordText(password);

		userRepo.save(user);

		response.setMessage("User Signup Successful");
		response.setStatus("SUCCESS");
		response.setStatusCode("200");

		return response;
	}

	@Override
	public ResponseDto userLogin(UserDto dto) {

		ResponseDto response = new ResponseDto();

		String email = dto.getEmail();
		String password = dto.getPassword();

		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			response.setMessage("Email and Password are required");
			response.setStatus("FAILED");
			response.setStatusCode("400");
			return response;
		}

		Optional<Users> existingUser = userRepo.findByEmail(email);

		if (existingUser.isEmpty()) {
			response.setMessage("User Not Found");
			response.setStatus("FAILED");
			response.setStatusCode("404");
			return response;
		}

		boolean passwordMatch = encode.matches(password, existingUser.get().getPassword());

		if (!passwordMatch) {
			response.setMessage("Invalid Password");
			response.setStatus("FAILED");
			response.setStatusCode("401");
			return response;
		}

		AuthResponseDto authResponse = new AuthResponseDto();
		authResponse.setMessage("Login Successful");
		authResponse.setStatus("SUCCESS");
		authResponse.setStatusCode("200");
		authResponse.setToken(jwtService.generateToken(existingUser.get().getEmail()));
		authResponse.setTokenType("Bearer");

		return authResponse;
	}
}
