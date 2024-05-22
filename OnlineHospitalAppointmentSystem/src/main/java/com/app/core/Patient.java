package com.app.core;

import java.sql.Date;

public class Patient {
	private int id;
	private String name;
	private String email;
	private String password;
	private Date dob;

	public Patient(int id, String name, String email, String password, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.dob = dob;
	}

	public Patient(String name, String email, String password, Date dob) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

}
