package br.com.estacione.fiap.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name="tb_ticket")
public class Ticket {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID uuid;

    @Column(nullable = false, unique = true, length = 8)
    private String valor;

    @Column(nullable = false, unique = true, length = 8)
    private String tempo;

    @Column(nullable = false, unique = true, length = 8)
    private Boolean pago;

}
