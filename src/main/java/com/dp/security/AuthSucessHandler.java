package com.dp.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dp.model.User;
import com.dp.service.UserService;

@Component
public class AuthSucessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		User user=userService.findByLogin(authentication.getName());
		System.out.println(authentication.getName()+" has logged in!");
		user.setLastLoginDate(new Date());
		userService.saveUser(user);
		//response.sendRedirect("/current");
	}

}
