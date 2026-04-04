package br.com.estacione.fiap.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name="tb_carro")

public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String placa;


}
