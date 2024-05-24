package com.app.dao;

import com.app.entities.User;

public interface UserDao {
//add a method for user's signin
	User signIn(String email, String password);

	// add a method for voter reg.
	String voterRegistration(User newVoter);

	// delete voter details
	String deleteVoterDetails(int voterId);

}
