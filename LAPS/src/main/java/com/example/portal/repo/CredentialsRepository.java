package com.example.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.portal.model.Credentials;

public interface CredentialsRepository extends JpaRepository<Credentials, String>{
	
}
