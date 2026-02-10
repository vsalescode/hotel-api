package com.hotelapi.hotelmanagement.infrastructure.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagamento")
@Getter
@Setter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String formaPagamento;

    private LocalDate dataPagamento;

    private BigDecimal valorTotal;

    private String status;

    @OneToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;
}
