package com.app.dao;

import static com.app.utils.DBConnection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.app.entities.Candidate;

public class CandidateDaoImpl implements CandidateDao {

	Connection cn;
	PreparedStatement pst1, pst2, pst3, pst4;

	public CandidateDaoImpl() throws SQLException {
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from candidates");
		pst2 = cn.prepareStatement("update candidates set votes=votes+1 where id=?");
		pst3 = cn.prepareStatement(
				"select *,max(votes) 'Total Votes' from candidates group by id order by 'Total Votes' desc limit 2");
		pst4 = cn.prepareStatement("select party,sum(votes) 'Total Votes' from candidates group by party");
	}

	// Listing all candidates
	@Override
	public List<Candidate> getAllCandidate() throws SQLException {
		List<Candidate> list = new ArrayList<>();
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next()) {
				list.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
		}
		return list;
	}

	// Increment candidate's votes(by candidate id)
	@Override
	public void addVote(int id) throws SQLException {
		pst2.setInt(1, id);

		if (pst2.executeUpdate() == 1)
			System.out.println("Vote Added!!");

	}

	// Get to 2 candidates by votes
	@Override
	public List<Candidate> getTopTwoCandidates() throws SQLException {
		List<Candidate> list = new ArrayList<>();

		try (ResultSet rst = pst3.executeQuery()) {
			while (rst.next()) {
				list.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
		}
		return list;
	}

	// Get party name n total votes
	@Override
	public Map<String, Integer> getPartyWiseVotes() throws SQLException {
		Map<String, Integer> map = new TreeMap<>();
		try (ResultSet rst = pst4.executeQuery()) {
			while (rst.next()) {
				map.put(rst.getString(1), rst.getInt(2));
			}
		}
		return map;
	}

	public void cleanup() throws SQLException {
		System.out.println("Candidate dao cleaned up");
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		if (pst4 != null)
			pst4.close();
		closeConnection();
	}
}
