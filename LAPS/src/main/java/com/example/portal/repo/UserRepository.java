package com.example.portal.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.example.portal.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{


	

}
