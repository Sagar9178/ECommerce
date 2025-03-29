package com.ecom.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
	private Integer status;
	
	private String message;
	
	private T data;
}
