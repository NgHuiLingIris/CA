package com.example.portal.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.example.portal.model.Holiday;
import com.example.portal.model.Leave;
import com.example.portal.repo.HolidayRepository;
import com.example.portal.repo.LeaveRepository;
import com.example.portal.util.ComputeLeave;

@Service
public class LeaveService implements LeaveServiceIF {
	private HolidayRepository hRepo;
	
	private LeaveRepository lRepo;
	
	@Autowired
	public void setlRepo(LeaveRepository lRepo) {
		this.lRepo = lRepo;
	}

	@Autowired
	public void sethRepo(HolidayRepository hRepo) {
		this.hRepo = hRepo;
	}
	
	@Override
	@Transactional
	public Leave SaveLeave(Leave l)
	{
		List<Holiday> hols = hRepo.findAll();
    	ArrayList<Date> holidays = (ArrayList<Date>) hols.stream().map(a -> a.getDate()).collect(Collectors.toList());
    	ComputeLeave ldt = new ComputeLeave(l.getFromDate(), l.getToDate(), holidays);
    	double diff = ldt.getDifference();
    	l.setDuration(diff);
    	lRepo.save(l);
        return l;

	}
}
