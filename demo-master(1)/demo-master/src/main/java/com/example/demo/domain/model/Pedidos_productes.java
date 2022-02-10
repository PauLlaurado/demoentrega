package com.example.demo.domain.model;

import com.example.demo.domain.model.compositekeys.KeyPedidoProducte;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;
@Entity
@Table(name = "pedidos_productes")
@IdClass(KeyPedidoProducte.class)
public class Pedidos_productes {


    @Id
    public UUID pedidosid;

    @Id
    public UUID producteid;
}
