package com.kejeiri.taskmanager.page;


import com.kejeiri.taskmanager.ApplicationContextUtils;
import com.kejeiri.taskmanager.entity.User;
import com.kejeiri.taskmanager.layout.TaskLayout;
import com.kejeiri.taskmanager.service.UserService;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class HomePage extends VerticalLayout implements View {
	UserService userService;
	TaskLayout taskLayout;
	User user;
	public static final String NAME = "home";

	public HomePage() {
		userService = ApplicationContextUtils.getApplicationContext().getBean(UserService.class);
		taskLayout = ApplicationContextUtils.getApplicationContext().getBean(TaskLayout.class);
		user = (User) VaadinSession.getCurrent().getAttribute("currentUser");
		addHeader();
		addTaskForm();
		addTaskList();
		addLogout();
	}

	private void addHeader() {
		Label title = new Label("Home");
		title.addStyleName("h1");
		addComponent(title);
	}

	private void addTaskForm() {
		HorizontalLayout form = new HorizontalLayout();
		TextField task = new TextField();
		task.setPlaceholder("Enter task");
		Button add = new Button("add");		
		add.addClickListener(e -> {
			user.getTasks().add(task.getValue().trim());
			task.clear();
			userService.save(user);
			taskLayout.setTasks(user);
		});

		form.addComponents(task, add);
		addComponent(form);

	}

	private void addTaskList() {
		taskLayout.setTasks(user);
		addComponent(taskLayout);
	}

	private void addLogout() {
		Button logout = new Button("Logout");
		logout.addClickListener(e -> {
			VaadinSession.getCurrent().setAttribute("currentUser", null);
			getUI().getNavigator().removeView(HomePage.NAME);
			Page.getCurrent().setUriFragment("!" + LoginPage.NAME);
		});
		addComponent(logout);
	}

}
