package com.example.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.portal.model.User;
import com.example.portal.repo.HolidayRepository;
import com.example.portal.repo.UserRepository;

@Controller
public class UserController {
	private UserRepository userRepository;
	private HolidayRepository hRepo;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public HolidayRepository gethRepo() {
		return hRepo;
	}

	@Autowired
	public void sethRepo(HolidayRepository hRepo) {
		this.hRepo = hRepo;
	}

	//What I add here...Pagination Version 2.0
	   @RequestMapping(path = "/home/viewEmployee", method = RequestMethod.GET)
	   public String viewemployee(HttpServletRequest request, Model model) {
		   //ArrayList<User> plist = (ArrayList<User>)userRepository.findAll();//不要加plist，直接传入addAttribute即可
		   //model.addAttribute("users", plist);
		   int page = 0;//default page number is 0
		   int size = 5; //default page size is 3
		   //model.addAttribute("data",userRepository.findAll(new PageRequest(page, 3)));
		   
		   if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }

	        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }

		   model.addAttribute("users",userRepository.findAll(PageRequest.of(page, size)));
		   model.addAttribute("currentPage", page);
		   return "viewEmployee";
	    }
	@GetMapping("/home/addEmployee")
	public String homesendForm(User user, Model model) {
		model.addAttribute("managers", userRepository.findManagerList());
		
		return "addEmployee";
	}

	@PostMapping("/home/addEmployee")
	public String processForm(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("users", userRepository.findAll());
			return "addEmployee";
		}
		userRepository.save(user);
		model.addAttribute("users", userRepository.findAll());
		return "confirmnewemployee";
	}

	@RequestMapping(path = "/home/edit/{employeeid}", method = RequestMethod.GET)
	public String EditUser(@PathVariable(value = "employeeid") long employeeid, User user, Model model) {
		user = userRepository.findById(employeeid).orElse(null);
		//to retrieve for selected user
		userRepository.save(user);
		model.addAttribute("user", user);
		//to retrieve for approvers
		model.addAttribute("users", userRepository.findAll());
		return "updateEmployee";
	}

	@RequestMapping(path = "/home/edit/{employeeid}", method = RequestMethod.POST)
	public String updateUser(@PathVariable(value = "employeeid") long employeeid, @Valid User user,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "updateEmployee";
		}
		userRepository.save(user);
		ArrayList<User> plist = (ArrayList<User>) userRepository.findAll();
		model.addAttribute("users", plist);
		return "redirect:/home/viewEmployee";

	}

	@RequestMapping(path = "/home/delete/{employeeid}", method = RequestMethod.GET)
	public String deleteLeave(@PathVariable(name = "employeeid") long employeeid, Model model, User user) {
		userRepository.delete(userRepository.findById(employeeid).orElse(null));
		model.addAttribute("user", userRepository.findAll());
		return "redirect:/home/viewEmployee";
	}

}
