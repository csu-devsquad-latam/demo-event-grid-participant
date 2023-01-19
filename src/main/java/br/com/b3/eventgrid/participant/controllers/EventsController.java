package br.com.b3.eventgrid.participant.controllers;

import br.com.b3.eventgrid.participant.models.*;
import br.com.b3.eventgrid.participant.services.EventsService;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventsController {

    private final String SubscriptionValidationEvent = "Microsoft.EventGrid.SubscriptionValidationEvent";

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
    ResponseEntity<Response> events(@RequestBody List<EventGridRequest> eventList)  throws  Exception{

        if (eventList == null || eventList.size() == 0){
            throw new Exception("No Request Body Available");
        }

        var request = eventList.get(0);

        System.console().printf("Event Type",request.getEventType() );

        switch (request.getEventType()){
            case SubscriptionValidationEvent:
                var eventGridValidationCodeRequest = (ValidationEventGridRequestData)request.getData();

                var response = new EventGridResponse();
                response.setValidationResponse(eventGridValidationCodeRequest.getValidationCode());
                return ResponseEntity.ok(response);
            default:
                ObjectMapper mapper = new ObjectMapper();
                System.console().printf("Data: %s", mapper.writeValueAsString(request.getData()));

                var genericResponse = new GenericResponse();
                genericResponse.setData(request.getData());

                return ResponseEntity.ok(genericResponse);
        }
    }

}
