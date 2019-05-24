package com.example.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {
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
