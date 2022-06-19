package br.ufmg.watchdogs.server.api.repository;

import br.ufmg.watchdogs.server.api.model.FoodRelease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodReleaseRepository extends JpaRepository<FoodRelease, Long> {

    Page<FoodRelease> findAll(Pageable pageable);
}
