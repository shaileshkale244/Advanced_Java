package com.app.tester;

import static com.app.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.app.dao.CommentDao;
import com.app.dao.CommentDaoImpl;
import com.app.entities.Comment;

public class AddNewCommnet {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			// create category dao instance
			CommentDao dao = new CommentDaoImpl();
			//for testing - do not add any white space
			System.out.println("Enter comment details - text , rating ,userid ,postid");		
			System.out.println(dao.addComment(new Comment(sc.next(), sc.nextInt()), sc.nextLong(), sc.nextLong()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
