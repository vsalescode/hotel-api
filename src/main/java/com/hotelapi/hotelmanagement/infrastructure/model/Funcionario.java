package com.hotelapi.hotelmanagement.infrastructure.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

    @Id
    @Column(name = "cpf", length = 14)
    private String cpf;

    @NotBlank
    private String nome;

    private String telefone;
    private String email;

    private String login;
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;
}
