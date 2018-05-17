package com.kejeiri.taskmanager.layout;

import org.springframework.beans.factory.annotation.Autowired;

import com.kejeiri.taskmanager.entity.User;
import com.kejeiri.taskmanager.service.UserService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
//we need to get this as bean
@SpringComponent
public class TaskLayout extends VerticalLayout{

	@Autowired
	UserService userService;
	
	public void setTasks(User user) {
		removeAllComponents();
		user.getTasks().forEach(task -> {
			addComponent(new TaskItemLayout(task, userService, user));
		});
	}
}
