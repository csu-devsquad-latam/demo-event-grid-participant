package br.com.b3.eventgrid.participant.controllers;

import br.com.b3.eventgrid.participant.models.*;
import br.com.b3.eventgrid.participant.services.EventsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventsController {


    @Autowired
    private EventsService eventsService;

    @GetMapping("health")
    ResponseEntity<String> health() {
        return ResponseEntity.ok("{\"status\":\"ok\"}");
    }


    @PostMapping(value = "setup", consumes = {"application/json"})
    ResponseEntity<String> setup(@RequestBody BypassRequest<RegisterRequest> request) {
        String result = eventsService.setup(request);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "startSettlement", consumes = {"application/json"})
    ResponseEntity<String> start(@RequestBody BypassRequest<SettlementRequest> request) {
        String result = eventsService.start(request);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "events", consumes = {"application/json"})
    ResponseEntity<Response> events(@RequestBody List<EventGridRequest> eventList) throws Exception {
        var response = (Response) eventsService.events(eventList);
        return ResponseEntity.ok(response);
    }

}
