package com.example.portal.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.portal.model.Holiday;
import com.example.portal.model.Holiday;
@Repository
public interface HolidayRepository extends JpaRepository<Holiday,Long>{
	
}
