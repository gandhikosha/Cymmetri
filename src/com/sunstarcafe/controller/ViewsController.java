package com.sunstarcafe.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.cj.util.StringUtils;
import com.sunstarcafe.entity.User;
import com.sunstarcafe.service.UserService;

@Controller
public class ViewsController {
	
	@Autowired
	private UserService userService;

	private String auth_token;
	
	@RequestMapping("/showFormForAddUser")
	public String showFormForAdd(Model theModel) {

		User user = new User();
		theModel.addAttribute("user", user);
		return "userForm";	
	}

	@RequestMapping("/showFormForUpdateUser")
	public String showFormForUpdate(@RequestParam("userId") String userId, Model theModel) {

		User user = userService.getUser(new Integer(userId));

		theModel.addAttribute("user", user);

		return "userUpdateForm";
	}
	
	@RequestMapping("/addNewUser")
	public String addUser(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, 
			@RequestParam("mobileNo") String mobileNo) {

		User user= new User(email, password, firstName, lastName, mobileNo,email);
		userService.addUser(user);

		// use a redirect to prevent duplicate submissions
		return "redirect:/userList";

	}
	
	@RequestMapping("/updateUserDetails")
	public String updateUser(@RequestParam("userId") String userId, @RequestParam("email") String email,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, 
			@RequestParam("mobileNo") String mobileNo){

		
		User user= userService.getUser(new Integer(userId));
		if(user!=null)
		{
			user.setUserId(new Integer(userId));
			
			if(!StringUtils.isNullOrEmpty(email))
				user.setEmail(email);
			
			if(!StringUtils.isNullOrEmpty(firstName))
				user.setFirstName(firstName);
			
			if(!StringUtils.isNullOrEmpty(lastName))
				user.setLastName(lastName);
			
			if(!StringUtils.isNullOrEmpty(mobileNo))
				user.setMobileNo(mobileNo);
			
		userService.updateUser(user);
		}

		// use a redirect to prevent duplicate submissions
		return "redirect:/userList";

	}
	
	@RequestMapping("/deleteUser")
	public String delete(@RequestParam("userId") String userId) {

		userService.deleteUser(new Integer(userId));

		// redirect to /Books/list
		return "redirect:/userList";

	}
	
	@RequestMapping("/getToken")
	public String getToken(@RequestParam("auth_token") String auth_token, HttpServletRequest req) throws IOException {
		this.auth_token=auth_token;
		String ip=req.getRemoteAddr();
		System.out.println("ip: "+ip);
		Properties prop=new Properties();
		boolean allow_user=false;
		
		try (InputStream inputStream = getClass()
				.getClassLoader().getResourceAsStream("App.properties")) {
			
            prop.load(inputStream);
            String app_id=prop.getProperty("app_id");
    		String app_pass=prop.getProperty("app_pass");
    		String inputStr="app_id="+app_id+"&app_pass="+app_pass+"&user_session_string="+auth_token+"&user_ip="+ip;
    		System.out.println(inputStr);
    		String resp=apiCall("https://bytetratraining.cymmetri.io/api-sso/api/sso/validateToken", inputStr);
    		System.out.println(resp);
    		//{"status":"user_verified","allow_user":"true","user_id":"koshaa_d","allow_user_status":"allow_user_true"}
    		JSONObject respJSON = new JSONObject(resp);
    		allow_user=respJSON.getBoolean("allow_user");
    		System.out.println(allow_user);		
    		

        } catch (IOException e) {
            e.printStackTrace();
        }	
		
		if(allow_user)
		{
			return "Dashboard";
		}
		else
			return "redirect:/Login.jsp";
	}
	
	public String apiCall(String urlStr, String inputStr) throws IOException
	{
		URL url = new URL(urlStr);

		// Open a connection(?) on the URL(??) and cast the response(???)
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// Now it's "open", we can set the request method, headers etc.
		connection.setRequestProperty("accept", "application/json");
		
		try(OutputStream os = connection.getOutputStream()) {
		    byte[] input = inputStr.getBytes("utf-8");
		    os.write(input, 0, input.length);			
		}
				
		 StringBuilder response = new StringBuilder();
		try(BufferedReader br = new BufferedReader(
				  new InputStreamReader(connection.getInputStream(), "utf-8"))) {
				   
				    String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				        response.append(responseLine.trim());
				    }
				    System.out.println(response.toString());
		}
		
		return response.toString();

	}

}
