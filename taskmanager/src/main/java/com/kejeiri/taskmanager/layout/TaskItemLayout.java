package com.kejeiri.taskmanager.layout;

import com.kejeiri.taskmanager.entity.User;
import com.kejeiri.taskmanager.service.UserService;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")

//we need to get this as bean
//@SpringComponent //removed; we're getting it using 'new' not by using Bean
public class TaskItemLayout extends HorizontalLayout{
	public TaskItemLayout(String task, UserService userService, User user) {		
		Button remove = new Button("remove");
		addComponent(remove);
		addComponent(new Label(task));	
		remove.addClickListener(e->{
			removeAllComponents();
			for (int i = 0; i < user.getTasks().size(); i++) {				
				if (user.getTasks().get(i).equals(task)) {
					user.getTasks().remove(i);
					break;
				}
			}
			userService.save(user);
		});		
	}
}
