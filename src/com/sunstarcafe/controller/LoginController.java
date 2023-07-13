package com.sunstarcafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sunstarcafe.entity.User;
import com.sunstarcafe.service.LoginService;
import com.sunstarcafe.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String login(@RequestParam("email") String email,@RequestParam("password") String password) {

		boolean result= loginService.validateUser(email, password);
		if(result)
			return "Dashboard";
		else
			return "redirect:/Login.jsp";
	}

}
