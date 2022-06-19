package br.ufmg.watchdogs.server.api.repository;

import br.ufmg.watchdogs.server.api.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Page<Animal> findAll(Pageable pageable);
}
