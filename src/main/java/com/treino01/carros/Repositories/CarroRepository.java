package com.treino01.carros.Repositories;

import com.treino01.carros.Model.Carro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    
}
