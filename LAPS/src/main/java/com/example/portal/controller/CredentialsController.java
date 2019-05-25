package com.example.portal.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.portal.model.Credentials;
import com.example.portal.repo.CredentialsRepository;

@Controller
public class CredentialsController {

	private CredentialsRepository cRepo;
	
	@Autowired
	public void setcRepo(CredentialsRepository cRepo) {
		this.cRepo = cRepo;
	}
	
	@GetMapping("/home/createCredentials")
	public ModelAndView createCreds() {
		ModelAndView mav = new ModelAndView("createCredentials");
		mav.addObject("credentials", new Credentials());
		return mav;
	}

	@PostMapping("/home/createCredentials")
	public String createCreds(@ModelAttribute("credentials") Credentials creds) {
		if(creds.getEmployeeid() == null) {
			String uuid = UUID.randomUUID().toString();
			creds.setEmployeeid(uuid);
		}
		cRepo.save(creds);
		return "redirect:/home/manageCredentials";
	}
	
	@GetMapping("/home/manageCredentials")
	public ModelAndView manageCredentials() {
		ModelAndView mav = new ModelAndView("manageCredentials");
		List credlist = cRepo.findAll();
		mav.addObject("credlist", credlist);
		return mav;
	}
	
	@GetMapping("/home/editCredentials/{id}")
	public ModelAndView editCredentials(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("editCredentials");
		Optional<Credentials> c = cRepo.findById(id);
		if(c.isPresent()) {
			Credentials cred = c.get();
			mav.addObject("cred", cred);
			return mav;
			}
		return mav;
		}
	
	@PostMapping("/home/editCredentials")
	public String editCred(@ModelAttribute("cred") Credentials cred) {
		cRepo.save(cred);
		return "redirect:/home/manageCredentials";
	}
	
	@GetMapping("/home/delCredentials/{id}")
	public String delCredentials(@PathVariable String id) {
		cRepo.deleteById(id);
		return "redirect:/home/manageCredentials";
	}
}
