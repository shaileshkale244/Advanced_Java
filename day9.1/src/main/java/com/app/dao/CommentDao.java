package com.app.dao;

import com.app.entities.Comment;

public interface CommentDao {
	String addComment(Comment comment,Long commenter_id,Long post_id);
}
