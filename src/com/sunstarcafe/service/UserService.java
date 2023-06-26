package com.sunstarcafe.service;

import java.util.List;

import com.sunstarcafe.entity.User;

public interface UserService {

	public Integer addUser(User user);
	
	public List<User> getUsers();
	
	public User getUser(Integer userId);
	
	public void deleteUser(Integer userId);

	public void updateUser(User user);

	public User getUserByEmail(String email);
}
