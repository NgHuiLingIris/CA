package com.example.portal.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.portal.model.Holiday;
import com.example.portal.repo.HolidayRepository;
import com.example.portal.repo.UserRepository;

@Controller
public class HolidayController {
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
	@RequestMapping(path = "/home/editholiday/{id}", method = RequestMethod.GET)
    public String editHoliday( 
       @PathVariable(name = "id") long id, Model model,Holiday h) {
		h= hRepo.findById(id).orElse(null);
	         hRepo.save(h);
	     
			 	model.addAttribute("holiday",h);
	    	  return "addHoliday";
    }
	
    @RequestMapping(path = "/home/editholiday/{id}", method = RequestMethod.POST)
    public String updateHoliday( 
       @PathVariable(name = "id") long id, Model model,@Valid Holiday h) {
	    
	         hRepo.save(h);
	         ArrayList<Holiday> plist = (ArrayList<Holiday>) hRepo.findAll();
			 	model.addAttribute("holiday", plist);
	    	  return "redirect:/home/viewholiday";
    }
    
    
  
 //ADMIN-create holiday list
    @RequestMapping(path = "/home/addholiday", method = RequestMethod.GET)
    public String createLeave(Model model) {
        model.addAttribute("holiday", new Holiday());
        return "addHoliday";
    }


	  @RequestMapping(path = "/home/addholiday", method = RequestMethod.POST)
	    public String saveLeave(@Valid  Holiday h, BindingResult bindingResult, Model model) {
	    	if (bindingResult.hasErrors()) {
	    		 return "addHoliday";
	        }
	  
	  	  hRepo.save(h);
    	  ArrayList<Holiday> plist = (ArrayList<Holiday>) hRepo.findAll();
		 	model.addAttribute("holiday", plist);
	       
	
		 	  return "viewHoliday";
	    }
	    @RequestMapping(path = "/home/viewholiday", method = RequestMethod.GET)
	    public String viewHoliday( Model model) {
	 
	  	  
    	  ArrayList<Holiday> plist = (ArrayList<Holiday>) hRepo.findAll();
		 	model.addAttribute("holiday", plist);
	       
	
		 	  return "viewHoliday";
	
	    }
	  

	  
	    //admin- delete for holiday

	    
	    @RequestMapping(path = "/home/deleteholiday/{id}", method = RequestMethod.GET)
	    public String deleteHoliday(@PathVariable(name = "id") long  id,Holiday h,Model m) {
	    	 hRepo.delete(  hRepo.findById(id).orElse(null));
	    	 m.addAttribute("holiday", hRepo.findAll());
	    	 return "viewHoliday";
	    }
	
}
