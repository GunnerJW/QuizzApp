package com.dp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dp.model.TestResult;
import com.dp.model.User;
import com.dp.repository.UserRepository;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}
	
	public Iterable<User> findPendingUsers(){
		return userRepository.findAllByEnabled(false);
	}
	
	public void saveUser(User user)
	{
		user.setPassword(bCrypt.encode(user.getPassword()));
		user.setEnabled(false);
		userRepository.save(user);
	}
	
	public void enableUser(User user) {
		user.setEnabled(true);
		userRepository.save(user);
	}
	
	public void saveTestResult(TestResult testResult) {
		String login=testResult.getUser().getLogin();
		User user=userRepository.findByLogin(login);
		user.getTestResults().add(testResult);
		System.out.println(testResult);
		System.out.println(user.getTestResults());
		userRepository.save(user);
	}
	
	public String getRegisterDate(String userName) {
		User user=userRepository.findByLogin(userName);
		return user.getRegisterDate().toString();
	}
}
