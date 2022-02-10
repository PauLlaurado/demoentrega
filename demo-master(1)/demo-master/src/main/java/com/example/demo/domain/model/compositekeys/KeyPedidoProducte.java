package com.example.demo.domain.model.compositekeys;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

public class KeyPedidoProducte implements Serializable {
    public UUID pedidosid;
    public UUID producteid;
}
