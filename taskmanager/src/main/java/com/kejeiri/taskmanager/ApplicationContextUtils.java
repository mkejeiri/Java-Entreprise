package com.kejeiri.taskmanager;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware{

	private static ApplicationContext ctx;
	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;		
	}
	
	 @Autowired
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}
	

}
