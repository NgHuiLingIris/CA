package com.example.portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leavetype")
public class leavetype {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "granularity")
	private double granularity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getGranularity() {
		return granularity;
	}
	public void setGranularity(double granularity) {
		this.granularity = granularity;
	}
	public leavetype() {
		super();
		// TODO Auto-generated constructor stub
	}
	public leavetype(int id, String name, String description, double granularity) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.granularity = granularity;
	}
	@Override
	public String toString() {
		return "leavetype [id=" + id + ", name=" + name + ", description=" + description + ", granularity="
				+ granularity + "]";
	}
	
}
