package com.kejeiri.springproject.complaintsystem.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.kejeiri.springproject.complaintsystem.entities.Complaint;

public class ComplaintDao {
	
	//a hibernate session which allow us to interact with DB
	private Session session;
	
	public ComplaintDao(SessionFactory sessionFactory) {
		session = sessionFactory.openSession();
	}
	
	public void insertConplaint(Complaint complaint) {
		session.save(complaint);
		session.flush();		
	}
	
	public List<Complaint> getAllComplaints(){
		Query  query = session.createQuery("from Complaint");		
		List<Complaint> allComplaints = query.list();
		return allComplaints;
	}
	
	public void deleteComplaint(Integer complaintID) {		
		Query query = session.createQuery("delete from Complaint where  id ="+complaintID);		
		query.executeUpdate();
	}
	
	public void sessionClose() {
		session.close();
	}
	
	
}
