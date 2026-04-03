package br.com.estacione.fiap.service;

import br.com.estacione.fiap.dto.CarroDTO;
import br.com.estacione.fiap.entity.Carro;
import br.com.estacione.fiap.repository.CarroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarroService {
    private final CarroRepository repository;

    private CarroDTO convertToDTO(Carro carro){
        CarroDTO dto = new CarroDTO();
        dto.setUuid(carro.getUuid());
        dto.setModelo(carro.getModelo());
        dto.setPlaca(carro.getPlaca());
        return dto;
    }

    private Carro convertToEntity(CarroDTO dto){
        Carro carro = new Carro();
        carro.setUuid(dto.getUuid());
        carro.setModelo(dto.getModelo());
        carro.setPlaca(dto.getPlaca());
        return carro;
    }

    public CarroDTO save(CarroDTO carroDTO){
        Carro carro = convertToEntity(carroDTO);

        if (carro.getUuid() == null){
            carro.setUuid(UUID.randomUUID());
        }

        carro = repository.save(carro);
        return convertToDTO(carro);
    }

    public List<CarroDTO> getCarro(){
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(UUID uuid){
        repository.deleteById(uuid);
    }

    public CarroDTO findById(UUID uuid){
        Optional<Carro> byUuid = repository.findById(uuid);

        if (byUuid.isPresent()){
            return convertToDTO(byUuid.get());
        }

        throw new RuntimeException("Carro com id " + uuid + " nao encontrado");
    }

}
