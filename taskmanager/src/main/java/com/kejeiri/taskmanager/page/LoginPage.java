package com.kejeiri.taskmanager.page;

import com.kejeiri.taskmanager.ApplicationContextUtils;
import com.kejeiri.taskmanager.entity.User;
import com.kejeiri.taskmanager.service.UserService;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LoginPage extends VerticalLayout implements View{

	public static final String NAME="login";
	UserService userService;

	public LoginPage() {
		userService = ApplicationContextUtils.getApplicationContext().getBean(UserService.class);	
		addHeader();
		addForm();		
	}

	private void addHeader() {
		Label title = new Label("Login");
		title.addStyleName("h1");
		HorizontalLayout hl = new HorizontalLayout();
		hl.addComponent(title);
		addComponent(hl);		
	}

	private void addForm() {
		
		
		TextField username = new TextField();		
		PasswordField pass = new PasswordField();
		username.setPlaceholder("username");
		pass.setPlaceholder("password");
		
		VerticalLayout vl = new VerticalLayout();
		vl.addComponents(username,pass);
		HorizontalLayout hlTexts = new HorizontalLayout();
		hlTexts.addComponent(vl);		
		addComponent(hlTexts);
		
		Button login = new Button("Login");
		Button register = new Button("Register");
		
		
		login.addClickListener(e->{
			User user = new User();
			user.setUserName(username.getValue());
			user.setPassword(pass.getValue());
			
			
			User dbUser = userService.getUser(user.getUserName());
			
			if (validate(user, dbUser)){
				VaadinSession.getCurrent().setAttribute("currentUser", dbUser);
				getUI().getNavigator().addView(HomePage.NAME, HomePage.class);
				Page.getCurrent().setUriFragment("!"+HomePage.NAME);
			} else {
				Notification.show("Invalid username/password!",Type.ERROR_MESSAGE);			
			}					
			
		});
		
		register.addClickListener(e->{
			Page.getCurrent().setUriFragment("!"+RegisterPage.NAME);			
		});
		
		HorizontalLayout hlButtons = new HorizontalLayout();
		hlButtons.addComponents(login, register);
		addComponent(hlButtons);		
	}

	private boolean validate(User user, User dbUser) {
		return !((dbUser == null) || !dbUser.getPassword().equals(user.getPassword()));
	}
}
