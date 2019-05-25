package com.example.portal.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		long employeeid = user.getEmployeeid();
		if (employeedivision.equals("Admin")){
			return "redirect:/admin";
		}
		else if (employeedivision.equals("Employee")){
			return "redirect:/employee/"+employeeid;
		}
		else if (employeedivision.equals("Manager")){
			return "redirect:/manager/"+employeeid;
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
	@RequestMapping(path="/employee/{id}", method = RequestMethod.GET)
	public String Employee(@PathVariable(value = "id") int employeeid,Model model, User user)
	{	user.setEmployeeid(employeeid);
		model.addAttribute("user", user);
		return "homeEmployee";
	}
	
	@RequestMapping(path="/manager/{id}")
	public String Manager(@PathVariable(value = "id") int employeeid,Model model, User user)
	{	user.setEmployeeid(employeeid);
		model.addAttribute("user", user);
		return "homeManager";
	}
}
