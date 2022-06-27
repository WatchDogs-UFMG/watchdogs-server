package br.ufmg.watchdogs.server.api.repository;

import br.ufmg.watchdogs.server.api.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

    Page<Log> findAll(Pageable pageable);
}
