package com.hotelapi.hotelmanagement.infrastructure.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ReservaQuartoId implements Serializable {

    private Long idReserva;
    private Long idQuarto;
}
