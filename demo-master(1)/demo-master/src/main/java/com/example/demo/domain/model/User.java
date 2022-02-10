package com.example.demo.domain.model;


import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="usser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID userid;

    public String username;
    public String password;
    public String role;
    public String groups;
    public boolean enabled;

    @ManyToMany
    @JoinTable(name = "favorite", joinColumns = @JoinColumn(name ="userid"), inverseJoinColumns = @JoinColumn(name = "pedidosid"))
    public Set<Pedidos> pedidos;

//    @ManyToMany
//    @JoinTable(name = "missatgesremitent", joinColumns = @JoinColumn(name ="userid"), inverseJoinColumns = @JoinColumn(name = "pedidosid"))
//    public Set<Misatges> misatgesremi;
//
//    @ManyToMany
//    @JoinTable(name = "missatgesdesti", joinColumns = @JoinColumn(name ="userid"), inverseJoinColumns = @JoinColumn(name = "pedidosid"))
//    public Set<Misatges> misatgesdest;

}
