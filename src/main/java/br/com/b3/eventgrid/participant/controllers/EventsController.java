package br.com.b3.eventgrid.participant.controllers;

import br.com.b3.eventgrid.participant.models.EventGridResponse;
import br.com.b3.eventgrid.participant.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @PostMapping("setup")
    ResponseEntity setup() {
        eventsService.setup();
        return ResponseEntity.ok().build();
    }

    @PostMapping("startSettlement")
    ResponseEntity start() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("events")
    ResponseEntity<EventGridResponse> events() {
        return ResponseEntity.ok().build();
    }

}
