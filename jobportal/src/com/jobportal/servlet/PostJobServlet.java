package com.jobportal.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jobportal.dao.JobPostingDao;
import com.jobportal.entity.JobPosting;


@WebServlet("/postJob")
public class PostJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostJobServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("views/postJob.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		JobPosting jobPost = new JobPosting();
		jobPost.setMessageBody(request.getParameter("messageBody"));
		jobPost.setJobName(request.getParameter("jobName"));
		jobPost.setPosterName(request.getParameter("posterName"));
		jobPost.setContactPhone(request.getParameter("contactPhone"));
		jobPost.setJobPostingPassword(request.getParameter("jobPostingPassword"));
		
		JobPostingDao jobPostingDao = new JobPostingDao();
		jobPostingDao.insertJobPosting(jobPost);
		
		RequestDispatcher rd = request.getRequestDispatcher("views/postJobSuccess.jsp");
		rd.forward(request, response);
		
	}
}
