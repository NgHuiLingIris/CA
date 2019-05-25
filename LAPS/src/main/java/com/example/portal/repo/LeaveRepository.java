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
			  value = "SELECT * FROM leave_app WHERE employee_id = :employeeid", 
			  nativeQuery = true)
				Collection<Leave> findAllByEmployeeid(int employeeid);
	
	@Query(
			  value = "SELECT * FROM leave_app where status='Pending' AND leave_type='Compensation'", 
			  nativeQuery = true)
			Collection<Leave> findAllPendingCompensationLeave();
	
	@Query(
			  value = "SELECT * FROM leave_app where status='Pending' AND leave_type<>'Compensation'", 
			  nativeQuery = true)
			Collection<Leave> findAllPendingLeave();

	@Query(
			  value = "SELECT *\r\n" + 
			  		"FROM leave_app\r\n" + 
			  		"where employee_id in (\r\n" + 
			  		"	select employeeid\r\n" + 
			  		"    from user1 \r\n" + 
			  		"    where reportsto = (SELECT employeename FROM sa48.user1 WHERE employeeid = :managerid)\r\n" + 
			  		")", 
			  nativeQuery = true)
			Collection<Leave> findAllSubLeave(int managerid);
	

}

