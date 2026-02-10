package com.hotelapi.hotelmanagement.infrastructure.repository;

import com.hotelapi.hotelmanagement.infrastructure.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}