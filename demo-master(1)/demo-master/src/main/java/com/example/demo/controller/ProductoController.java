package com.example.demo.controller;

import com.example.demo.domain.dto.ResponseList;
import com.example.demo.domain.dto.ResponseMessage;
import com.example.demo.domain.model.Producto;
import com.example.demo.domain.model.projection.ProjectionProducto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/productes")
public class ProductoController {


    @Autowired
    private ProductoRepository producterepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<?> findallproductos(Authentication authentication) {
        return ResponseEntity.ok().body(new ResponseList(producterepository.findBy(ProjectionProducto.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findoneproducto( @PathVariable UUID id) {
        return ResponseEntity.ok().body(new ResponseList(producterepository.findByProducteid(id, ProjectionProducto.class)));
    }

    @PostMapping("/")
    public ResponseEntity<Object> createproducto(@RequestBody Producto producto,Authentication authentication) {
        if (authentication == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (userRepository.findByUsername(authentication.getName()).role.equals("t")) {
            producterepository.save(producto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseMessage.message("Usuari no administrador, no pot crear productes"));

    }

}
