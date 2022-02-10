package com.example.demo.domain.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID pedidosuuid;

    public String data;
    public boolean entregat;

    @ManyToMany(mappedBy = "pedidos")
    public Set<User> user;

    @ManyToMany
    @JoinTable(name = "pedidos_productes", joinColumns = @JoinColumn(name ="pedidosid"), inverseJoinColumns = @JoinColumn(name = "producteid"))
    public Set<Producto> productos;

}
