package br.com.estacione.fiap.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name="tb_carro")

public class Carro {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID uuid;

    @Column(nullable = false, unique = true, length = 8)
    private String modelo;

    @Column(nullable = false, unique = true, length = 8)
    private String placa;


}
