package br.com.b3.eventgrid.participant.models;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SettlementRequest {
    private UUID participantId;
    private UUID transactionId;
    private UUID settlementId;
}
