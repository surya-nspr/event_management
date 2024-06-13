package com.hexaware.eventmanagement.service;
import java.util.List;

import com.hexaware.eventmanagement.customexceptions.InvalidEventDataException;
import com.hexaware.eventmanagement.customexceptions.NameAlreadyExistsException;
import com.hexaware.eventmanagement.dto.EventDTO;

public interface EventService {
    List<EventDTO> getAllEvents();
    EventDTO getEventById(Long id);
    EventDTO createEvent(EventDTO eventDTO) throws InvalidEventDataException, NameAlreadyExistsException;
    EventDTO updateEvent(Long id, EventDTO eventDTO);
    void deleteEvent(Long id);
}
