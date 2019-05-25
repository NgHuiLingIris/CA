package com.example.portal.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.portal.model.Leave;
import com.example.portal.model.User;

@Controller
public class HomepageController {
	@RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "homepage";
    }
	@RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginView(User user,Model model) {
		String employeedivision = user.getEmployeediv();
		if (employeedivision.equals("Admin")){
			return "homeAdmin";
		}
		else if (employeedivision.equals("Employee")){
			return "homeEmployee";
		}
		else if (employeedivision.equals("Manager")){
			return "homeManager";
		}
		else {
			return "homepage";
		}
    }
	@RequestMapping(path="/admin")
	public String Admin()
	{
		return "homeAdmin";
	}
	@RequestMapping(path="/employee")
	public String Employee()
	{
		return "homeEmployee";
	}
	@RequestMapping(path="/manager")
	public String Manager()
	{
		return "homeManager";
	}
}
