package com.example.portal.repo;
import java.util.Collection;

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
	
	

}
