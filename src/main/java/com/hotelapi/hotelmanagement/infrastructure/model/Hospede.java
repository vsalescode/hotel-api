package com.hotelapi.hotelmanagement.infrastructure.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "hospede")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospede {

    @Id
    @Column(name = "cpf", length = 14)
    private String cpf;

    @NotBlank
    private String nome;

    private String telefone;

    @Email
    private String email;

    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
}
