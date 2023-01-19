package br.com.b3.eventgrid.participant.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ValidationEventGridRequestData {

    private String validationCode;
    private String validationUrl;

}
