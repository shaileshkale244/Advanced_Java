package com.app.core;

public class Doctor {

	private int id;
	private String name;
	private String email;
	private String phone;
	private String speciality;

	public String getName() {
		return name;
	}
	

	public Doctor(int id, String name, String email, String phone, String speciality) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.speciality = speciality;
	}


	public Doctor(String name, String email, String phone, String speciality) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.speciality = speciality;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getId() {
		return id;
	}

}
