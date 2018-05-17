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

@WebServlet(urlPatterns = {"/removejob","/jobremove"})
public class RemoveJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public RemoveJobServlet() {
        super();       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
    	request.setAttribute("err", null);
    	RequestDispatcher rd = request.getRequestDispatcher("/views/removeJob.jsp");
    	rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JobPostingDao jobDao = new JobPostingDao();
		String pass = request.getParameter("jobPostingPassword");
		int jobId = Integer.valueOf(request.getParameter("jobid"));		
		JobPosting job = jobDao.getJobPostingById(jobId);
		
		if (job == null) {
			request.setAttribute("err", "Incorrect Job id, try again!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/removeJob.jsp");
	    	rd.forward(request, response);
		} else {
			if (job.getJobPostingPassword().equals(pass)) {
				jobDao.removeJobPosting(jobId);
				RequestDispatcher rd = request.getRequestDispatcher("/views/removeJobSuccess.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("err", "Incorrect password, try again!");
				RequestDispatcher rd = request.getRequestDispatcher("/views/removeJob.jsp");
		    	rd.forward(request, response);
			}
		}	
		
	}
}
