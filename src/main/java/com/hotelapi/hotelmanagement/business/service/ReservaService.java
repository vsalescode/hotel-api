package com.hotelapi.hotelmanagement.business.service;

import com.hotelapi.hotelmanagement.infrastructure.model.Reserva;
import com.hotelapi.hotelmanagement.infrastructure.model.ReservaQuarto;
import com.hotelapi.hotelmanagement.infrastructure.model.ReservaQuartoId;
import com.hotelapi.hotelmanagement.infrastructure.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final HotelRepository hotelRepository;

    @Transactional
    public Reserva criarReserva(Reserva reserva) {
        validarPeriodo(reserva.getDataEntrada(), reserva.getDataSaida());

        hotelRepository.findById(reserva.getHotel().getId())
                .orElseThrow(() -> new EntityNotFoundException("Hotel informado na reserva não existe."));

        reserva.setStatus("CONFIRMADA");
        return reservaRepository.save(reserva);
    }

    private void validarPeriodo(LocalDate entrada, LocalDate saida) {
        if (entrada == null || saida == null) {
            throw new IllegalArgumentException("Datas de entrada e saída são obrigatórias.");
        }
        if (saida.isBefore(entrada)) {
            throw new IllegalArgumentException("A data de saída não pode ser anterior à data de entrada.");
        }
        if (entrada.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Não é possível realizar reservas para datas passadas.");
        }
    }

    @Transactional
    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada."));
        reserva.setStatus("CANCELADA");
        reservaRepository.save(reserva);
    }
}