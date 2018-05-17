package com.kejeiri.taskmanager.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="users")
public class User {
	
	public List<String> getTasks() {
		return tasks;
	}

	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}

	@Id
	@Column(name="id")
	@GenericGenerator(name="inc", strategy="increment")
	@GeneratedValue(generator="inc")
	private Integer id;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@ElementCollection(fetch =FetchType.EAGER)
	@CollectionTable(name="UserTasks", joinColumns=@JoinColumn(name="id"))
	@Column(name="tasks")
	private List<String> tasks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
