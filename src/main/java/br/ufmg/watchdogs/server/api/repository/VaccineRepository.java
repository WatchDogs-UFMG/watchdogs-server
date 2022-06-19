package br.ufmg.watchdogs.server.api.repository;

import br.ufmg.watchdogs.server.api.model.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    Page<Vaccine> findAll(Pageable pageable);
}
