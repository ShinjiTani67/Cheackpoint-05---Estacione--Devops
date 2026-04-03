package br.com.estacione.fiap.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarroDTO {
    @NotBlank(message = "ID é obrigatório")
    private UUID uuid;

    @NotBlank(message = "obrigatório")
    private String modelo;

    @NotBlank(message = "obrigatório")
    private String placa;

}
