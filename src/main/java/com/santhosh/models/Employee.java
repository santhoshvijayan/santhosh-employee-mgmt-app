package com.santhosh.models;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
	
	@Id
	private String id;
	
	@NotNull
	@Size(min=5, max=30)
	private String empname;
	
	@NotNull
	private Date joindate;
	
	@NotNull
	@Size(min=3, max=15)
	private String city;
	
	private String phone;
		
	@NotNull
	private Boolean isactive;

	
	public Employee() {
	}

	public Employee(String empname, Date joindate, String city, String phone, Boolean isactive) {
		this.empname = empname;
		this.joindate = joindate;
		this.city = city;
		this.phone = phone;
		this.isactive = isactive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

		
	
}
