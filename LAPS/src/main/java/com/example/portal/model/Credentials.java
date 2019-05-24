package com.example.portal.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Credentials {
	@Id
	private String employeeid;
	private String employeeuid;
	private String employeepwd;
	
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getEmployeeuid() {
		return employeeuid;
	}
	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
	}
	public String getEmployeepwd() {
		return employeepwd;
	}
	public void setEmployeepwd(String employeepwd) {
		this.employeepwd = employeepwd;
	}
	
}
