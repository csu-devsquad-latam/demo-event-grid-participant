package br.com.b3.eventgrid.participant.controllers;

import br.com.b3.eventgrid.participant.models.EventGridResponse;
import br.com.b3.eventgrid.participant.models.RegisterRequest;
import br.com.b3.eventgrid.participant.models.ResponseMessage;
import br.com.b3.eventgrid.participant.models.SettlementRequest;
import br.com.b3.eventgrid.participant.models.SetupRequest;
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
    ResponseEntity<ResponseMessage> setup(@RequestBody SetupRequest request) {
        eventsService.setup(request);
        ResponseMessage response = new ResponseMessage();
        response.setStatus("ok");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("startSettlement")
    ResponseEntity<ResponseMessage> start(@RequestBody SettlementRequest request) {
        eventsService.start(request);
        ResponseMessage response = new ResponseMessage();
        response.setStatus("ok");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("events")
    ResponseEntity<EventGridResponse> events() {
        return ResponseEntity.ok().build();
    }

}
