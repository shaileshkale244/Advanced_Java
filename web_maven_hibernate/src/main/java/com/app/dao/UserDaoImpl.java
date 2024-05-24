package com.app.dao;

import static com.app.utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.entities.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User signIn(String email, String password) {
		String jpql = "select u from User u where u.email=:email and u.password=:password";
		User user = null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			user = session.createQuery(jpql, User.class).setParameter("email", email).setParameter("password", password)
					.getSingleResult();

			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return user;
	}

	@Override
	public String voterRegistration(User newVoter) {
		String msg = "Voter registration failed";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.persist(newVoter);
			msg = "Voter registration Successfull!!";

			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return msg;
	}

	@Override
	public String deleteVoterDetails(int voterId) {
		String msg = "Voter deletion failed";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			User user = session.find(User.class, voterId);
			if (user != null) {
				session.delete(user);
				msg = "Voter deleted!!";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return msg;
	}

}
