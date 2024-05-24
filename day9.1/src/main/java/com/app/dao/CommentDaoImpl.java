package com.app.dao;

import static com.app.utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.entities.BlogPost;
import com.app.entities.Comment;
import com.app.entities.User;

public class CommentDaoImpl implements CommentDao {

	@Override
	public String addComment(Comment comment, Long commenter_id, Long post_id) {
		String mesg = "Comment Addition Fails!!!";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			User commentor = session.get(User.class, commenter_id);
			BlogPost post = session.get(BlogPost.class, post_id);

			if (post != null && commentor != null && commenter_id != post.getAuthor().getId()) {
				// establish association
				comment.setCommenter(commentor);
				comment.setPost(post);
				session.persist(comment);
				mesg = "Comment added!!!";
			}

			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

}
