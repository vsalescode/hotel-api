package com.hotelapi.hotelmanagement.infrastructure.repository;



import com.hotelapi.hotelmanagement.infrastructure.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {

    List<Funcionario> findByHotelId(Long hotelId);
}