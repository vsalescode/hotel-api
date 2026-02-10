package com.hotelapi.hotelmanagement.business.service;

import com.hotelapi.hotelmanagement.infrastructure.model.Hotel;
import com.hotelapi.hotelmanagement.infrastructure.model.Quarto;
import com.hotelapi.hotelmanagement.infrastructure.repository.HotelRepository;
import com.hotelapi.hotelmanagement.infrastructure.repository.QuartoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuartoService {

    private final QuartoRepository quartoRepository;
    private final HotelRepository hotelRepository;

    @Transactional
    public Quarto criarQuarto(Long hotelId, Quarto quarto) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel não encontrado para vincular o quarto."));

        quarto.setHotel(hotel);
        if (quarto.getStatus() == null) {
            quarto.setStatus("DISPONIVEL");
        }

        return quartoRepository.save(quarto);
    }

    public List<Quarto> listarPorHotel(Long hotelId) {
        return quartoRepository.findByHotelId(hotelId);
    }

    @Transactional
    public void alterarStatus(Long id, String novoStatus) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado."));
        quarto.setStatus(novoStatus);
        quartoRepository.save(quarto);
    }
}