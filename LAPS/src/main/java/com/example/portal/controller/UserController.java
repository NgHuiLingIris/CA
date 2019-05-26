package com.example.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(path = "/home/viewEmployee", method = RequestMethod.GET)
	public String viewemployee(Model model) {
		ArrayList<User> plist = (ArrayList<User>) userRepository.findAll();
		model.addAttribute("users", plist);
		return "viewEmployee";
	}

	@GetMapping("/home/addEmployee")
	public String homesendForm(User user, Model model) {
		model.addAttribute("users", userRepository.findAll());
		System.out.println("REACH HERE");
		return "addEmployee";
	}

	@PostMapping("/home/addEmployee")
	public String processForm(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("here1");
			model.addAttribute("users", userRepository.findAll());
			return "addEmployee";
		}
		System.out.println("here2");
		userRepository.save(user);
		System.out.println("here3");
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
		model.addAttribute("managers", userRepository.findAll());
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
