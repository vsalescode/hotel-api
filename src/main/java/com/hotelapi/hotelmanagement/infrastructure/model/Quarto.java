package com.hotelapi.hotelmanagement.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "quarto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quarto")
    private Long id;

    private Integer numeroLeitos;

    private String tipo;

    private BigDecimal precoDiaria;

    private String status;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;
}
