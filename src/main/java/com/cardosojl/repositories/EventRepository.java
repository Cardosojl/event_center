package com.cardosojl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardosojl.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

}
