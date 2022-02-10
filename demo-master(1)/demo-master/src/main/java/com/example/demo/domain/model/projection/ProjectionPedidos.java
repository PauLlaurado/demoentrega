package com.example.demo.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionPedidos {

    UUID getPedidosuuid();
    String getData();
    Boolean getEntregat();
    @JsonIgnoreProperties("productos")
    Set<ProjectionProducto> getProductos();

}
