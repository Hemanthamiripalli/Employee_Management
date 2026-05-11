package com.employee.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeResponseDto {

	private String message;
	private String status;
	private String statusCode;
	private List<EmployeeDto> list;
	private EmployeeDto emp;

}
