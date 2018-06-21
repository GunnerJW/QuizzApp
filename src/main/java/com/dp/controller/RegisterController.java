package com.dp.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dp.model.User;
import com.dp.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("userValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(User User)
	{
		return "register";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registrationValidation(@Validated User user,BindingResult bindingResult,Model model)
	{
		User userExists=userService.findByLogin(user.getLogin());
		if(bindingResult.hasErrors())
		{
			return "register";
		}
		else if(userExists!=null)
		{
			model.addAttribute("taken", true);
			return "register";
		}
		user.setRegisterDate(new Date());
		user.setLastLoginDate(new Date());
		userService.saveUser(user);
		return "redirect:/login";
	}
}
