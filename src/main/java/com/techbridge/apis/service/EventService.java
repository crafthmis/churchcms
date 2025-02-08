package com.techbridge.apis.service;

import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.Event;
import com.techbridge.apis.model.dto.EventDto;
import com.techbridge.apis.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public ResponseEntity<Object> getEvents() {
        List<EventDto> EventDtos =  eventRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new EventDto(dto.getBrnId(),dto.getEtyId(),dto.getIsPledgeable(),dto.getName(),dto.getDescription(),dto.getFromDate(),dto.getToDate()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",EventDtos);
    }

    public ResponseEntity<Object> getEvent(Long id) {
        Optional<Event> Event = eventRepository.findById(id);
        if (Event.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such event with id: " + id);
        }
        EventDto EventDto = Event.map(dto -> new EventDto(dto.getBrnId(),dto.getEtyId(),dto.getIsPledgeable(),dto.getName(),dto.getDescription(),dto.getFromDate(),dto.getToDate()))
                .orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", EventDto);
    }

    public ResponseEntity<Object> createNewEvent(Event event) {

        Optional<Event> optionalEvent = eventRepository.findByName(event.getName());
        if (optionalEvent.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "Event already exists");
        }
        if (null != event.getName() && !event.getName().isEmpty()) {
            eventRepository.save(event);
            throw new CustomErrorException(HttpStatus.CREATED, "Event created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteEvent(Long id) {
        boolean exists = eventRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        eventRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "Event deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateEvent(Long EventId, EventDto dto) {
        Event event = eventRepository.findById(EventId).orElseThrow(() ->
                new IllegalArgumentException("No such Event with id: " + EventId));
        if (!Objects.isNull(dto)) {
            event.setBrnId(dto.getBrnId());
            event.setDescription(dto.getDescription());
            eventRepository.save(event);
            throw new CustomErrorException(HttpStatus.CREATED, "Event updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}


