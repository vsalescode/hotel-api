package com.hotelapi.hotelmanagement.infrastructure.controller;
import com.hotelapi.hotelmanagement.business.service.HospedeService;
import com.hotelapi.hotelmanagement.infrastructure.model.Hospede;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hóspedes", description = "Gerenciamento de hóspedes do sistema")
@RestController
@RequestMapping("/api/hospedes")
@RequiredArgsConstructor
public class HospedeController {

    private final HospedeService hospedeService;

    @Operation(summary = "Cadastrar novo hóspede")
    @PostMapping
    public ResponseEntity<Hospede> cadastrar(@RequestBody Hospede hospede) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospedeService.cadastrar(hospede));
    }

    @Operation(summary = "Buscar hóspede por CPF")
    @GetMapping("/{cpf}")
    public ResponseEntity<Hospede> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(hospedeService.buscarPorCpf(cpf));
    }

    @Operation(summary = "Listar todos os hóspedes")
    @GetMapping
    public ResponseEntity<List<Hospede>> listarTodos() {
        return ResponseEntity.ok(hospedeService.listarTodos());
    }
}