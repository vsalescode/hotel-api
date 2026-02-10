package com.hotelapi.hotelmanagement.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    private String status;

    @ManyToOne
    @JoinColumn(name = "cpf_hospede")
    private Hospede hospede;

    @ManyToOne
    @JoinColumn(name = "cpf_funcionario")
    private Funcionario funcionario;


    @ManyToOne
    @JoinColumn(name = "id_hotel", nullable = false)
    private Hotel hotel;
}
