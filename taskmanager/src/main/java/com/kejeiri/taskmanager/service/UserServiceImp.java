package com.kejeiri.taskmanager.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejeiri.taskmanager.entity.User;
import com.kejeiri.taskmanager.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{
	

	@Autowired
	UserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);		
	}

	@Override
	public User getUser(String name) {		
		return userRepository.getUserByUserName(name);
	}

	@Override
	public boolean register(User user) {
		if (user.getUserName().isEmpty() ||user.getPassword().isEmpty()) {
			return false;	
		}
		
		User dbUser = userRepository.getUserByUserName(user.getUserName());
		if (dbUser != null) {
			return false;			
		}
		
		save(user);
		return true;		
	}
}
