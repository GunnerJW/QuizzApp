package com.dp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dp.model.Question;
import com.dp.service.QuestionService;

@Controller
public class ExpertController {
	
	@Autowired
	private QuestionService questionService;

	@RequestMapping(value="/dashboard/add-question",method=RequestMethod.GET)
	public String addQuestion(Question qst,@RequestParam(value="type") String type,Model model) {
		model.addAttribute("type",type);
		model.addAttribute("qst", qst);
		return "add-question";
	}
	
	@RequestMapping(value="/dashboard/get-question",method=RequestMethod.POST)
	public String getQuestion(@ModelAttribute("qst") Question qst,BindingResult result) {
		System.out.println(qst);
		questionService.listToSet(qst);
		/*for(String s : qst.getPossibilitiesList())
			qst.getPossibilities().add(s);*/
		System.out.println(qst);
		questionService.saveQuestion(qst);
		return "redirect:/dashboard";
	}
}
