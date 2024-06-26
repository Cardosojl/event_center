package com.cardosojl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardosojl.models.EventType;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long> {

}
