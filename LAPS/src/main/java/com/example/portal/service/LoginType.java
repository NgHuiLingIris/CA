package com.example.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portal.model.User;
import com.example.portal.repo.UserRepository;

@Service
public class LoginType implements LoginTypeIF {

	@Autowired
	private UserRepository uRepo;
	
	@Override
	public User loginType(long employeeid) {
		List<User> ulist = uRepo.findLoginTypeByEmployeeid(employeeid);
		return ulist.get(0);		
	}
	
}
