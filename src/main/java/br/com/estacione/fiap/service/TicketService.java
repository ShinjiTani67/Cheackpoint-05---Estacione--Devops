package br.com.estacione.fiap.service;


import br.com.estacione.fiap.dto.TicketDTO;
import br.com.estacione.fiap.entity.Ticket;
import br.com.estacione.fiap.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository repository;

    private TicketDTO convertToDTO(Ticket ticket){
        TicketDTO dto = new TicketDTO();
        dto.setUuid(ticket.getUuid());
        dto.setValor(ticket.getValor());
        dto.setTempo(ticket.getTempo());
        dto.setPago(ticket.getPago());
        return dto;
    }

    private Ticket convertToEntity(TicketDTO dto){
        Ticket ticket = new Ticket();
        ticket.setUuid(dto.getUuid());
        ticket.setValor(dto.getValor());
        ticket.setTempo(dto.getTempo());
        ticket.setPago(dto.getPago());
        return ticket;
    }

    public TicketDTO save(TicketDTO ticketDTO){
        Ticket ticket = convertToEntity(ticketDTO);

        if (ticket.getUuid() == null){
            ticket.setUuid(UUID.randomUUID());
        }

        ticket = repository.save(ticket);
        return convertToDTO(ticket);
    }

    public List<TicketDTO> getTicket(){
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(UUID uuid){
        repository.deleteById(uuid);
    }

    public TicketDTO findById(UUID uuid){
        Optional<Ticket> byUuid = repository.findById(uuid);

        if (byUuid.isPresent()){
            return convertToDTO(byUuid.get());
        }

        throw new RuntimeException("Ticket com id " + uuid + " nao encontrado");
    }

}
