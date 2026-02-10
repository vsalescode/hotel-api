package com.hotelapi.hotelmanagement.infrastructure.repository;

import com.hotelapi.hotelmanagement.infrastructure.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    List<Quarto> findByHotelId(Long hotelId);
}