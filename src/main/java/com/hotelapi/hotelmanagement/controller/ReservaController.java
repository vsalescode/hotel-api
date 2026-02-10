package com.hotelapi.hotelmanagement.controller;

import com.hotelapi.hotelmanagement.business.service.ReservaService;
import com.hotelapi.hotelmanagement.infrastructure.model.Reserva;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Reservas", description = "Gerenciamento de reservas e estadias")
@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReservaController {

    private final ReservaService reservaService;

    @Operation(summary = "Criar nova reserva")
    @PostMapping
    public ResponseEntity<Reserva> criar(@RequestBody Reserva reserva) {
        Reserva criada = reservaService.criarReserva(reserva);
        return ResponseEntity.created(URI.create("/reservas/" + criada.getId())).body(criada);
    }

    @Operation(summary = "Cancelar reserva")
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }
}