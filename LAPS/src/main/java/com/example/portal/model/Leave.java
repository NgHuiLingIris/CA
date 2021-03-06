package com.example.portal.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

import com.example.portal.validator.IsValidLeave;


@Entity
@Table(name = "Leave_App")
@IsValidLeave
public class Leave {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private int Id;
	@Column(name = "overseas_contact_details")
	private int overseasContactDetails;
	@Column(name = "manager_comment")
	private String managerComment;
	private String employeename;
	
	public Leave(int id, int overseasContactDetails, String managerComment, double granularity,
			 long employeeId, String status, String reason, Date fromDate, Date toDate,
			String leave_type, String description) {
		super();
		Id = id;
		this.overseasContactDetails = overseasContactDetails;
		this.managerComment = managerComment;
		this.granularity = granularity;
		EmployeeId = employeeId;
		this.status = status;
		this.reason = reason;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.leave_type = leave_type;
	}
	private double granularity;
	public long getEmployeeId() {
		return EmployeeId;
	}


	public void setEmployeeId(long employeeId) {
		EmployeeId = employeeId;
	}
	@Column(name = "employee_id")

	private long EmployeeId;
	private String status;
	@NotEmpty(message = "Please fill in a reason")
	private String reason;
	@Column(name = "from_date")
	private Date fromDate;
	private String leave_type;
	@Column(name = "to_date")
	private Date toDate;
	private Double duration;

	public Double getDuration() {
		return duration;
	}


	public void setDuration(Double duration) {
		this.duration = duration;
	}


	public Leave(int id, int overseasContactDetails, String managerComment, double granularity,
			String status, Date fromDate, Date toDate, String reason, String leave_type, String description) {
		super();
		Id = id;
		this.overseasContactDetails = overseasContactDetails;
		this.managerComment = managerComment;
		this.granularity = granularity;
		this.status = status;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.reason = reason;
		this.leave_type = leave_type;
	}


	public int getOverseasContactDetails() {
		return overseasContactDetails;
	}

	public void setOverseasContactDetails(int overseasContactDetails) {
		this.overseasContactDetails = overseasContactDetails;
	}

	public String getManagerComment() {
		return managerComment;
	}

	public void setManagerComment(String managerComment) {
		this.managerComment = managerComment;
	}

	public double getGranularity() {
		return granularity;
	}

	public void setGranularity(double granularity) {
		this.granularity = granularity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

public String getLeave_type() {
	return leave_type;
}
public void setLeave_type(String leave_type) {
	this.leave_type = leave_type;
}

public Leave() {
	super();
	// TODO Auto-generated constructor stub
}


public Leave(int id, String leave_type) {
	super();
	Id = id;
	this.leave_type = leave_type;
}
public Leave(String leaveType) {
	super();
	this.leave_type = leaveType;
}
@Override
public String toString() {
	return "Leave [leave_type=" + leave_type;
}
public String getLeaveType() {
	return leave_type;
}
public void setLeaveType(String leaveType) {
	this.leave_type = leaveType;
}

public String getEmployeename() {
	return employeename;
}


public void setEmployeename(String employeename) {
	this.employeename = employeename;
}
}
