package com.example.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portal.model.leavetype;

@Repository
public interface LeavetypeRepository extends JpaRepository<leavetype, Integer>{

}
