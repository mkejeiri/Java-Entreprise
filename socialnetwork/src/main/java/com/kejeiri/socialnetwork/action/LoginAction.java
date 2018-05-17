package com.kejeiri.socialnetwork.action;
import org.apache.commons.lang3.StringUtils;

import com.kejeiri.socialnetwork.dao.UserDao;
import com.kejeiri.socialnetwork.model.User;
import com.opensymphony.xwork2.ActionSupport;


public class LoginAction  extends ActionSupport{
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void validate() {	
		super.validate();
		if (StringUtils.isEmpty(user.getUserName())) {
			addFieldError("user.userName", "user name cannot be blank");
		} else {
			if (StringUtils.isEmpty(user.getUserName())) {
				addFieldError("user.password", "password cannot be blank");
			}	
		}
		
		
	}
	
	@Override
	public String execute() {
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		UserDao userDao = new UserDao();
		userDao.insertUser(user);
		return SUCCESS;
	}

}
