package com.dp.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class GeneralController {

	@RequestMapping("/")
	public String home(Authentication auth) {
		if(auth==null)
			return "redirect:/login";
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(Authentication auth,Model model) {
		model.addAttribute("currentUser", auth.getName());
		return "dashboard";
	}
	
	@RequestMapping("/current")
	public String current(Authentication auth,Model model) {
		if(auth==null)
			model.addAttribute("currentUser","none");
		else
			model.addAttribute("currentUser", auth.getName());
		return "current";
	}
}
