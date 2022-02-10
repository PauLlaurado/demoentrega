package com.example.demo.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "missatges")
public class Misatges {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID missatgesid;

    public String missatges;
    public String traduccio;
    public UUID iduserenviat;
    public UUID iduserremitent;

    @Override
    public String toString() {
        return "Misatges{" +
                "missatgesid=" + missatgesid +
                ", missatges='" + missatges + '\'' +
                ", traduccio='" + traduccio + '\'' +
                ", iduserenviat=" + iduserenviat +
                ", iduserremitent=" + iduserremitent +
                '}';
    }
}
