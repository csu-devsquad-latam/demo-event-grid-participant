package br.com.b3.eventgrid.participant.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SetupRequest {

    private String targetUrl;
    private RegisterRequest data;
}
