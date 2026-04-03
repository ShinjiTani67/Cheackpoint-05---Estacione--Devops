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

import java.util.UUID;

@Controller
@RequestMapping("/ticket")
@AllArgsConstructor
@Log
public class TicketController {
    private final TicketService service;

    @GetMapping
    public String listTicket(Model model) {
        var ticket = service.getTicket();
        ticket.forEach(a -> log.info("ID do ticket: " + a.getUuid()));
        model.addAttribute("addressList", ticket);
        return "ticket";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Ticket validado!";
    }

    @GetMapping("/new")
    public String newAddress(Model model) {
        model.addAttribute("address", new TicketDTO());
        return "addressformulario";
    }

    @PostMapping("/save")
    public String saveTicket(
            @Valid @ModelAttribute("ticket") TicketDTO ticketDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            log.warning("Erros de validação ao salvar o ticket:");
            bindingResult.getAllErrors().forEach(e -> log.warning(e.toString()));
            model.addAttribute("ticket", ticketDTO);
            return "ticketformulario";
        }

        log.info("Salvando ticket: " + ticketDTO);
        service.save(ticketDTO);
        return "redirect:/ticket";
    }

    @GetMapping("/edit/{id}")
    public String editTicket(@PathVariable UUID id, Model model) {
        TicketDTO ticket = service.findById(id);
        model.addAttribute("ticket", ticket);
        return "ticketformulario";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable UUID id) {
        service.deleteById(id);
        return "redirect:/ticket";
    }

}
