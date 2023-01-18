package br.com.b3.eventgrid.participant.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private UUID participantId;
    private String targetUrl;
    private String eventType;
    private String authType;
    private String accessToken;

}
