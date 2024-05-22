package com.app.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.app.entities.Candidate;

public interface CandidateDao {

	List<Candidate> getAllCandidate() throws SQLException; 
	
	void addVote(int id) throws SQLException;
	
	List<Candidate> getTopTwoCandidates() throws SQLException;
	
	Map<String,	Integer> getPartyWiseVotes() throws SQLException;
}
