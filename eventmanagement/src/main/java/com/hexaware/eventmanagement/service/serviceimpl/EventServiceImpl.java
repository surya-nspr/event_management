package com.hexaware.eventmanagement.service.serviceimpl;

import com.hexaware.eventmanagement.customexceptions.EventNotFoundException;
import com.hexaware.eventmanagement.customexceptions.InvalidEventDataException;
import com.hexaware.eventmanagement.customexceptions.NameAlreadyExistsException;
import com.hexaware.eventmanagement.dto.EventDTO;
import com.hexaware.eventmanagement.entity.Event;
import com.hexaware.eventmanagement.repository.EventRepository;
import com.hexaware.eventmanagement.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(event -> modelMapper.map(event, EventDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EventDTO getEventById(Long id) throws EventNotFoundException {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event"," id: ",id));
        return modelMapper.map(event, EventDTO.class);
    }

    @Override
    public EventDTO createEvent(EventDTO eventDTO) throws InvalidEventDataException, NameAlreadyExistsException {
        if (eventDTO == null) {
            throw new InvalidEventDataException("Event data cannot be null");
        }

        if (eventRepository.existsByTitle(eventDTO.getTitle())) {
            throw new NameAlreadyExistsException("Event with title " + eventDTO.getTitle() + " already exists");
        }

        Event event = modelMapper.map(eventDTO, Event.class);
        Event savedEvent = eventRepository.save(event);
        String action = "Created event with title: " + savedEvent.getTitle();
        return modelMapper.map(savedEvent, EventDTO.class);
        
    }

    @Override
    public EventDTO updateEvent(Long id, EventDTO eventDTO) throws EventNotFoundException, InvalidEventDataException {

        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event ", "id: ", id));

        if (eventDTO == null) {
            throw new InvalidEventDataException("Event data cannot be null");
        }

        if (eventDTO.getDate() != null) {
            existingEvent.setDate(eventDTO.getDate());
        }

        if (eventDTO.getLocation() != null && !eventDTO.getLocation().isEmpty()) {
            existingEvent.setLocation(eventDTO.getLocation());
        }

        Event updatedEvent = eventRepository.save(existingEvent);
        return modelMapper.map(updatedEvent, EventDTO.class);
    }

    @Override
    public void deleteEvent(Long id) throws EventNotFoundException {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException("Event ", "id: ",id);
        }
        eventRepository.deleteById(id);
    }
}
