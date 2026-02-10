package com.hotelapi.hotelmanagement.infrastructure.repository;

import com.hotelapi.hotelmanagement.infrastructure.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel, Long> {

    boolean existsByEmail(String email);
    List<Hotel> findByCidadeIgnoreCase(String cidade);
    List<Hotel> findByCategoriaIgnoreCase(String categoria);
    Optional<Hotel> findByEmail(String email);


}
