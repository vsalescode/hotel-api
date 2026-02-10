package com.hotelapi.hotelmanagement.infrastructure.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel")
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Size(max = 50)
    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Email(message = "Email inválido")
    @NotBlank
    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "rua", length = 150)
    private String rua;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "cep", length = 10)
    private String cep;

    @NotBlank
    @Column(name = "cidade", length = 100)
    private String cidade;

    @NotBlank
    @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
    @Column(name = "estado", length = 2)
    private String estado;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Funcionario> funcionarios = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Quarto> quartos = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reserva> reservas = new ArrayList<>();





}
