package com.dp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dp.model.User;
import com.dp.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/dashboard/enable-user")
	public String renderValidationPage(Model model) {
		Iterable<User> pendingUsers=userService.findPendingUsers();
		model.addAttribute("pendingUsers",pendingUsers);
		return "enable-user";
	}
	
	@RequestMapping(value="/enable-user",method=RequestMethod.POST)
	public String enableUser(@RequestParam("pendingUserLogin") String pendingUserLogin) {
		User pendingUser=userService.findByLogin(pendingUserLogin);
		userService.enableUser(pendingUser);
		return "redirect:/dashboard/enable-user";
	}
}
