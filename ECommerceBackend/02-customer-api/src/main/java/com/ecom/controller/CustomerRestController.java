package com.ecom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.CustomerDto;
import com.ecom.dto.ResetPwdDto;
import com.ecom.response.ApiResponse;
import com.ecom.response.AuthResponse;
import com.ecom.service.CustomerService;


@RestController
public class CustomerRestController {
	
	@Autowired	
	private CustomerService customerService;
	@Autowired
	private BCryptPasswordEncoder bcryptpwd;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> register(@RequestBody CustomerDto dto){
		
		ApiResponse<String> response=new ApiResponse<>();
		
		if(!customerService.isEmailUnique(dto.getEmail())) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("Duplicate Email");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
		}
		Boolean register = customerService.register(dto);
		if(register) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData("Registation Sucess");
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}else {
			response.setStatus(500);
			response.setMessage("Failed");
			response.setData("Registation Failed");
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody CustomerDto dto){
		AuthResponse login = customerService.login(dto);
		ApiResponse<AuthResponse> response=new ApiResponse<>();
		if(login!=null) {
			response.setStatus(200);
			response.setMessage("login Success");
			response.setData(login);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(500);
			response.setMessage("Invalid credential");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/resetPwd")
	public ResponseEntity<ApiResponse<String>> resetPwd( @RequestBody ResetPwdDto dto){
		
		CustomerDto cdto = customerService.getCustomerByEmail(dto.getEmail());
		
		ApiResponse<String> response=new ApiResponse<>();
		if(!bcryptpwd.matches( dto.getOldPwd(),cdto.getPwd())) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("Invalid old Password");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
		if( ! dto.getNewPwd().equals(dto.getConfirmPwd()) ) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("New pwd and confirm pwd not maching");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
		
		Boolean result = customerService.resetpwd(dto);
		if(result) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData("Password Updated success");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(500);
			response.setMessage("Failed");
			response.setData("Failed to resetpwd");
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/forgetPwd/{email}")
	public ResponseEntity<ApiResponse<String>> forgetPwd( @PathVariable String email){
		
		ApiResponse<String> response=new ApiResponse<>();
		Boolean result = customerService.forgetpwd(email);
		if(result) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData("Email sent to reset Password");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("No account found");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	 
}
