package com.hotelapi.hotelmanagement.infrastructure.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reserva_quarto")
@Getter
@Setter
public class ReservaQuarto {

    @EmbeddedId
    private ReservaQuartoId id;

    @ManyToOne
    @MapsId("idReserva")
    private Reserva reserva;

    @ManyToOne
    @MapsId("idQuarto")
    private Quarto quarto;
}
