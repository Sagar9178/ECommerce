package com.ecom.dto;




import java.sql.Date;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerDto {
	private Long  id;
	private String name;
	private String email;
	private String pwd;
	
	private String pwdUpdated;
	private String phno;
	
	private Date dateCreated;

	
	private Date lastUpdate;
	
	
}
