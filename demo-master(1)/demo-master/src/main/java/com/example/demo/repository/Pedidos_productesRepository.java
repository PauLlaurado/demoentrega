package com.example.demo.repository;

import com.example.demo.domain.model.Pedidos_productes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface Pedidos_productesRepository extends JpaRepository<Pedidos_productes, UUID> {
    List<Pedidos_productesRepository> findBy();

}
