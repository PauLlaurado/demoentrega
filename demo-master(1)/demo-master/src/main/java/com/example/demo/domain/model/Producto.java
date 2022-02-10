package com.example.demo.domain.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "productes")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID producteid;

    public String nom;
    public int cant;

    @ManyToMany(mappedBy = "productos")
    public Set<Pedidos> pedidos;

}
