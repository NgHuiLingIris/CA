package com.example.portal.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "holiday")
public class Holiday {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", updatable = false, nullable = false)
	private long Id;
	@NotEmpty(message = "Please specify a holiday name")
	private String name;


	public Holiday(long id, String holidayName, Date date) {
		super();
		Id = id;
		this.name = holidayName;
		
		this.date = date;
	}
	
	private Date date;

	

	@Override
	public String toString() {
		return "Holiday [Id=" + Id + ", name=" + name + ", fromDate=" + date + 
				 "]";
	}

	public Holiday() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getHolidayName() {
		return name;
	}

	public void setHolidayName(String holidayName) {
		this.name = holidayName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



}
