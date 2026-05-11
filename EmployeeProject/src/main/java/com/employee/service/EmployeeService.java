package com.employee.service;

import java.util.List;

import com.employee.dtos.EmployeeDto;

public interface EmployeeService {

	String saveEmp(EmployeeDto dto);

	List<EmployeeDto> getAll();

	EmployeeDto getbyId(Long id);

	String deletebyId(Long id);

	String updateById(EmployeeDto dto, Long id);

	EmployeeDto getByEmail(String email);

	EmployeeDto getByName(String name);
}
