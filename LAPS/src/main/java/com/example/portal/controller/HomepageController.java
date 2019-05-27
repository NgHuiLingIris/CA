package com.example.portal.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.portal.model.Credentials;
import com.example.portal.model.Leave;
import com.example.portal.model.User;
import com.example.portal.service.AuthenticateIF;
import com.example.portal.service.LoginTypeIF;

@Controller
public class HomepageController {
	@Resource(name = "authenticate")
	private AuthenticateIF authserv;	
	private LoginTypeIF logserv;

	@Autowired
	public void setAuthserv(AuthenticateIF authserv) {
		this.authserv = authserv;
	}

	@Autowired
	public void setLogserv(LoginTypeIF logserv) {
		this.logserv = logserv;
	}

	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String Index(Model model) {
	model.addAttribute("credentials", new Credentials());
	return "homepage";
}		
	
	
//	@RequestMapping(path = "/login", method = RequestMethod.GET)
//    public String login(Model model) {
//        model.addAttribute("user", new User());
//        return "homepage";
//    }
//	@RequestMapping(path = "/login", method = RequestMethod.POST)
//    public String loginView(User user,Model model) {
//		String employeedivision = user.getEmployeediv();
//		long employeeid = user.getEmployeeid();
//		if (employeedivision.equals("Admin")){
//			return "redirect:/admin";
//		}
//		else if (employeedivision.equals("Employee")){
//			return "redirect:/employee/"+employeeid;
//		}
//		else if (employeedivision.equals("Manager")){
//			return "redirect:/manager/"+employeeid;
//		}
//		else {
//			return "homepage";
//		}
//    }
//	@RequestMapping(path="/admin")
//	public String Admin()
//	{
//		return "homeAdmin";
//	}
//	@RequestMapping(path="/employee/{id}", method = RequestMethod.GET)
//	public String Employee(@PathVariable(value = "id") int employeeid,Model model, User user)
//	{	user.setEmployeeid(employeeid);
//		model.addAttribute("user", user);
//		return "homeEmployee";
//	}
//	
//	@RequestMapping(path="/manager/{id}")
//	public String Manager(@PathVariable(value = "id") int employeeid,Model model, User user)
//	{	user.setEmployeeid(employeeid);
//		model.addAttribute("user", user);
//		return "homeManager";
//	}

	@RequestMapping(path = "/login", method=RequestMethod.POST)
	public String authenticateLogin(@ModelAttribute Credentials credentials, Model model) {
	Credentials cred = authserv.authenticate(credentials.getEmployeeuid(), credentials.getEmployeepwd());
	User user = logserv.loginType(cred.getEmployeeid());	
	long employeeid = user.getEmployeeid();
	if (user.getEmployeediv().equals("Admin")) {
		return "redirect:/admin";
	}
	else if(user.getEmployeediv().equals("User")){
		return "redirect:/employee/"+ employeeid;
	}
	else if(user.getEmployeediv().equals("Manager")) {
		return "redirect:/manager/" + employeeid;
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
