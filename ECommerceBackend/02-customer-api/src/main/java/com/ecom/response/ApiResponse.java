package com.ecom.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ApiResponse<T> {
	private Integer status;
	
	private String message;
	
	private T data;

	
}
