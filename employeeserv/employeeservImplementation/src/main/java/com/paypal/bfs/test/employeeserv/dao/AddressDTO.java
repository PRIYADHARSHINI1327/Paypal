package com.paypal.bfs.test.employeeserv.dao;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Embeddable
public class AddressDTO {
	
	
	
	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	private String line1;
	
	private String line2;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private Integer zipCode;

}
