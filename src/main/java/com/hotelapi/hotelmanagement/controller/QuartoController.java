package com.hotelapi.hotelmanagement.controller;

import com.hotelapi.hotelmanagement.business.service.QuartoService;
import com.hotelapi.hotelmanagement.infrastructure.model.Quarto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Quartos", description = "Gerenciamento de quartos e disponibilidades")
@RestController
@RequestMapping("/api/quartos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuartoController {

    private final QuartoService quartoService;

    @Operation(summary = "Adicionar quarto a um hotel")
    @PostMapping("/hotel/{hotelId}")
    public ResponseEntity<Quarto> criar(@PathVariable Long hotelId, @RequestBody Quarto quarto) {
        return ResponseEntity.ok(quartoService.criarQuarto(hotelId, quarto));
    }

    @Operation(summary = "Listar quartos de um hotel")
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Quarto>> listarPorHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(quartoService.listarPorHotel(hotelId));
    }

    @Operation(summary = "Alterar status do quarto (Disponível/Ocupado/Manutenção)")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestParam String novoStatus) {
        quartoService.alterarStatus(id, novoStatus);
        return ResponseEntity.noContent().build();
    }
}