package com.app.dao;


import java.util.List;
import java.util.Map;

import com.app.entities.Candidate;

public interface CandidateDao {
//add a method to get all candidate details
	List<Candidate> getAllCandidates() ;

	// add a method to inc votes
	String incrementVotes(int candidateId);

	// add a method to get top 2 candidates
	List<Candidate> getTop2Candidates() ;

	// add a method to get party wise votes
	Map<String, Integer> getPartywiseVotes() ;

}
