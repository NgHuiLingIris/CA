package com.example.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.portal.model.Credentials;
import com.example.portal.repo.CredentialsRepository;

@Service
public class Authenticate implements AuthenticateIF {

	@Autowired
	private CredentialsRepository cRepo;
	
	@Transactional
	public Credentials authenticate(String employeeuid, String employeepwd) {
		List<Credentials> clist = cRepo.findCredentialsByEmployeeuidAndEmployeepwd(employeeuid, employeepwd);
		return clist.get(0); 
	}
}
