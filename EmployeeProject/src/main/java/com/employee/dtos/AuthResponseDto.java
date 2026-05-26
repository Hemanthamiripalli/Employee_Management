package com.employee.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponseDto extends ResponseDto {
	private String token;
	private String tokenType;
}
