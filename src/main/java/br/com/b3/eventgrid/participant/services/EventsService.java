package br.com.b3.eventgrid.participant.services;

import br.com.b3.eventgrid.participant.models.BypassRequest;
import br.com.b3.eventgrid.participant.models.RegisterRequest;
import br.com.b3.eventgrid.participant.models.SettlementRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class EventsService {

    @Value("eventService.participantId")
    private String participantId;
    @Value("eventService.url")
    private String eventServiceUrl;
    @Value("eventService.verifyUrl")
    private String verifyUrl;
    @Value("eventService.type")
    private String eventServiceType;
    @Value("eventService.auth.type")
    private String eventServiceAuthType;
    @Value("eventService.auth.token")
    private String eventServiceAuthToken;

    @Autowired
    private ObjectMapper mapper;

    public String setup(BypassRequest<RegisterRequest> request) {

        try {
            String body = mapper.writeValueAsString(request.getData());

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(eventServiceUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return response.body();
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public String start(BypassRequest<SettlementRequest> request) {

        try {
            String body = mapper.writeValueAsString(request.getData());

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(verifyUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
