package com.app.beans;

import java.time.LocalDate;
import java.time.Period;

import com.app.dao.UserDaoImpl;
import com.app.entities.User;

public class UserBean {
//state -- request params from clnt mapped to Java bean properties
	private String email;
	private String password;
	private String fn;
	private String ln;
	private String dob;

	// dependency -- dao layer
	private UserDaoImpl userDao;

	// add a property to store validated user details
	private User userDetails;
	private String message;

	// def ctor
	public UserBean() {
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

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getLn() {
		return ln;
	}

	public void setLn(String ln) {
		this.ln = ln;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	// Add B.L method for user authentication
	public String validateUser() {
		System.out.println("in validate user " + email + " " + password);
		try {
			userDetails = userDao.signIn(email, password);
			message = "Login Successful!";
			if (userDetails.getRole().equals("admin"))
				return "admin_main";
			if (userDetails.isStatus())
				return "logout";
			return "candidate_list";
		} catch (RuntimeException e) {
			message = "Invalid Email or Password , Please retry!";
			return "login";

		}

	}

	// add a B.L method for validating i/ps n then registration
	public String validateAndRegister() {
		LocalDate birthDate = LocalDate.parse(dob);
		int ageInYears = Period.between(birthDate, LocalDate.now()).getYears();
		// in case of invalid age --ret err mesg
		if (ageInYears < 21)
			return "registration failed";
		// valid age transient voter
		User voter = new User(fn, ln, email, password, birthDate);
		return userDao.voterRegistration(voter);
	}

}
