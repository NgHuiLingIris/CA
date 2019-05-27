package com.example.portal.repo;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import  com.example.portal.model.Leave;


@Repository
public interface LeaveRepository extends JpaRepository<Leave,Integer> {
	@Query(
			  value = "select l.id,u.employeename, l.leave_type, l.reason, l.status, l.from_date, l.to_date, l.duration,l.granularity,l.overseas_contact_details, l.employee_id,l.manager_comment\r\n" + 
			  		"from leave_app l join user1 u\r\n" + 
			  		"on l.employee_id = u.employeeid\r\n" + 
			  		"where l.employee_id= :employee_id and status <> \"Approved\"", 
			  nativeQuery = true)
				Collection<Leave> findAllByEmployeeid(int employee_id);
	
	@Query(
			  value = "SELECT l.id,u.employeename, l.leave_type, l.reason, l.status, l.from_date, l.to_date, l.duration,l.granularity,l.overseas_contact_details, l.employee_id,l.manager_comment \r\n" + 
			  		"FROM leave_app l join user1 u\r\n" + 
			  		"	on l.employee_id = u.employeeid\r\n" + 
			  		"where l.employee_id in (\r\n" + 
			  		"	select employeeid\r\n" + 
			  		"    from user1 \r\n" + 
			  		"    where reportsto = :managerid\r\n" + 
			  		"    )\r\n" + 
			  		"    and leave_type = 'Compensation'", 
			  nativeQuery = true)
			Collection<Leave> findAllPendingCompensationLeave(int managerid);
	
	@Query(
			  value = "SELECT * FROM leave_app where status='Pending' AND leave_type<>'Compensation'", 
			  nativeQuery = true)
			Collection<Leave> findAllPendingLeave();

	@Query(
			  value = "SELECT l.id,u.employeename, l.leave_type, l.reason, l.status, l.from_date, l.to_date, l.duration,l.granularity,l.overseas_contact_details, l.employee_id,l.manager_comment \r\n" + 
			  		"FROM leave_app l join user1 u\r\n" + 
			  		"on l.employee_id = u.employeeid\r\n" + 
			  		"where l.employee_id in (\r\n" + 
			  		"select employeeid\r\n" + 
			  		"from user1 \r\n" + 
			  		"where reportsto = :managerid\r\n" + 
			  		")\r\n" + 
			  		"and leave_type <> 'Compensation'", 
			  nativeQuery = true)
			Collection<Leave> findAllSubLeave(int managerid);
	
	@Query(
			  value = "select l.id,u.employeename, l.leave_type, l.reason, l.status, l.from_date, l.to_date, l.duration,l.granularity,l.overseas_contact_details, l.employee_id,l.manager_comment\r\n" + 
			  		"from leave_app l join user1 u\r\n" + 
			  		"	on l.employee_id = u.employeeid", 
			  nativeQuery = true)
			Collection<Leave> findAllLeaveAdmin();
	
	@Query(
			  value = "select l.id,u.employeename, l.leave_type, l.reason, l.status, l.from_date, l.to_date, l.duration,l.granularity,l.overseas_contact_details, l.employee_id,l.manager_comment\r\n" + 
			  		"from leave_app l join user1 u\r\n" + 
			  		"on l.employee_id = u.employeeid\r\n" + 
			  		"where l.employee_id= :employeeid and status = \"Approved\"", 
			  nativeQuery = true)
			Collection<Leave> findAllApprovedLeaveByEmployee(int employeeid);

}

