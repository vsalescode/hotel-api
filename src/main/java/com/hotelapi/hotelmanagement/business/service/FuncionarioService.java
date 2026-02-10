package com.hotelapi.hotelmanagement.business.service;

import com.hotelapi.hotelmanagement.infrastructure.model.Funcionario;
import com.hotelapi.hotelmanagement.infrastructure.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Transactional
    public Funcionario cadastrar(Funcionario funcionario) {
        if (funcionarioRepository.existsById(funcionario.getCpf())) {
            throw new IllegalArgumentException("Funcionário já cadastrado com este CPF.");
        }
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarPorHotel(Long hotelId) {
        return funcionarioRepository.findByHotelId(hotelId);
    }
}