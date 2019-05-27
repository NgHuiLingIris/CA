package com.example.portal.repo;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.portal.model.Leave;
import  com.example.portal.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	@Query(
			  value = "SELECT * FROM sa48.user1 where employeeid = :employeeid", 
			  nativeQuery = true)
	User findByEmployeeid(int employeeid);
	
	@Query(
			  value = "SELECT employeename FROM user1", 
			  nativeQuery = true)
	User findManagername();
	public List<User> findLoginTypeByEmployeeid(long employeeid);
	//ADMIN- Query to return the available Managers to map to the employee
		@Query("select user from User user where user.employeediv ='Manager' ")
	  
	    List<User> findManagerList();
}
