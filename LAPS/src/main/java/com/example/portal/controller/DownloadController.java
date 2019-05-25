package com.example.portal.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.portal.model.User;
import com.example.portal.repo.UserRepository;
//import com.opencsv.CSVWriter;

// Controller for CSV Download

@Controller
public class DownloadController {
	
	
		private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping("/home/viewEmployee/download")
    public @ResponseBody String downloadempcsv(Model model)  {
			
	       String fileName = "src/main/resources/downloads/employee.csv";
	   
	        try{
	        	
	        	FileWriter fos = new FileWriter (fileName);
	      //  	CSVWriter writer = new CSVWriter(fos);
 	        	ArrayList<User> clist = (ArrayList<User>) userRepository.findAll();
 	        	String[] header = { "Employee Name", "Employee Role", "Leave Entitled",  "Reports To", "Employee Email"}; 
 	        //	writer.writeNext(header);	
				for(User users: clist)
				{
				String[] data= new String[] {
						(String) users.getEmployeename(),
						users.getEmployeediv(),
						users.getReportsto(),
						users.getEmployeemail(),
//						(String)users.getComphours()
						};
	     //       writer.writeNext(data);
	         

				}
			//    writer.close();
				 
	        }
	        catch (IOException e) { 

	        	 e.printStackTrace(); 
	        		       }
	   	 return "Your list Employee List is Downloaded";
	        }

	}


