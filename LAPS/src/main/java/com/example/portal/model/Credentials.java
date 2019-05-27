package com.example.portal.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="credentials")
public class Credentials {
	@Id
	private long employeeid;
	private String employeeuid;
	private String employeepwd;
	
	public long getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(long employeeid) {
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
	public Credentials() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Credentials(long employeeid, String employeeuid, String employeepwd) {
		super();
		this.employeeid = employeeid;
		this.employeeuid = employeeuid;
		this.employeepwd = employeepwd;
	}
	public Credentials(String employeeuid, String employeepwd) {
		super();
		this.employeeuid = employeeuid;
		this.employeepwd = employeepwd;
	}
	public Credentials(long employeeid) {
		super();
		this.employeeid = employeeid;
	}
	@Override
	public String toString() {
		return "Credentials [employeeid=" + employeeid + ", employeeuid=" + employeeuid + ", employeepwd=" + employeepwd
				+ "]";
	}
	
	
}
