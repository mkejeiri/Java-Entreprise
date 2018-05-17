package com.jobportal.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jobportal.dao.JobPostingDao;
import com.jobportal.entity.JobPosting;


@WebServlet("/viewjobs")
public class ViewJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewJobServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JobPostingDao jobPostingDao = new JobPostingDao();
		List<JobPosting> jobs = jobPostingDao.getAllJobPosting();
		request.setAttribute("jobs", jobs);
		RequestDispatcher rd = request.getRequestDispatcher("/views/viewJobs.jsp");
		rd.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
	}

}
