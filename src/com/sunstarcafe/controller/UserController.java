package com.sunstarcafe.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sunstarcafe.entity.User;
import com.sunstarcafe.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	private Gson gson = new Gson();
    	
	@RequestMapping("/userList")
	public String getUsers() {

		List<User> users = userService.getUsers();

		Gson gson = new Gson();
	    String userList = gson.toJson(users);

		System.out.println(userList);
		return userList;
	}
	
	@RequestMapping("/testCon")
	public boolean testCon() {

		System.out.println("Inside Test Code");
		return true;
	}

	@PostMapping("/addUser")
	public String addUser(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("mobileNo") String mobileNo, @RequestParam("login") String login) {

		User user= new User(email, password, firstName, lastName, mobileNo,login);
		Integer userId=userService.addUser(user);
		user.setUserId(new Integer(userId));
		String userJson = gson.toJson(user);
	    System.out.println(userJson);
		return userJson;

	}
	
	@PutMapping("/updateUser")
	public String updateUser(@RequestParam("userId") String userId, @RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("mobileNo") String mobileNo, @RequestParam("login") String login){

		User user= new User(email, password, firstName, lastName, mobileNo,login);
		user.setUserId(new Integer(userId));
		userService.updateUser(user);

		String userJson = gson.toJson(user);
		return userJson;

	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam("userId") String userId) {
		userService.deleteUser(new Integer(userId));

	}
	
}
