package com.kejeiri.taskmanager;

import com.kejeiri.taskmanager.page.LoginPage;
import com.kejeiri.taskmanager.page.RegisterPage;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
@SuppressWarnings("serial")
@SpringUI
public class TaskUI  extends UI{

	@Override
	protected void init(VaadinRequest request) {
		//Vaadin is built into ajax framework and it can navigate from page to page  
		//need to create a navigator in order to register the view (pack page)
		new Navigator(this, this);
		
		//Navigator has been created and we call the getNavigator method to 
		//regiter the views.
		getNavigator().addView(LoginPage.NAME, LoginPage.class);
		getNavigator().addView(RegisterPage.NAME, RegisterPage.class);
		
		//navigate back and forth give raise to this event (PopStateEvent) when URI_CHANGED_METHOD
		Page.getCurrent().addPopStateListener(e->{
			route(e.getUri());
		});
		
		//setContent(new Label("Welcome to Vaadin!"));
		//a default call since we could the route method when navigate back and forth
		route("");
	}

	private void route(String uri) {
		if (VaadinSession.getCurrent().getAttribute("user") != null) {
			//TODO: GO to homepage
			
		}else {
			getNavigator().navigateTo(LoginPage.NAME);
		}	}
}
