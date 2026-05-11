package com.employee.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDto {
	private String message;
	private String status;
	private String statusCode;
}
