package com.example.portal.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.portal.model.Leave;

public interface LeaveServiceIF {

	Leave SaveLeave(Leave l);

}