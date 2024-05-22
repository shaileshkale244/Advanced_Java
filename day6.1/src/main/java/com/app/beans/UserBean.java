package com.app.beans;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import com.app.dao.UserDaoImpl;
import com.app.entities.User;

public class UserBean {
//state -- request params from clnt mapped to Java bean properties
	private String email;
	private String password;
	private String fname;
	private String lname;
	private String dob;

	// dependency -- dao layer
	private UserDaoImpl userDao;
	// add a property to store validated user details
	private User userDetails;
	private String message;

	// def ctor
	public UserBean() throws SQLException {
		// create dao instance
		userDao = new UserDaoImpl();
		System.out.println("user bean created...");
	}

	// getter n setter
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

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

	public String getMessage() {
		return message;
	}

	
	
	// Add B.L method for user authentication
	public String validateUser() throws SQLException {
		System.out.println("in validate user " + email + " " + password);
		// bean --> invokes dao's method
		userDetails = userDao.signIn(email, password);
		if (userDetails == null) {
			// invalid login
			message = "Invalid Email or Password , Please retry!";
			return "login";// returning the navigational outcome to JSP
		}
		// => valid login --> chk role based authorization
		message = "Login Successful!";
		if (userDetails.getRole().equals("admin"))
			return "admin_main";
		// => voter login
		if (userDetails.isStatus())
			return "logout";
		// not yet voted
		return "candidate_list";
	}

	// Add B.L method for user registration
	public String registerUser() throws SQLException {
		LocalDate date = LocalDate.parse(dob);
		if (Period.between(date, LocalDate.now()).getYears() > 21) {
			userDetails = new User(fname,lname,email,password, Date.valueOf(date));
			userDao.voterRegistration(userDetails);
			message="Voter registered Succesfully!";
			return "login";
		}
		message=" Registration Failed !!  Voter must be 21 year old !!!";
		return "login";
			
	}
	
}
