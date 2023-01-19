package br.com.b3.eventgrid.participant.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EventGridRequest {
    private UUID id;
    private String topic;
    private String subject;
    private Object data;
    private String eventType;
    private LocalDate eventTime;
    private Integer metadataVersion;
    private Integer dataVersion;

}
