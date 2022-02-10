package com.example.demo.repository;

import com.example.demo.domain.model.Pedidos;
import com.example.demo.domain.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductoRepository  extends JpaRepository<Producto, UUID> {
    <T> List<T> findBy(Class<T> type);
    <T> List<T> findByProducteid(UUID id, Class<T> type);

}
