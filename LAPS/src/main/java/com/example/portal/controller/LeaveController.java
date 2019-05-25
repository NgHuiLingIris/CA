package com.example.portal.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.portal.model.Holiday;
import com.example.portal.model.Leave;
import com.example.portal.model.User;
import com.example.portal.repo.HolidayRepository;
import com.example.portal.repo.LeaveRepository;
import com.example.portal.repo.UserRepository;
import com.example.portal.service.LeaveServiceIF;
import com.example.portal.util.ComputeLeave;

@Controller
public class LeaveController implements LeaveServiceIF{
	private UserRepository mRepo;
	private LeaveRepository lRepo;
	private HolidayRepository hRepo;
	@Autowired
	public void setlRepo(LeaveRepository lRepo) {
		this.lRepo = lRepo;
	}

	@Autowired
	public void setmRepo(UserRepository mRepo) {
		this.mRepo = mRepo;
	}
	
	@Autowired
	public void setmRepo(HolidayRepository hRepo) {
		this.hRepo = hRepo;
	}
	
	//Apply Leave HTML
	@RequestMapping(path = "/leaves/add", method = RequestMethod.GET)
    public String createLeave(Model model) {
        model.addAttribute("leave", new Leave());
        return "applyleave";
    }
	
	//View Leave HTML: Admin view: 0/0, Employee view:x/0 and Manager view: x/0 for subordinate: x/x
	@RequestMapping(path = "/leaves/{employeeid}/{managerid}", method = RequestMethod.GET)
    public String getAllLeave(@PathVariable(value = "employeeid") int employeeid, @PathVariable(value = "managerid") int managerid, Model model) {
		 ArrayList<Leave> plist = new ArrayList<Leave>();
		  if(employeeid == 0) //this is for Admin to view all
		  { 
			  plist = (ArrayList<Leave>) lRepo.findAll(); 
		  }
		  else if(employeeid != 0 && managerid == 0) //this is for employee & manager view to view their leaves
		  { 
			  plist = (ArrayList<Leave>)lRepo.findAllByEmployeeid(employeeid);
		  }
		  else if(employeeid != 0 && managerid != 0) //this is for manager view to see subordinate history
		  { 
			  plist = (ArrayList<Leave>)lRepo.findAllSubLeave(managerid);
		  }
		  model.addAttribute("leavelist",plist); 
        return "viewleave";
    } 
	@RequestMapping(path = "/leaves", method = RequestMethod.POST)
    public String saveLeave(@Valid Leave l, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
            return "applyleave";
        }
    	l=SaveLeave(l);
    	ArrayList<Leave> plist = (ArrayList<Leave>) lRepo.findAll();
    	model.addAttribute("leave_period", l.getDuration());
	 	model.addAttribute("leavelist", plist);
        return "viewleave";
    }
	
	// Update leave HTML
	@RequestMapping(path = "/leaves/edit/{id}", method = RequestMethod.GET)
    public String updateLeave( @PathVariable(value = "id") int id,Leave l,Model model) {   	
    	l = lRepo.findById(id).orElse(null);
    	System.out.println(l);
    	  lRepo.save(l);
        model.addAttribute("leaves", l);
        return "editleave";
    }

	@RequestMapping(path = "/leaves/edit/{id}", method = RequestMethod.POST)
	public String updateLeave(@PathVariable(value = "id") String id, @Valid Leave l,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "applyleave";
		}
		l=SaveLeave(l);
		String status = l.getStatus();
		if(status.equals("Approved"))
		{
			System.out.println("testApproved");
			DeductLeaveEntitled(l);
		}
		else {
			System.out.println("testNull");
		}
		ArrayList<Leave> plist = (ArrayList<Leave>) lRepo.findAll();
		model.addAttribute("leavelist", plist);

		return "viewleave";
	}
	//Delete Leave
	@RequestMapping(path = "/leaves/delete/{id}", method = RequestMethod.GET)
    public String deleteLeave(@PathVariable(name = "id") int id) {
    	lRepo.delete(lRepo.findById(id).orElse(null));
        return "viewleave";
    }
	//Manager Leave
	@RequestMapping(path = "/approveleave", method = RequestMethod.GET)
    public String getPendingLeaves(Model model) {
    	 ArrayList<Leave> plist = (ArrayList<Leave>) lRepo.findAllPendingLeave();
 		model.addAttribute("leavelist", plist);
        return "approveleave";
    }
	@RequestMapping(path = "/leaves/edit/managerview/{id}", method = RequestMethod.GET)
	  public String updateleaves( @PathVariable(value = "id") int id,Leave l,Model model) {   	
	    	l = lRepo.findById(id).orElse(null);
	    	System.out.println(l);
	    	  lRepo.save(l);
	    	
	        model.addAttribute("leaves", l);
	        return "updateLeave";
	    }
	public void DeductLeaveEntitled(Leave l) {
    	double duration = l.getDuration();
    	int employeeid = (int) l.getEmployeeId();
    	User u = mRepo.findByEmployeeid(employeeid);
    	double leaveEntitled = u.getLeaveentitled();
    	double remainingLeave = leaveEntitled - duration;
    	u.setLeaveentitled(remainingLeave);
    	mRepo.save(u);
	}
	
	//Compensation
	
	//note hardcoded, edit to /claimcompensation/{employeeid} 
    @RequestMapping(path = "/claimcompensation", method = RequestMethod.GET)
    public String EditLeave(User user,Model model, Leave leave) { 
    	long employeeid = 2;
    	user = mRepo.findById(employeeid).orElse(null);
    	System.out.println(user );
    	mRepo.save(user );
        model.addAttribute("user", user);
        Leave l = new Leave();
        model.addAttribute("Leave",l);
        return "claimcompensation";
    }
	  @RequestMapping(path = "/approvecompensation", method = RequestMethod.GET)
	    public String getPendingCompensation(Model model) {
	    	 ArrayList<Leave> plist = (ArrayList<Leave>) lRepo.findAllPendingCompensationLeave();
	 		model.addAttribute("leavelist", plist);
	        return "approvecompensation";
	    }
	  @RequestMapping(path = "/compleaves/edit/managerview/{id}", method = RequestMethod.GET)
	  public String updateCompensation( @PathVariable(value = "id") int id,Leave l,Model model) {   	
	    	l = lRepo.findById(id).orElse(null);
	    	System.out.println(l);
	    	  lRepo.save(l);
	        model.addAttribute("leaves", l);
	        return "updateleave";
	    }
	
	
	@Override
	public Leave SaveLeave(Leave l) {
		
		List<Holiday> hols = hRepo.findAll();
    	ArrayList<Date> holidays = (ArrayList<Date>) hols.stream().map(a -> a.getDate()).collect(Collectors.toList());
    	ComputeLeave ldt = new ComputeLeave(l.getFromDate(), l.getToDate(), holidays);
    	double diff = ldt.getDifference();
    	l.setDuration(diff);
    	lRepo.save(l);
        return l;
	}
}
