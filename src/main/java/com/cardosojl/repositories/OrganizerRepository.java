package com.cardosojl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardosojl.models.Organizer;
@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

}
