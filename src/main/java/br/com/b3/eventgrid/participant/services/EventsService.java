package br.com.b3.eventgrid.participant.services;

import br.com.b3.eventgrid.participant.models.RegisterRequest;
import br.com.b3.eventgrid.participant.models.SetupRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    public boolean setup(SetupRequest request) {

        try {
            String body = mapper.writeValueAsString(request.getData());

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(eventServiceUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());


        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return true;
    }



}
