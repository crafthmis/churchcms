package com.techbridge.apis.controller;

import com.techbridge.apis.model.Event;
import com.techbridge.apis.model.dto.EventDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class EventController extends BaseController {

    @GetMapping("/events")
    public ResponseEntity<Object> getCounties() {
        return eventService.getEvents();
    }


    @GetMapping("/event/{id}")
    public ResponseEntity<Object> getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @PostMapping("/event/add")
    public ResponseEntity<Object> addNewEvent(@RequestBody Event event) {
        return eventService.createNewEvent(event);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<Object> updateEvent(@PathVariable("id") Long id, @RequestParam(required = false) EventDto dto) {
        return eventService.updateEvent(id, dto);
    }
}
