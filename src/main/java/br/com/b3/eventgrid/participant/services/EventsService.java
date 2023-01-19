package br.com.b3.eventgrid.participant.services;

import br.com.b3.eventgrid.participant.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class EventsService {

    private final String SubscriptionValidationEvent = "Microsoft.EventGrid.SubscriptionValidationEvent";


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
                    .uri(new URI(request.getTargetUrl()))
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
                    .uri(new URI(request.getTargetUrl()))
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

    public Response events(List<EventGridRequest> eventList) throws Exception {
        if (eventList == null || eventList.size() == 0) {
            throw new Exception("No Request Body Available");
        }

        var request = eventList.get(0);

        System.out.printf("Event Type %s \n", request.getEventType());

        Response response = null;

        switch (request.getEventType()) {
            case SubscriptionValidationEvent:

                var eventGridValidationCodeRequest = new ValidationEventGridRequestData();

                eventGridValidationCodeRequest.setValidationCode(request.getData().get("validationCode"));
                eventGridValidationCodeRequest.setValidationUrl(request.getData().get("validationUrl"));
                response = new EventGridResponse();
                ((EventGridResponse)response).setValidationResponse(eventGridValidationCodeRequest.getValidationCode());
                break;
            default:
                request.getData().keySet().forEach( q -> {
                    System.out.printf( "key: %s - value: %s \n", q, request.getData().get(q) );
                });
                response = new GenericResponse();
                ((GenericResponse)response).setData(request.getData());
        }

        return response;
    }

}
