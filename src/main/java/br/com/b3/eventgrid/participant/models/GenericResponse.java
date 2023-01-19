package br.com.b3.eventgrid.participant.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class GenericResponse extends Response{
    private Map<String, String> data;
}
