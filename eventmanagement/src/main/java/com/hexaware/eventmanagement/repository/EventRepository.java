package com.hexaware.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.eventmanagement.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	boolean existsByTitle(String title);
}
