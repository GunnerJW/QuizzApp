package com.dp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dp.mail.ResultMailSender;
import com.dp.model.Question;
import com.dp.model.TestResult;
import com.dp.model.User;
import com.dp.service.QuestionService;
import com.dp.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("resultMailSender")
	private ResultMailSender mailSender;
	
	private List<Question> questionsList;
	
	private List<String> answersList;
	
	@RequestMapping(value="/dashboard/user-settings",method=RequestMethod.GET)
	public String userSettings(Authentication auth,Model model) {
		String name = auth.getName();
		String registerDate=userService.getRegisterDate(name);
		model.addAttribute("registerDate", registerDate);
		model.addAttribute("currentUser", name);
		return "user-settings";
	}
	
	@RequestMapping(value="/dashboard/test-history",method=RequestMethod.GET)
	public String testHistory() {
		return "test-history";
	}
	
	@RequestMapping(value="/dashboard/pass-test",method=RequestMethod.GET)
	public String passTest() {
		return "pass-test";
	}
	
	@RequestMapping(value="/dashboard/start-test",method=RequestMethod.GET)
	public String startTest(@RequestParam(value="type") String type,Model model) {
		questionsList=questionService.generateQuestionList(type);
		answersList=new ArrayList<String>();
		model.addAttribute("question", questionsList.get(0));
		model.addAttribute("flag", 0);
		model.addAttribute("score", 0);
		return "test";
	}
	
	@RequestMapping(value="/test",method=RequestMethod.POST)
	public String test(@RequestParam(value="answer",defaultValue="") String answer,
					   @RequestParam(value="flag") int flag,
					   @RequestParam(value="score") int score,
					   Authentication auth,
					   Model model) {
		String correctAnswer=questionsList.get(flag-1).getCorrectAnswer();
		if(answer.equals(correctAnswer))
			score++;
		answersList.add(answer);
		System.out.println(answer);
		System.out.println(correctAnswer);
		model.addAttribute("score", score);
		if(flag==5)
		{
			String currentUserLogin=auth.getName();
			User currentUser=userService.findByLogin(currentUserLogin);
			String verdict=(score<4)?"Fail":"Success";
			TestResult testResult=new TestResult(currentUser,questionsList.get(0).getDiscipline(),"mc",score,new Date(),verdict);
			userService.saveTestResult(testResult);
			model.addAttribute("questionsList", questionsList);
			model.addAttribute("answersList", answersList);
			//mailSender.sendMail("mehdi.jw@gmail.com", "test result", score+"/5");
			return "result";
		}
		model.addAttribute("question", questionsList.get(flag));
		model.addAttribute("flag", flag);
		return "test";
	}
	
}
