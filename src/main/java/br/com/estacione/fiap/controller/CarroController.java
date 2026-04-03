package br.com.estacione.fiap.controller;


import br.com.estacione.fiap.dto.CarroDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carro")
@AllArgsConstructor
@Log
public class CarroController {

    private final CarroService service;

    @GetMapping
    public String listCarro(Model model) {
        var carro = service.getCarro();
        carro.forEach(a -> log.info("ID do carro: " + a.getUuid()));
        model.addAttribute("carroList", carro);
        return "carro";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Carro funcionando!";
    }

    @GetMapping("/new")
    public String newCarro(Model model) {
        model.addAttribute("carro", new CarroDTO());
        return "carroformulario";
    }

    @PostMapping("/save")
    public String saveAddress(
            @Valid @ModelAttribute("carro") CarroDTO carroDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            log.warning("Erros de validação ao salvar carro:");
            bindingResult.getAllErrors().forEach(e -> log.warning(e.toString()));
            model.addAttribute("carro", carroDTO);
            return "carroformulario";
        }

        log.info("Salvando carro: " + carroDTO);
        service.save(carroDTO);
        return "redirect:/carro";
    }

    @GetMapping("/edit/{id}")
    public String editCarro(@PathVariable UUID id, Model model) {
        CarroDTO carro = service.findById(id);
        model.addAttribute("carro", carro);
        return "carroformulario";
    }

    @GetMapping("/delete/{id}")
    public String deleteCarro(@PathVariable UUID id) {
        service.deleteById(id);
        return "redirect:/carro";
    }


}
