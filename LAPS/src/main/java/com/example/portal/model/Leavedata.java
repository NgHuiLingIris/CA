package com.example.portal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Leavedata {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "leaveId", updatable = false, nullable = false)
	private long leaveId;
	private long employeeid;
	private String employeename;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fromDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date toDate;
	private String leavetype;
	private String notes;
	private	String reportsto;
	private double leavehrs;
	private String approvalstatus;

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
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getReportsto() {
		return reportsto;
	}
	public void setReportsto(String reportsto) {
		this.reportsto = reportsto;
	}
	public double getLeavehrs() {
		return leavehrs;
	}
	public void setLeavehrs(double leavehrs) {
		this.leavehrs = leavehrs;
	}
	public String getApprovalstatus() {
		return approvalstatus;
	}
	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	
	public Leavedata() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Leavedata(long employeeid, String employeename, Date fromDate, Date toDate, String leavetype, String notes,
			String reportsto, double leavehrs, String approvalstatus) {
		super();
		this.employeeid = employeeid;
		this.employeename = employeename;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.leavetype = leavetype;
		this.notes = notes;
		this.reportsto = reportsto;
		this.leavehrs = leavehrs;
		this.approvalstatus = approvalstatus;
	}
	
}
