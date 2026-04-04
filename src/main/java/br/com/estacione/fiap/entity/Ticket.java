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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String valor;

    @Column(nullable = false)
    private String tempo;

    @Column(nullable = false)
    private Boolean pago;

}
