package com.kejeiri.taskmanager.service;


import com.kejeiri.taskmanager.entity.User;

public interface UserService {
	public void save(User user);
	public User getUser(String name);
	public boolean register(User user);
}
