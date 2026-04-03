package br.com.estacione.fiap.repository;


import br.com.estacione.fiap.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarroRepository extends JpaRepository<Carro, UUID> {

    Optional<Carro> findByUuid(UUID uuid);

}
