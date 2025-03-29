package com.ecom.mapper;

import java.util.function.Function;

import org.modelmapper.ModelMapper;

import com.ecom.dto.CustomerDto;

import com.ecom.entity.Customer;


public class CustomerMapper {
	public static final ModelMapper modelMapper=new ModelMapper();
	
	public static CustomerDto convertToDto(Customer entity) {
		return modelMapper.map(entity, CustomerDto.class);
	}
	
	public static Customer convertToentity(CustomerDto dto) {
		return modelMapper.map(dto, Customer.class);
	}
}
