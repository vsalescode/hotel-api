package com.hotelapi.hotelmanagement.business.service;


import com.hotelapi.hotelmanagement.infrastructure.model.Hotel;
import com.hotelapi.hotelmanagement.infrastructure.repository.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;



    @Transactional
    public Hotel criarHotel(Hotel hotel) {

        validarEmailUnico(hotel.getEmail());

        validarDadosObrigatorios(hotel);

        return hotelRepository.save(hotel);
    }



    @Transactional
    public Hotel atualizarHotel(Long id, Hotel dadosAtualizados) {

        Hotel existente = buscarPorId(id);

        if (!existente.getEmail().equals(dadosAtualizados.getEmail())) {
            validarEmailUnico(dadosAtualizados.getEmail());
        }

        validarDadosObrigatorios(dadosAtualizados);

        existente.setNome(dadosAtualizados.getNome());
        existente.setCategoria(dadosAtualizados.getCategoria());
        existente.setTelefone(dadosAtualizados.getTelefone());
        existente.setEmail(dadosAtualizados.getEmail());

        existente.setRua(dadosAtualizados.getRua());
        existente.setNumero(dadosAtualizados.getNumero());
        existente.setComplemento(dadosAtualizados.getComplemento());
        existente.setBairro(dadosAtualizados.getBairro());
        existente.setCep(dadosAtualizados.getCep());
        existente.setCidade(dadosAtualizados.getCidade());
        existente.setEstado(dadosAtualizados.getEstado());

        return hotelRepository.save(existente);
    }



    public Hotel buscarPorId(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Hotel não encontrado com id: " + id));
    }

    public List<Hotel> listarTodos() {
        return hotelRepository.findAll();
    }

    public List<Hotel> buscarPorCidade(String cidade) {
        return hotelRepository.findByCidadeIgnoreCase(cidade);
    }

    public List<Hotel> buscarPorCategoria(String categoria) {
        return hotelRepository.findByCategoriaIgnoreCase(categoria);
    }



    @Transactional
    public void removerHotel(Long id) {

        Hotel hotel = buscarPorId(id);

        validarSePodeExcluir(hotel);

        hotelRepository.delete(hotel);
    }



    private void validarEmailUnico(String email) {

        boolean existe = hotelRepository.existsByEmail(email);

        if (existe) {
            throw new IllegalArgumentException(
                    "Já existe um hotel cadastrado com o email: " + email
            );
        }
    }

    private void validarDadosObrigatorios(Hotel hotel) {

        if (hotel.getNome() == null || hotel.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do hotel é obrigatório");
        }

        if (hotel.getCidade() == null || hotel.getCidade().isBlank()) {
            throw new IllegalArgumentException("Cidade é obrigatória");
        }

        if (hotel.getEstado() == null || hotel.getEstado().length() != 2) {
            throw new IllegalArgumentException("Estado deve ter 2 caracteres");
        }
    }

    private void validarSePodeExcluir(Hotel hotel) {

        if (!hotel.getQuartos().isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível excluir hotel com quartos cadastrados"
            );
        }

        if (!hotel.getFuncionarios().isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível excluir hotel com funcionários cadastrados"
            );
        }
    }
}
