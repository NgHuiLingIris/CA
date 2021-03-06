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
import com.example.portal.model.leavetype;
import com.example.portal.repo.HolidayRepository;
import com.example.portal.repo.LeaveRepository;
import com.example.portal.repo.LeavetypeRepository;
import com.example.portal.repo.UserRepository;
import com.example.portal.service.LeaveServiceIF;
import com.example.portal.util.ComputeLeave;

@Controller
public class LeaveController implements LeaveServiceIF {
	private UserRepository mRepo;
	private LeaveRepository lRepo;
	private HolidayRepository hRepo;
	private LeavetypeRepository ltRepo;

	@Autowired
	public void setLtRepo(LeavetypeRepository ltRepo) {
		this.ltRepo = ltRepo;
	}

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

	// Add Leave Type
	@RequestMapping(path = "/home/addleavetype", method = RequestMethod.GET)
	public String addLeaveType(Model model) {
		leavetype leavetype = new leavetype();
		model.addAttribute("leavetype", leavetype);
		return "addleavetype";
	}

	// Save Leave Type
	@RequestMapping(path = "/home/addleavetype", method = RequestMethod.POST)
	public String saveLeaveType(Model model, leavetype lt) {
		ltRepo.save(lt);
		return "redirect:/home/viewleavetype";
	}

	// View Leave Type
	@RequestMapping(path = "/home/viewleavetype", method = RequestMethod.GET)
	public String viewLeaveType(Model model, leavetype lt) {
		ArrayList<leavetype> ltlist = new ArrayList<leavetype>();
		ltlist = (ArrayList<leavetype>) ltRepo.findAll();
		model.addAttribute("leavetypelist", ltlist);
		return "viewleavetype";
	}
	// Edit Leave Type
	@RequestMapping(path = "/home/editleavetype/{id}", method = RequestMethod.GET)
	public String editLeaveType(@PathVariable(name="id") int id, leavetype lt, Model model) {
		lt = ltRepo.findById(id).orElse(null);
		ltRepo.save(lt);
		model.addAttribute("leavetype", lt);
		return "editleavetype";
	}
	// Save Edit Leave Type
	@RequestMapping(path = "/home/editleavetype/{id}", method = RequestMethod.POST)
	public String saveEditedLeaveType(Model model, leavetype lt) {
		ltRepo.save(lt);
		return "redirect:/home/viewleavetype";
	}
	@RequestMapping(path = "/home/deleteleavetype/{id}", method = RequestMethod.GET)
	public String deleteLeaveType(@PathVariable(name="id") int id, leavetype lt, Model model) {
		lt = ltRepo.findById(id).orElse(null);
		ltRepo.delete(lt);
		model.addAttribute("leavetype", lt);
		return "redirect:/home/viewleavetype";
	}
	// Apply Leave HTML
	@RequestMapping(path = "/leaves/add/{employeeid}/{managerid}", method = RequestMethod.GET)
	public String createLeave(@PathVariable(value = "employeeid") long employeeid,
			@PathVariable(value = "managerid") int managerid, Model model) {
		User user = new User();
		user.setEmployeeid(employeeid);
		user.setReportsto(managerid);
		model.addAttribute("ltlist", ltRepo.findAll());
		model.addAttribute("leave", new Leave());
		model.addAttribute("user", user);
		return "applyleave";
	}

	// View Leave HTML: Admin view: 0/0, Employee view:x/0 and Manager view: x/0 for
	// subordinate: x/x
	@RequestMapping(path = "/leaves/{employeeid}/{managerid}", method = RequestMethod.GET)
	public String getAllLeave(@PathVariable(value = "employeeid") int employeeid,
			@PathVariable(value = "managerid") int managerid, Model model) {
		ArrayList<Leave> plist = new ArrayList<Leave>();
		if (employeeid == 0 && managerid == 0) // this is for Admin to view all
		{
			System.out.println("Admin goes here");
			plist = (ArrayList<Leave>) lRepo.findAllLeaveAdmin();
		} else if (employeeid != 0 && managerid == 0) // this is for employee & manager view to view their leaves
		{
			plist = (ArrayList<Leave>) lRepo.findAllByEmployeeid(employeeid);
		} else if (managerid != 0) // this is for manager view to see subordinate history
		{
			plist = (ArrayList<Leave>) lRepo.findAllSubLeave(managerid);
		}
		model.addAttribute("leavelist", plist);
		return "viewleave";
	}

