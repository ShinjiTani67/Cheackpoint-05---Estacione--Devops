package br.com.estacione.fiap.controller;

import br.com.estacione.fiap.dto.TicketDTO;
import br.com.estacione.fiap.service.TicketService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {

    private final TicketService service;

    @GetMapping
    public List<TicketDTO> listTicket() {
        return service.getTicket();
    }

    @GetMapping("/{id}")
    public TicketDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public TicketDTO save(@RequestBody TicketDTO ticketDTO) {
        return service.save(ticketDTO);
    }

    @PutMapping("/{id}")
    public TicketDTO update(@PathVariable UUID id, @RequestBody TicketDTO ticketDTO){
        ticketDTO.setUuid(id);
        return service.save(ticketDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteById(id);
    }

    @GetMapping("/test")
    public String test() {
        return "Ticket validado!";
    }

}
