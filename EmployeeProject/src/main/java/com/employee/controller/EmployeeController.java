package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dtos.EmployeeDto;
import com.employee.dtos.EmployeeResponseDto;
import com.employee.dtos.ResponseDto;
import com.employee.service.EmployeeService;

@RequestMapping("/emp")
@RestController
@CrossOrigin(origins = "http://localhost:3030")
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@PostMapping("/saveemp")
	public ResponseEntity<ResponseDto> saveEmp(@RequestBody EmployeeDto dto) {
		ResponseDto res = new ResponseDto();

		if (dto != null) {

			String message = empService.saveEmp(dto);

			if (message.equals("Success")) {

				res.setMessage("Employee Created Successfully");

				res.setStatus("Success");

				res.setStatusCode(HttpStatus.OK.toString());

				return new ResponseEntity<>(res, HttpStatus.OK);
			}

			else if (message.equals("Email Already Exists")) {

				res.setMessage("Email Already Exists");

				res.setStatus("Failed");

				res.setStatusCode(HttpStatus.BAD_REQUEST.toString());

				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}

			else {

				res.setMessage("Employee Not Created");

				res.setStatus("Failed");

				res.setStatusCode(HttpStatus.BAD_REQUEST.toString());

				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
		}

		res.setMessage("Invalid Request");

		res.setStatus("Failed");

		res.setStatusCode(HttpStatus.BAD_REQUEST.toString());

		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getall")
	public ResponseEntity<EmployeeResponseDto> getAllEmp() {
		EmployeeResponseDto res = new EmployeeResponseDto();
		List<EmployeeDto> list = empService.getAll();
		if (list != null) {
			res.setMessage("List retrived Successfully");
			res.setStatus("Success");
			res.setStatusCode(HttpStatus.OK.toString());
			res.setList(list);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			res.setMessage("Failed");
			res.setStatus("Failed");
			res.setStatusCode(HttpStatus.NO_CONTENT.toString());
			res.setList(list);
			return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<EmployeeResponseDto> getById(@PathVariable Long id) {
		EmployeeResponseDto res = new EmployeeResponseDto();
		EmployeeDto dto = empService.getbyId(id);
		if (dto != null) {
			res.setMessage("Data Retrived SuccesFully");
			res.setStatus("Success");
			res.setStatusCode(HttpStatus.OK.toString());
			res.setEmp(dto);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			res.setMessage("Failed");
			res.setStatus("Failed");
			res.setStatusCode(HttpStatus.NO_CONTENT.toString());
			res.setEmp(dto);
			return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
		}

	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<ResponseDto> deleteById(@PathVariable Long id) {

		ResponseDto res = new ResponseDto();
		String message = empService.deletebyId(id);
		if (message.equals("Success")) {
			res.setMessage("Deleted SuccessFully");
			res.setStatus("Success");
			res.setStatusCode(HttpStatus.OK.toString());
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			res.setMessage("Failed");
			res.setStatus("Failed");
			res.setStatusCode(HttpStatus.BAD_REQUEST.toString());
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<ResponseDto> updatebyId(@RequestBody EmployeeDto dto, @PathVariable Long id) {
		ResponseDto res = new ResponseDto();
		String message = empService.updateById(dto, id);

		if (message.equals("Success")) {
			res.setMessage("Employee updated SuccessFully");
			res.setStatus("Success");
			res.setStatusCode(HttpStatus.OK.toString());
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			res.setMessage("Employee Not updated");
			res.setStatus("Failed");
			res.setStatusCode(HttpStatus.BAD_REQUEST.toString());
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getbyemail/{email}")
	public ResponseEntity<EmployeeResponseDto> getByEmail(@PathVariable String email) {

		EmployeeResponseDto res = new EmployeeResponseDto();

		EmployeeDto dto = empService.getByEmail(email);

		if (dto != null) {

			res.setMessage("Employee Found");
			res.setStatus("Success");
			res.setStatusCode(HttpStatus.OK.toString());
			res.setEmp(dto);

			return new ResponseEntity<>(res, HttpStatus.OK);

		} else {

			res.setMessage("Employee Not Found");
			res.setStatus("Failed");
			res.setStatusCode(HttpStatus.NOT_FOUND.toString());

			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getbyname/{name}")
	public ResponseEntity<EmployeeResponseDto> getByName(@PathVariable String name) {

		EmployeeResponseDto res = new EmployeeResponseDto();

		EmployeeDto dto = empService.getByName(name);

		if (dto != null) {

			res.setMessage("Employee Found");
			res.setStatus("Success");
			res.setStatusCode(HttpStatus.OK.toString());
			res.setEmp(dto);

			return new ResponseEntity<>(res, HttpStatus.OK);

		} else {

			res.setMessage("Employee Not Found");
			res.setStatus("Failed");
			res.setStatusCode(HttpStatus.NOT_FOUND.toString());

			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
	}

}
