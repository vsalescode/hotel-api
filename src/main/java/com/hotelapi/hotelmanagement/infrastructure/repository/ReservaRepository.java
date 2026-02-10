package com.hotelapi.hotelmanagement.infrastructure.repository;

import com.hotelapi.hotelmanagement.infrastructure.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByHospedeCpf(String cpf);
}