	@RequestMapping(path = "/leaves/{employeeid}/{managerid}", method = RequestMethod.POST)
	public String saveLeave(@Valid Leave l, BindingResult bindingResult,
			@PathVariable(value = "employeeid") int employeeid, @PathVariable(value = "managerid") int managerid,
			Model model) {
		if (bindingResult.hasErrors()) {
			// System.out.println("reach here error");
			User user = new User();
			user.setEmployeeid(employeeid);
			user.setReportsto(managerid);
			model.addAttribute("leave", l);
			model.addAttribute("user", user);
			if (l.getLeave_type().contains("Compensation")) {
				return "claimcompensation";
			}
			return "applyleave";
		}
		l = SaveLeave(l);
		return "redirect:/leaves/{employeeid}/{managerid}";
	}

	// to view approved leaves
	@RequestMapping(path = "/viewapproveleaves/{employeeid}/{managerid}", method = RequestMethod.GET)
	public String getApprovedLeavesLeave(@PathVariable(value = "employeeid") int employeeid,
			@PathVariable(value = "managerid") int managerid, Model model) {
		ArrayList<Leave> plist = new ArrayList<Leave>();
		plist = (ArrayList<Leave>) lRepo.findAllApprovedLeaveByEmployee(employeeid);
		model.addAttribute("leavelist", plist);
		return "viewapprovedleave";
	}

	// Update leave HTML
	@RequestMapping(path = "/leaves/edit/{id}/{employeeid}", method = RequestMethod.GET)
	public String updateLeave(@PathVariable(value = "id") int id, @PathVariable(value = "employeeid") int employeeid,
			Leave l, Model model) {
		l = lRepo.findById(id).orElse(null);
		lRepo.save(l);
		model.addAttribute("leaves", l);
		if (l.getLeave_type().contains("Compensation") == false) {
			return "editleave";
		} else {
			return "compensationeditleave";
		}
	}

	@RequestMapping(path = "/leaves/edit/{id}/{employeeid}", method = RequestMethod.POST)
	public String updateLeave(@PathVariable(value = "id") int id, @PathVariable(value = "employeeid") int employeeid,
			@Valid Leave l, BindingResult bindingResult, Model model) {
		int managerauthority;

		if (bindingResult.hasErrors()) {
			model.addAttribute("leaves", l);
			return "editleave";
		}
		l = SaveLeave(l);
		String status = l.getStatus();
		if (status.equals("Approved")) {
			if(l.getLeave_type().contains("Compensation"))
			{
				double duration = l.getDuration();
				double compused = duration * 8;
				User user = mRepo.findByEmployeeid((int) l.getEmployeeId());
				double ucomphours = user.getComphours();
				user.setComphours(ucomphours - compused);
			}
			System.out.println("testApproved");
			DeductLeaveEntitled(l);
			managerauthority = employeeid;
		} else if (status.equals("Rejected")) {
			System.out.println("manager view rejected");
			managerauthority = employeeid;
		} else {
			System.out.println("Edit Leave function");
			managerauthority = 0;
		}
		ArrayList<Leave> plist = (ArrayList<Leave>) lRepo.findAll();
		model.addAttribute("leavelist", plist);

		return "redirect:/leaves/{employeeid}/" + managerauthority;
	}

	// Cancel Leave
	@RequestMapping(path = "/leaves/cancel/{id}/{employeeid}", method = RequestMethod.GET)
	public String cancelLeave(@PathVariable(name = "id") int id, @PathVariable(name = "employeeid") int employeeid) {
		Leave l = lRepo.findById(id).orElse(null);
		l.setStatus("Cancel");
		lRepo.save(l);
		// lRepo.delete(lRepo.findById(id).orElse(null));
		int managerauthority = 0;
		return "redirect:/viewapproveleaves/{employeeid}/" + managerauthority;
	}

