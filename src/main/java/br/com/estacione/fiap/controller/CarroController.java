package br.com.estacione.fiap.controller;


import br.com.estacione.fiap.dto.CarroDTO;
import br.com.estacione.fiap.service.CarroService;
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
@RequestMapping("/carro")
@AllArgsConstructor
public class CarroController {

    private final CarroService service;

    @GetMapping
    public List<CarroDTO> listCarro() {
        return service.getCarro();
    }

    @GetMapping("/{id}")
    public CarroDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public CarroDTO save(@RequestBody CarroDTO carroDTO){
        return service.save(carroDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        service.deleteById(id);
    }
}
