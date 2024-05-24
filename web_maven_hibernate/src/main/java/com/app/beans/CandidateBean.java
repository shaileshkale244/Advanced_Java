package com.app.beans;

import java.util.List;
import java.util.Map;

import com.app.dao.CandidateDaoImpl;
import com.app.entities.Candidate;

public class CandidateBean {
	private CandidateDaoImpl candidateDaoImpl;

	public CandidateBean() {
		candidateDaoImpl = new CandidateDaoImpl();
		System.out.println("candidate bean created");
	}

	public CandidateDaoImpl getCandidateDaoImpl() {
		return candidateDaoImpl;
	}

	// add B.L method to get top 2 candidates
	public List<Candidate> getCandidates() {
		return candidateDaoImpl.getTop2Candidates();
	}

	// add B.L method to get partywise votes
	public Map<String, Integer> getPartyVotes()  {
		return candidateDaoImpl.getPartywiseVotes();
	}

}