	// Delete Leave
	@RequestMapping(path = "/leaves/delete/{id}/{employeeid}", method = RequestMethod.GET)
	public String deleteLeave(@PathVariable(name = "id") int id, @PathVariable(name = "employeeid") int employeeid) {
		Leave l = lRepo.findById(id).orElse(null);
		l.setStatus("Deleted");
		lRepo.save(l);
		// lRepo.delete(lRepo.findById(id).orElse(null));
		int managerauthority = 0;
		return "redirect:/leaves/{employeeid}/" + managerauthority;
	}

	// Manager Leave
	@RequestMapping(path = "/approveleave/{managerid}", method = RequestMethod.GET)
	public String getPendingLeaves(@PathVariable(value = "managerid") int managerid, Model model) {
		System.out.println("findallapproveleave1");
		ArrayList<Leave> plist = (ArrayList<Leave>) lRepo.findAllSubLeave(managerid);
		User user = new User();
		user.setEmployeeid(managerid);
		System.out.println(user.getEmployeeid());
		model.addAttribute("user", user);
		model.addAttribute("leavelist", plist);
		return "approveleave";
	}

	@RequestMapping(path = "/approveleave/{managerid}", method = RequestMethod.POST)
	public String saveAndReturnSubLeaves(@Valid Leave l, @PathVariable(value = "managerid") int managerid,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("reach here error");
			return "applyleave";
		}
		l = SaveLeave(l);
		if (l.getLeave_type().contains("Compensation")) {
			return "redirect:/approvecompensation/{managerid}";
		}
		return "redirect:/approveleave/{managerid}";
	}

	@RequestMapping(path = "/leaves/edit/managerview/{id}/{employeeid}", method = RequestMethod.GET)
	public String updateleaves(@PathVariable(value = "id") int id, @PathVariable(value = "employeeid") int employeeid,
			Leave l, Model model) {
		l = lRepo.findById(id).orElse(null);
		System.out.println(l);
		lRepo.save(l);
		model.addAttribute("leaves", l);
		User user = new User();
		user.setEmployeeid(employeeid);
		model.addAttribute("user", user);
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

	// Compensation

	@RequestMapping(path = "/claimcompensation/{employeeid}", method = RequestMethod.GET)
	public String EditLeave(@PathVariable(name = "employeeid") long employeeid, User user, Model model, Leave leave) {
		user = mRepo.findById(employeeid).orElse(null);
		mRepo.save(user);
		model.addAttribute("user", user);
		double c = user.getComphours();
		double claim = (Math.floor(c/4))*0.5;
		double remainder = c-(Math.floor(c/4)*4);
		Leave l = new Leave();
		model.addAttribute("Leave", l);
		model.addAttribute("claim", claim);
		model.addAttribute("remainder", remainder);
		return "claimcompensation";
	}

	@RequestMapping(path = "/approvecompensation/{employeeid}", method = RequestMethod.GET)
	public String getPendingCompensation(@PathVariable(value = "employeeid") int employeeid, Model model) {
		User user = new User();
		user.setEmployeeid(employeeid);
		ArrayList<Leave> plist = (ArrayList<Leave>) lRepo.findAllPendingCompensationLeave(employeeid);
		model.addAttribute("leavelist", plist);
		model.addAttribute("user", user);
		return "approvecompensation";
	}

	@RequestMapping(path = "/compleaves/edit/managerview/{id}/{employeeid}", method = RequestMethod.GET)
	public String updateCompensation(@PathVariable(value = "id") int id,
			@PathVariable(value = "employeeid") int employeeid, Leave l, Model model) {
		l = lRepo.findById(id).orElse(null);
		System.out.println(l);
		lRepo.save(l);
		model.addAttribute("leaves", l);
		User user = new User();
		user.setEmployeeid(employeeid);
		model.addAttribute("user", user);
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
