package com.employee.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDto {

	private Long id;
	private String empName;
	private String empEmail;
	private String jobTitle;
	private String salary;
	private String status;
}
