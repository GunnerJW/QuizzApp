package com.dp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dp.validation.UserValidator;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public Validator userValidator() {
		return new UserValidator();
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		return new ResourceBundleMessageSource();
	}
	
	/*@Bean
	public MailSender mailSender() {
		return new MailSender();
	}*/
	
}
