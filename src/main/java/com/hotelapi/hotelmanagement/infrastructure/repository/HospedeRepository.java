package com.hotelapi.hotelmanagement.infrastructure.repository;

import com.hotelapi.hotelmanagement.infrastructure.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, String> {

}