package com.ecom.entity;






import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long  id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "pwd")
	private String pwd;
	
	@Column(name = "pwdUpdated")
	private String pwdUpdated;
	
	@Column(name = "phno")
	private String phno;
	
	@Column(name = "date_update")
	@CreationTimestamp
	private Date dateCreated;
	
	@Column(name = "last_update")
    @UpdateTimestamp
	private Date lastUpdate;

	
	
}
