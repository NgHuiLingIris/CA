package com.example.portal.service;

import org.springframework.stereotype.Service;

import com.example.portal.model.Credentials;

@Service
public interface AuthenticateIF {

	Credentials authenticate(String employeeuid, String employeepwd);
}