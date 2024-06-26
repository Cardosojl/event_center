package com.cardosojl.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cardosojl.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	
	@Query("SELECT e FROM Event e WHERE DATE(e.date) = :date")
	List<Event> findEventByDate(@Param("date") Date date);
	

}
