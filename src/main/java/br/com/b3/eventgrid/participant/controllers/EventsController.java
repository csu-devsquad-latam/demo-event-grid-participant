package br.com.b3.eventgrid.participant.controllers;

import br.com.b3.eventgrid.participant.models.EventGridResponse;
import br.com.b3.eventgrid.participant.models.RegisterRequest;
import br.com.b3.eventgrid.participant.models.ResponseMessage;
import br.com.b3.eventgrid.participant.models.SettlementRequest;
import br.com.b3.eventgrid.participant.models.BypassRequest;
import br.com.b3.eventgrid.participant.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @PostMapping("setup")
    ResponseEntity<String> setup(@RequestBody BypassRequest<RegisterRequest> request) {
        String result = eventsService.setup(request);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("startSettlement")
    ResponseEntity<String> start(@RequestBody BypassRequest<SettlementRequest> request) {
        String result = eventsService.start(request);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("events")
    ResponseEntity<EventGridResponse> events() {
        return ResponseEntity.ok().build();
    }

}
