package com.hotelapi.hotelmanagement.business.service;

import com.hotelapi.hotelmanagement.infrastructure.model.Hospede;
import com.hotelapi.hotelmanagement.infrastructure.repository.HospedeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospedeService {

    private final HospedeRepository hospedeRepository;

    @Transactional
    public Hospede cadastrar(Hospede hospede) {
        if (hospedeRepository.existsById(hospede.getCpf())) {
            throw new IllegalArgumentException("Já existe um hóspede cadastrado com este CPF: " + hospede.getCpf());
        }
        return hospedeRepository.save(hospede);
    }

    public Hospede buscarPorCpf(String cpf) {
        return hospedeRepository.findById(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Hóspede não encontrado com CPF: " + cpf));
    }

    public List<Hospede> listarTodos() {
        return hospedeRepository.findAll();
    }

    @Transactional
    public void remover(String cpf) {
        Hospede hospede = buscarPorCpf(cpf);
        hospedeRepository.delete(hospede);
    }
}