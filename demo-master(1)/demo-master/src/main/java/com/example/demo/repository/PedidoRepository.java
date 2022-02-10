package com.example.demo.repository;

import com.example.demo.domain.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedidos, UUID> {
    <T> List<T> findBy(Class<T> type);
    <T> List<T> findByPedidosuuid(UUID id, Class<T> type);

}
