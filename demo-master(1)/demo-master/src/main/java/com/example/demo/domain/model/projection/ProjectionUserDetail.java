package com.example.demo.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionUserDetail {
    UUID getUserid();
    String getUsername();
    @JsonIgnoreProperties("pedidos")
    Set<ProjectionPedidos> getPedidos();
}