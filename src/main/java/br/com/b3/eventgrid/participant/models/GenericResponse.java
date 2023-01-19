package br.com.b3.eventgrid.participant.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse extends Response{
    private Object data;
}
