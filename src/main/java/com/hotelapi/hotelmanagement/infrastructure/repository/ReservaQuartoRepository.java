package com.hotelapi.hotelmanagement.infrastructure.repository;

import com.hotelapi.hotelmanagement.infrastructure.model.ReservaQuarto;
import com.hotelapi.hotelmanagement.infrastructure.model.ReservaQuartoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaQuartoRepository extends JpaRepository<ReservaQuarto, ReservaQuartoId> {
}