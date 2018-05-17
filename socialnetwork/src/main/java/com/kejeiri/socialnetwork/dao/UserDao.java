package com.kejeiri.socialnetwork.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import com.kejeiri.socialnetwork.model.User;

public class UserDao {
	private SessionFactory factory;
	private Session session;
	
	public UserDao() {
		factory = new Configuration().configure().buildSessionFactory();
		session =factory.openSession();		
	}
	
	public void insertUser(User user) {
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();		
	}
	
	
	public List<User> getUserById(String paramName) {
		String hql = "FROM USER where name = :name";
		@SuppressWarnings("unchecked")
		List<User> users =session.createQuery(hql).setParameter("name",paramName).list();
		return users;
	}
	public void close() {
		session.close();
		factory.close();
	}
}
