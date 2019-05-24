package com.example.portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.example.portal.validator.CompHourValidator;
import com.example.portal.validator.IsValidCompHour;
import com.example.portal.validator.IsValidPhoneNumber;
import com.example.portal.validator.PhoneNumberValidator;

import org.hibernate.annotations.GenericGenerator;

//import org.hibernate.annotations.GenericGenerator;


//import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user1")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employeeid", updatable = false, nullable = false)
	private long employeeid;
	@NotEmpty(message="Name is mandatory")
	private String employeename;
	@NotEmpty(message = "Please choose a role")
	private String employeediv;
	@IsValidPhoneNumber
	private long employeecontact;
	@NotEmpty(message = "Email is mandatory")
	@Email(message = "Please provide a valid email address")
	private String employeemail;
	@NotEmpty(message = "Employee approver is mandatory")
	private String reportsto;
	@NotEmpty(message =  "Leave entitled is mandatory")
	private String leaveentitled;
	@IsValidCompHour
	private double comphours;
	private long leaveid;
	
	public long getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(long employeeid) {
		this.employeeid = employeeid;
	}
	
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getEmployeediv() {
		return employeediv;
	}
	public void setEmployeediv(String employeediv) {
		this.employeediv = employeediv;
	}
	public long getEmployeecontact() {
		return employeecontact;
	}
	public void setEmployeecontact(long employeecontact) {
		this.employeecontact = employeecontact;
	}
	public String getEmployeemail() {
		return employeemail;
	}
	public void setEmployeemail(String employeemail) {
		this.employeemail = employeemail;
	}
	public String getReportsto() {
		return reportsto;
	}
	public void setReportsto(String reportsto) {
		this.reportsto = reportsto;
	}
	public String getLeaveentitled() {
		return leaveentitled;
	}
	public void setLeaveentitled(String leaveentitled) {
		this.leaveentitled = leaveentitled;
	}
	public double getComphours() {
		return comphours;
	}
	public void setComphours(double comphours) {
		this.comphours = comphours;
	}
	
	public User(String employeename, String employeediv, long employeecontact,
			String employeemail, String reportsto, String leaveentitled, double comphours) {
		super();
		
		this.employeename = employeename;
		this.employeediv = employeediv;
		this.employeecontact = employeecontact;
		this.employeemail = employeemail;
		this.reportsto = reportsto;
		this.leaveentitled = leaveentitled;
		this.comphours = comphours;
	}
	public User(String employeename, String employeediv, String employeemail) {
		super();
		
		this.employeename = employeename;
		this.employeediv = employeediv;
		this.employeemail = employeemail;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [employeeid=" + employeeid + ", employeename=" + employeename + ", employeediv=" + employeediv
				+ ", employeecontact=" + employeecontact + ", employeemail=" + employeemail + ", reportsto=" + reportsto
				+ ", leaveentitled=" + leaveentitled + ", comphours=" + comphours + "]";
	}
	
	
}
