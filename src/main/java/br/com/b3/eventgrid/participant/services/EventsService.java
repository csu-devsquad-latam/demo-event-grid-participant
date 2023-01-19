package br.com.b3.eventgrid.participant.services;

import br.com.b3.eventgrid.participant.models.RegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.UUID;

@Component
public class EventsService {

    @Value("eventService.participantId")
    private String participantId;
    @Value("eventService.url")
    private String eventServiceUrl;
    @Value("eventService.type")
    private String eventServiceType;
    @Value("eventService.auth.type")
    private String eventServiceAuthType;
    @Value("eventService.auth.token")
    private String eventServiceAuthToken;

    @Autowired
    private ObjectMapper mapper;

    public boolean setup() {

        try {
            RegisterRequest registerRequest = new RegisterRequest(
                    UUID.fromString(participantId),
                    eventServiceUrl,
                    eventServiceType,
                    eventServiceAuthType,
                    eventServiceAuthToken);

            String body = mapper.writeValueAsString(registerRequest);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(eventServiceUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return true;
    }



}
