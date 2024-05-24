package com.app.dao;

import static com.app.utils.HibernateUtils.getFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.entities.Candidate;

public class CandidateDaoImpl implements CandidateDao {

	@Override
	public List<Candidate> getAllCandidates() {
		List<Candidate> list=new ArrayList<>();
		String jpql="select c from Candidate c";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
				try {
			list=session.createQuery(jpql, Candidate.class).getResultList();			
			tx.commit();
		}catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return list;
	
	}

	@Override
	public String incrementVotes(int candidateId) {
		String msg="Votes incrementation failed!!";
		
		return msg;
	}

	@Override
	public List<Candidate> getTop2Candidates() {
		List<Candidate> list=new ArrayList<>();
		String jpql="select c from Candidate c order by c.votes desc";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
				try {
			list=session.createQuery(jpql, Candidate.class).setMaxResults(2)
					.getResultList();			
			tx.commit();
		}catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return list;
	}

	@Override
	public Map<String, Integer> getPartywiseVotes() {
		Map<String, Integer> map=new HashMap<>();
		String jpql="select c.party,sum(c.votes) from Candidate c group by c.party";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
				try {
					List<Object[]> results = session.createQuery(jpql,Object[].class).getResultList();
					
					for(Object[] obj: results) {
						map.put((String)obj[0]
								, ((Number)obj[1]).intValue()
								);
					}
						
						
			tx.commit();
		}catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return map;
	}
	

	

}
