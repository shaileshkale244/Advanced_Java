package com.app.beans;

import java.sql.SQLException;

import com.app.dao.PatientDaoImpl;

public class PatientBean {
	private String email;
	private String password;
	private String fname;
	private String lname;
	private String dob;

	PatientDaoImpl patientDao;

	public PatientBean() throws SQLException {
		patientDao = new PatientDaoImpl();
	}

	public PatientDaoImpl getPatientDao() {
		return patientDao;
	}

	public void setPatientDao(PatientDaoImpl patientDao) {
		this.patientDao = patientDao;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
	
	

}
