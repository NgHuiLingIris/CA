package com.example.portal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portal.model.Credentials;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, String>{
	
	public List<Credentials> findCredentialsByEmployeeuidAndEmployeepwd(String employeeuid, String employeepwd);
}
