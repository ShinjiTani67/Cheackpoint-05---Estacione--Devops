package br.com.estacione.fiap.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDTO {

    @NotBlank(message = "ID é obrigatório")
    private UUID uuid;

    @NotBlank(message = "obrigatório")
    private String valor;

    @NotBlank(message = "obrigatório")
    private String tempo;

    @NotBlank(message = "obrigatório")
    private Boolean pago;

}
