package com.example.demo.controller;

import com.example.demo.domain.dto.ResponseList;
import com.example.demo.domain.dto.ResponseMessage;
import com.example.demo.domain.model.Pedidos;
import com.example.demo.domain.model.Pedidos_productes;
import com.example.demo.domain.model.projection.ProjectionPedidos;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.Pedidos_productesRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidoRepository pedidorepository;
    private Pedidos_productesRepository pedidosproductesRepository;
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<?> findallpedidos() {
        return ResponseEntity.ok().body(new ResponseList(pedidorepository.findBy(ProjectionPedidos.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findonepedido(@PathVariable UUID id) {
        return ResponseEntity.ok().body(new ResponseList(pedidorepository.findByPedidosuuid(id,ProjectionPedidos.class)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> createpedidos(@RequestBody Pedidos pedidos,  @PathVariable UUID id, Authentication authentication) {
        if (authentication != null) {
            Pedidos_productes pedidos_productes = new Pedidos_productes();
            pedidos_productes.pedidosid = pedidos.pedidosuuid;
            pedidos_productes.producteid = id;
            pedidorepository.save(pedidos);
            pedidosproductesRepository.save(pedidos_productes);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseMessage.message("No s'ha pogut realitzar la comanda comproba com a client si estan totes les dades correctes"));

    }
    @Modifying
    @Query("update pedidos set pedidos0 set pedidos0.entregat=:entregat where pedidos0.pedidosuuid=:pedidosuuid")
    public ResponseEntity<Object> update(@Param(value="pedidosuuid")@PathVariable UUID id,@Param(value="entregat")boolean entregat,Authentication authentication){
        return ResponseEntity.ok().build();
    }
    //


}
