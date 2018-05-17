package com.jobportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jobportal.entity.JobPosting;

public class JobPostingDao {
	private Connection singletonConnect;
	
	public JobPostingDao() {		
		try {
			singletonConnect = DatabaseConnection.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public JobPosting getJobPostingById(int id) {
		String SQL="Select * from jobPosting where id = ?";
		JobPosting jobPost = null;
		try {
			PreparedStatement preparedStatement = singletonConnect.prepareStatement(SQL);
			preparedStatement.setInt(1,id);
			ResultSet res = preparedStatement.executeQuery();
			if (res.next()) {
				jobPost = new JobPosting();
				jobPost.setId(res.getInt("id"));
				jobPost.setJobName(res.getString("jobName"));
				jobPost.setPosterName(res.getString("posterName"));
				jobPost.setContactPhone(res.getString("contactPhone"));
				jobPost.setJobPostingPassword(res.getString("jobPostingPassword"));
				jobPost.setMessageBody(res.getString("messageBody"));				
				res.close();
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		
		return jobPost;
	}
	
	public void insertJobPosting(JobPosting posting) {
		String SQL="INSERT INTO jobPosting (messageBody,jobName,posterName,contactPhone,jobPostingPassword) "
				+ "VALUES (?,?,?,?,?)";
		PreparedStatement preparedStatement;
			try {
				preparedStatement = singletonConnect.prepareStatement(SQL);
				preparedStatement.setString(1,posting.getMessageBody());
				preparedStatement.setString(2,posting.getJobName());
				preparedStatement.setString(3,posting.getPosterName());
				preparedStatement.setString(4,posting.getContactPhone());
				preparedStatement.setString(5,posting.getJobPostingPassword());
				preparedStatement.executeUpdate();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	}
	
	public void removeJobPosting(int jobPostId) {	
		String SQL="DELETE FROM `JobPortal`.`jobPosting` WHERE id = ?";		
		PreparedStatement preparedStatement;
			try {
				preparedStatement = singletonConnect.prepareStatement(SQL);
				preparedStatement.setInt(1,jobPostId); 
				preparedStatement.execute();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	
	}
	public List<JobPosting> getAllJobPosting(){
		ArrayList<JobPosting> jobPosts = null; 
		String SQL="Select * from jobPosting limit 200";		
		try {
			PreparedStatement preparedStatement = singletonConnect.prepareStatement(SQL);
			ResultSet res = preparedStatement.executeQuery();
			 jobPosts = new ArrayList();
			while (res.next()) {
				JobPosting jobPost  = new JobPosting();
				jobPost.setId(res.getInt("id"));
				jobPost.setJobName(res.getString("jobName"));
				jobPost.setPosterName(res.getString("posterName"));
				jobPost.setContactPhone(res.getString("contactPhone"));
				jobPost.setJobPostingPassword(res.getString("jobPostingPassword"));
				jobPost.setMessageBody(res.getString("messageBody"));
				jobPosts.add(jobPost);
			}
			preparedStatement.close();
			res.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return jobPosts;
	}
	
	public void close() {
		try {
			singletonConnect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
