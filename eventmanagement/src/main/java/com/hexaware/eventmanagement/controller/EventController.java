package com.hexaware.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hexaware.eventmanagement.customexceptions.EventNotFoundException;
import com.hexaware.eventmanagement.customexceptions.InvalidEventDataException;
import com.hexaware.eventmanagement.customexceptions.NameAlreadyExistsException;
import com.hexaware.eventmanagement.dto.EventDTO;
import com.hexaware.eventmanagement.service.EventService;

import jakarta.validation.Valid;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("eventsList")
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) throws EventNotFoundException {
        EventDTO eventDTO = eventService.getEventById(id);
        return ResponseEntity.ok(eventDTO);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO) throws InvalidEventDataException,NameAlreadyExistsException {
        EventDTO createdEventDTO = eventService.createEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEventDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) throws EventNotFoundException, InvalidEventDataException {
        EventDTO updatedEventDTO = eventService.updateEvent(id, eventDTO);
        return ResponseEntity.ok(updatedEventDTO);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) throws EventNotFoundException {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
