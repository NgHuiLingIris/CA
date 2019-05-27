package com.example.portal.service;

import org.springframework.stereotype.Service;

import com.example.portal.model.User;

@Service
public interface LoginTypeIF {
	
	User loginType(long employeeid);

}