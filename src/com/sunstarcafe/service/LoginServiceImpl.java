package com.sunstarcafe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunstarcafe.entity.User;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean validateUser(String email, String password) {
	
		boolean result=false;
		User user=userService.getUserByEmail(email);
		if(user!=null && user.getPassword().equals(password))
		{
			result=true;
		}
		return result;
	}

}
