package com.kejeiri.taskmanager.page;

import com.kejeiri.taskmanager.ApplicationContextUtils;
import com.kejeiri.taskmanager.entity.User;
import com.kejeiri.taskmanager.service.UserService;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class RegisterPage extends VerticalLayout implements View{
	public static final String NAME="register";
	private TextField username;		
	private PasswordField pass;
	
	//Autowired interface doesn't get injected since the redirection 
	//is done by vaadin and not spring
	/*@Autowired*/
	UserService userService;

	public RegisterPage() {
		userService = ApplicationContextUtils.getApplicationContext().getBean(UserService.class);	
		addHeader();
		addForm();
	}

	private void addHeader() {
		Label title = new Label("Register");
		title.addStyleName("h1");	
		HorizontalLayout hl = new HorizontalLayout();
		hl.addComponent(title);
		addComponent(hl);	
	}
	
	private void addForm() {
		username = new TextField();		
		pass = new PasswordField();
		username.setPlaceholder("username");
		pass.setPlaceholder("password");
		
		HorizontalLayout hlTexts = new HorizontalLayout();
		hlTexts.addComponents(username,pass);		
		addComponent(hlTexts);
		
		Button login = new Button("Login");
		Button register = new Button("Register");
		
		login.addClickListener(e->{
			Page.getCurrent().setUriFragment("!"+LoginPage.NAME);			
		});
		
		
		register.addClickListener(e->{
			User user = new User();
			user.setUserName(username.getValue().trim());
			user.setPassword(pass.getValue().trim());
			System.out.println(userService);
			if (userService.register(user)) {
				Page.getCurrent().setUriFragment("!"+LoginPage.NAME);
			} else {
				Notification.show("incorrect user info!",Notification.Type.ERROR_MESSAGE);
			}			
		});
		
		HorizontalLayout hlButtons = new HorizontalLayout();
		hlButtons.addComponents(register,login);
		addComponent(hlButtons);	
	}
}
